///**
// * Created by 郑明亮 on 2022/3/6 15:38.
// */
//package top.wys.utils;
//
//import com.google.common.base.Stopwatch;
//import com.owlike.genson.GenericType;
//import com.owlike.genson.Genson;
//import com.owlike.genson.GensonBuilder;
//import org.junit.Test;
//import org.noear.snack.ONode;
//import org.noear.weed.DbContext;
//import org.springframework.util.StopWatch;
//
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
///**
// * <p> this is your description</p>
// *
// * @author 郑明亮
// * @version 1.0.0
// * @time 2022/3/6 15:38
// */
//public class JsonTest {
//
//    String json = FileUtils.readTxtFile("D:\\zml\\workspace\\git\\ZmlTools\\src\\test\\resources\\TWEET.json");
//    @Test
//    public void genson() throws InterruptedException {
//        // 使用fastjson
//        TimeUnit.SECONDS.sleep(1);
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        Genson genson = new GensonBuilder().useStrictDoubleParse(true).useRuntimeType(true).create();
//        int size = 100000;
//        for (int i = 0; i < size; i++) {
////            List<FieldDataVo> dataVos = genson.deserialize(json, new GenericType<List<FieldDataVo>>() {});
//            FieldDataVo dataVo = genson.deserialize(json, FieldDataVo.class);
//        }
//        System.out.println("genson:"+stopwatch);
//        stopwatch.reset();
//        stopwatch.start();
//        for (int i = 0; i < size; i++) {
////            List<FieldDataVo> list = GsonTools.getListFromJson(json, FieldDataVo[].class);
//            FieldDataVo vo = GsonTools.getBeanFromJson(json, FieldDataVo.class);
//        }
//        System.out.println("gson:"+stopwatch);
//
//        stopwatch.reset();
//        stopwatch.start();
//        for (int i = 0; i < size; i++) {
////            List<FieldDataVo> bean = FastJsonTools.createJsonToListBean(json, FieldDataVo.class);
//            FieldDataVo bean = FastJsonTools.createJsonBean(json, FieldDataVo.class);
//        }
//        System.out.println("fastjson:"+stopwatch);
//
//        stopwatch.reset();
//        stopwatch.start();
//        for (int i = 0; i < size; i++) {
////            List<FieldDataVo> bean = FastJsonTools.createJsonToListBean(json, FieldDataVo.class);
//            FieldDataVo bean = ONode.deserialize(json, FieldDataVo.class);
//        }
//        System.out.println("snack:"+stopwatch);
////        System.out.println(bean);
//
////        System.out.println(list.toString());
////        System.out.println(dataVos.toString());
//
//    }
//
//    @Test
//    public void snack() {
//        FieldDataVo bean = ONode.deserialize(json, FieldDataVo.class);
//        System.out.println(bean);
//    }
//
//    @Test
//    public void weed() {
//        Properties prop = new Properties();
//        prop.setProperty("url","jdbc:mysql://192.168.4.77:6610/sso?characterEncoding=utf-8&serverTimezone=GMT&useSSL=false");
//        prop.setProperty("username","root");
//        prop.setProperty("password","icell");
//        DbContext db = new DbContext(prop);
//        System.out.println(System.getProperty("line.separator"));
//        String sql = db.table("user").where("name", "zhangsan").andEq("sex", "1").andGt("age", 20).limit(1, 20).selectQ("name,age,sex").toString();
//        System.out.println(sql);
//    }
//}
