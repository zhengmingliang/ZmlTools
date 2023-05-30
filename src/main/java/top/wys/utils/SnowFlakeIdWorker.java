/*
 * Created by 郑明亮 on 2021/4/29 18:35.
 */

//

package top.wys.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wys.utils.convert.ConvertUtils;

/**
 * <ol>
 *     Twitter的Snowflake 算法<br>
 * 分布式系统中，有一些需要使用全局唯一ID的场景，有些时候我们希望能使用一种简单一些的ID，并且希望ID能够
 * <p>
 * snowflake的结构如下(每部分用-分开):<br>
 * <pre>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 0
 * </pre>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)24个节点）<br>
 *    得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * 并且可以通过生成的id反推出生成时间,datacenterId和workerId
 * <p>
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/4/25 21:35
 * @email mpro@vip.qq.com
 * @since 1.2.2
 */
public class SnowFlakeIdWorker {

    private static final Logger log = LoggerFactory.getLogger(SnowFlakeIdWorker.class);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;
    private long sequence;

    public static final SnowFlakeIdWorker INSTANCE;
    static {
        Integer workId = 1;
        try {
            String localHostIP = DataUtils.getLocalHostIP();
            int lastIndex = localHostIP.lastIndexOf(".");
            workId = ConvertUtils.toInteger(localHostIP.substring(lastIndex + 1), 1) % 32;
        } catch (Exception e) {
            log.warn("自动计算工作机器id出现错误，将使用默认值1",e);
        }
        INSTANCE = new SnowFlakeIdWorker(workId,10,100);
    }

    /**
     * @param workerId 工作机器ID(0~31)
     * @param datacenterId 数据中心ID(0~31)
     * @param sequence 毫秒内序列(0~4095)
     */
    public SnowFlakeIdWorker(long workerId, long datacenterId, long sequence){
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d\n",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    private long twepoch = 1288834974657L;

    /**
     * 机器id所占的位数
     */
    private long workerIdBits = 5L;
    /**
     * 数据标识id所占的位数
     */
    private long datacenterIdBits = 5L;
    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 支持的最大数据标识id，结果是31
     */
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 序列在id中占的位数
     */
    private long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private long workerIdShift = sequenceBits;
    /**
     * 数据标识id向左移17位(12+5)
     */
    private long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间截向左移22位(5+5+12)
     */
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * @return 获取 工作机器ID
     */
    public long getWorkerId(){
        return workerId;
    }

    /**
     * @return 获取数据中心ID
     */
    public long getDatacenterId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    public String nextStringId(){
        return nextId() + "";
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }
}
