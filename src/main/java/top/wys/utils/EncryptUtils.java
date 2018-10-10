package top.wys.utils;/**
 * Created by 郑明亮 on 2018/10/4 20:44.
 */

/**
 * @author 郑明亮   @email 1072307340@qq.com
 * @version 1.0
 * @time 2018/10/4 20:44
 * TODO
 */
public class EncryptUtils {
    /**
     *  <p>自定义对称加密</p>
     * <ol>
     * <li>利用base64编码的原理；</li>
     * <li>根据时间戳生成随机位置和可还原密钥，将密钥字符插入到生成的随机位置当中，解密则采用相反的方式</li>
     * </ol>
     * @author 郑明亮
     * @time 2018年10月4日20:43:12
     * @param orignString 需要加密的原字符串
     * @param time 时间戳
     * @return 编码后的字符串
     */
    public static String zmlEncode(String orignString, long time){
        Long anotherTime = Long.valueOf(new StringBuilder(time + "").reverse().toString());
//        System.out.println("anotherTime:"+anotherTime);
        String encoding = RandomUtils.encoding(anotherTime);
//        System.out.println("anotherTime-encode:"+ encoding);
        String encodeString = Base64Coder.encodeString(orignString);
//        System.out.println("base64:"+encodeString);
        StringBuilder sb = new StringBuilder(encodeString);
        char[] chars = encoding.toCharArray();
        int[] position = getPosition(time, 2);
        for (int i = 0; i < position.length; i++) {
            if(position[i] >= sb.length()){
                sb.insert(i,chars[i]);
//                System.out.println(i+"--"+chars[i]);
            }else{
                sb.insert(position[i],chars[i]);
//                System.out.println(position[i]+"--"+chars[i]);
            }
        }
        return sb.toString();
    }


    /**
     *
     * <li>自定义对称解密，time字段必需为encryptString加密时的时间戳</li>
     * @author 郑明亮
     * @time 2018年10月4日20:42:47
     * @param encryptString 要解码的字符串
     * @param time 时间戳
     * @return 解码后的字符串
     */
    public static String zmlDecode(String encryptString, long time){
        Long anotherTime = Long.valueOf(new StringBuilder(time + "").reverse().toString());
//        System.out.println("anotherTime:"+anotherTime);
//        String encoding = RandomUtils.encoding(anotherTime);
//        System.out.println("anotherTime-encode:"+ encoding);

        StringBuilder sb = new StringBuilder(encryptString);

        int[] position = getPosition(time, 2);
        for (int i = position.length-1; i >= 0; i--) {
            int length = sb.length();
//            int secretLen = encoding.length();
            if (position[i] >= length) {
                sb.deleteCharAt(i);
            }else{
                sb.deleteCharAt(position[i]);
            }

        }
//        System.out.println("decode:"+sb.toString());
        String encodeString = Base64Coder.decodeString(sb.toString());
        return encodeString;
    }

    static int[] getPosition(Long number,int step){
        StringBuilder builder = new StringBuilder(number+"");
        int len = builder.length();
        if(step>=len){
            return new int[]{Integer.parseInt(number+"")};
        }
        int size = (len%step==0?len/step:len/step +1);
        int[] positions = new int[size];
        for (int i = 0,start = 0,end=start+step; i < size; i++) {
            if(start == end){
                positions[i] = Integer.parseInt(builder.substring(start));
            }else{
                positions[i] = Integer.parseInt(builder.substring(start,end));
            }
            start +=step;
            end = start+step;
            if(start >= len){
                break;
            }
            if(end >=len){
                end = len -1;
            }
        }
        return positions;
    }
}
