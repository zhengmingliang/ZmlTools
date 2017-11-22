package top.wys.utils;
/**
 * Created by 郑明亮 on 2017/11/17 15:41.
 */

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * @author 郑明亮   @email 1072307340@qq.com
 * @version 1.0
 * @time 2017/11/17 15:41
 * @description <p> </p>
 */
public class PropertiesUtilTest {

    @org.junit.Test
    public void testGetKeys() throws IOException {
        int size = 100;


        long start1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            Properties properties = new Properties();
            properties.load(PropertiesUtilTest.class.getResourceAsStream("/sysconfig.properties"));
            Set<String> strings = properties.stringPropertyNames();
            for (String string : strings) {
                System.out.printf("%s,\t",string);

            }
        }
        System.out.println();
        System.out.println("timnGaps1:%l,\t"+(System.currentTimeMillis()-start1));

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            if (PropertiesUtil.loadFromClassPath("sysconfig.properties")) {
//                Set<String> keys = PropertiesUtil.getAllKeys();//在配置文件少量时，使用该方法更省时
                Set<String> keys = PropertiesUtil.getKeys("sysconfig.properties");//在配置文件少量时，使用该方法更省时
                for (String key : keys) {
                    System.out.printf("%s \t",key);
                }
            }
        }
        System.out.println();
        System.out.println("timnGaps3:,\t"+(System.currentTimeMillis()-start3));

    }


    @org.junit.Test
    public void testGetValue() throws IOException {
        int size = 100;



        long start1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            Properties properties = new Properties();
            properties.load(PropertiesUtilTest.class.getResourceAsStream("/sysconfig.properties"));
            System.out.printf("end:%s",properties.getProperty("name"));
        }
        System.out.println();
        System.out.println("timnGaps1:%l,\t"+(System.currentTimeMillis()-start1));


        long start11 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            Properties properties = new SafeProperties();
            properties.load(PropertiesUtilTest.class.getResourceAsStream("/sysconfig.properties"));
            System.out.printf("end:%s",properties.getProperty("name"));
        }
        System.out.println();
        System.out.println("timnGaps11,\t"+(System.currentTimeMillis()-start11));





        long start3 = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            if (PropertiesUtil.loadFromClassPath("sysconfig.properties")) {
                System.out.printf("end:%s",PropertiesUtil.getInteger("age"));
                System.out.printf("end:%s",PropertiesUtil.getInteger("name2"));
            }
        }
        System.out.println();
        System.out.println("timnGaps3:,\t"+(System.currentTimeMillis()-start3));
    }


    @org.junit.Test
    public void testGetKey(){
        if (PropertiesUtil.loadFromClassPath("config.properties")) {
            PropertiesUtil.loadFromClassPath("jdbc2.properties");
//            System.out.println(PropertiesUtil.getString("jdbc_url"));
            System.out.println(PropertiesUtil.getString("name"));
        }
    }
    @Test
    public void testUpdate(){
        if (PropertiesUtil.loadFromClassPath("jdbc2.properties")) {
            System.out.println("修改前："+PropertiesUtil.getString("redis.port"));
            boolean update = false;
            try {
                update = PropertiesUtil.update("redis.port", "6380", "原来是6379，现在改成了6380", true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (update){
                System.out.println("修改后："+PropertiesUtil.getString("redis.port"));
            }else{
                System.out.println("更新失败");
            }
        }
    }
}
