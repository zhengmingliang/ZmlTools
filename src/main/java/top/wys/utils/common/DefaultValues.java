/**
 * Created by 郑明亮 on 2022/1/25 10:17.
 */
package top.wys.utils.common;

/**
 * <p> 常用默认值</p>
 *
 * @author 郑明亮
 * @version 1.3.3
 * @time 2022/1/24 22:17
 */
public class DefaultValues {

    /**
     * 默认分页条数
     */
    public static final int PAGE_SIZE = 10;

    /**
     * 默认分页最大条数
     */
    public static final int MAX_PAGE_SIZE = 50;
    /**
     * 默认缓存时间 30分钟
     */
    public static final int CACHE_TIME = 30 * 60 * 1000;
    /**
     * 默认的父级节点id
     */
    public static final String ROOT_NODE_ID = "root";
    /**
     * 默认的路径分隔符
     */
    public static final String DEFAULT_PATH_DELIMITER = "/";


    public interface Datasource{
        /**
         *  初始化连接(最小连接数)
         */
        int MIN_ACTION_NUM = 1;
        /**
         * 最大连接数
         */
        int DEFAULT_DATASOURCE_MAX_ACTION_NUM = 10;
        /**
         * 最大等待时间
         */
        int DEFAULT_DATASOURCE_MAX_WAIT_TIME = 15000;
        /**
         * 最小空闲时间
         */
        int DEFAULT_DATASOURCE_MIN_EVICTABLE_IDLE_TIME = 30000;
        /**
         * 最大空闲时间
         */
        int MAX_EVICTABLE_IDLE_TIME = 30000;
    }


    public interface Date{
        /**
         * 默认时间格式化
         */
        String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
        /**
         * 默认时间格式化
         */
        String DATE_PATTERN = "yyyy-MM-dd";
    }

    public interface Thread{
        /**
         * 要保留的空闲线程数。
         */
        int CORE_POOL_SIZE = 0;
        /**
         * 最大线程池数量
         */
        int MAX_POOL_SIZE = 32;
        /**
         * 定时线程池处理线程数
         */
        int SCHEDULED_EXECUTOR_POOL_SIZE = 8;
    }
}
