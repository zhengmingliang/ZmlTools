package top.wys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.util.*;
/*
 * Created by 郑明亮 on 2020/9/19 14:34.
 */

//

public class DataUtilsTest {

    @Test
    public void isWebUrl() {
        @Language("json")
        String json = "{\n" +
                "  \"ruyou\": \"\",\n" +
                "  \"sougou\": \"https://img02.sogoucdn.com/app/a/100520146/463c5414e61cb8bb194e8da687d4defc\",\n" +
                "  \"fanruan\": \"https://bbs.fanruan.com/upload/wenda/20200919/1600496701719871.jpg\",\n" +
                "  \"iqiyi\": \"http://pan.iqiyi.com/file/sns_comment_v2/dzvo7Fz77eO8-VVbP2aDjCJb2-HoZ05ZUtMuRNrM9AuRa0Pr31p6CZDCaPdIAC4iGXuC54U6iZNS7IHIfxddGg.jpg\",\n" +
                "  \"souhu\": \"https://cy-pic.kuaizhan.com/g3/46/aa/9b3d-d8ec-472e-8642-dfa2325c353541?cysign=346004560ebec4ab1ce15ffdfb2d27fe&cyt=1600496692\",\n" +
                "  \"fenghuang\": \"https://x0.ifengimg.com/uploading/user/20200919142502/114936669/39a434b95111451ca5d4546316aea30a.jpg\",\n" +
                "  \"360快传\": \"http://p6.qhimg.com/t011be5862d2b578f8f.jpg\",\n" +
                "  \"csdn\": \"\",\n" +
                "  \"youku\": \"https://image.planet.youku.com/img/100/19/62238/i_1490875862238_463c5414e61cb8bb194e8da687d4defc_b_w1920h1200_face_w1920h1200_x962y293w189h281c1.jpg\",\n" +
                "  \"youdata163\": \"https://nos.netease.com/youdata-netease/public-1600496706221693838.jpg\",\n" +
                "  \"segmentfault\": \" https://image-static.segmentfault.com/188/566/1885663690-5f65a43fafef9\",\n" +
                "  \"163yanxuan\": \"{\\\"code\\\":\\\"403\\\"}\",\n" +
                "  \"lightshot\": \"https://image.prntscr.com/image/E6VsZJyPSeqgeITc3s4uUQ.jpg\"\n" +
                "}\n";

        Map<Object, Object> map = FastJsonTools.createJsonToMap(json);
        map.forEach((key,value) ->{
            boolean webUrl = DataUtils.isWebUrl(value + "");
            boolean webUrl2 = DataUtils.isWebUrl(value + "");
            System.out.println("isUrl: " + webUrl+","+webUrl2+"   "+value);
        });

    }

    @Test
    public void getDateFromText() {
        String text = "账单周期 Statement Cycle 2021/10/11-2021/11/10\n"
                +        "信用额度(人民币) Credit Limit(RMB) 29,000\n"
                +        "预借现金额度(人民币) Cash Advance Limit(RMB) 14,500\t\t到期还款日 Payment Due Date 2021年11月30日"
                ;
        String dateFromText = DataUtils.getDateFromText(text);
        System.out.println(dateFromText);
        List<String> datesFromText = DataUtils.getDatesFromText(text);
        System.out.println(datesFromText);
    }

    @Test
    public void getDateTimeFromText() {
        String text = "账单周期 Statement Cycle 2021/10/11-2021/11/10\n"
                +        "2021-01-10 12:00:12,2022/10/11 12:32,2012年10月12日 12:10:12\n"
                +        "预借现金额度(人民币) Cash Advance Limit(RMB) 14,500\t\t到期还款日 Payment Due Date 2021年11月30日"
                ;
        String dateFromText = DataUtils.getDateTimeFromText(text);
        System.out.println(dateFromText);
        List<String> datesFromText = DataUtils.getDateTimesFromText(text);
        System.out.println(datesFromText);
    }

    @Test
    public void getNullPropertyNames() {
        Person person = Person.builder()
                .name("张三")
                .age(15)
                .address("")
                .friends(new ArrayList<>(2))
                .build();
        String[] nullPropertyNames = DataUtils.getNullPropertyNames(person);
        Assert.isTrue(((JSONArray)JSON.toJSON(nullPropertyNames)).containsAll(Arrays.asList("sex")));
        String[] emptyPropertyNames = DataUtils.getEmptyPropertyNames(person);
        Assert.isTrue(((JSONArray)JSON.toJSON(emptyPropertyNames)).containsAll(Arrays.asList("sex","address","friends")));
    }

    @Test
    public void mapToBean() {
        Map<String,Object> params = new HashMap<>();
        params.put("name","张三");
        params.put("age",21);
        params.put("sex",1);
        params.put("friends",Arrays.asList(new JSONObject().fluentPut("name","李四").fluentPut("age",20).put("sex",true)));

        Person person = DataUtils.mapToBean(params, Person.class);
//        Person person2 = mapToBean(params, Person.class);
        System.out.println(JSON.toJSONString(person));
//        System.out.println(JSON.toJSONString(person2));
    }


   /* public static <T> T mapToBean(Map<String, Object> map, Class<T> t){
        if (map == null){
            return null;
        }

        T obj = null;
        try {
            obj = t.newInstance();

            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return obj;
    }*/

    @Data
    @Builder
    public static class Person{
        public Person() {
        }

        public Person(String name, String sex, int age, String address, List<Person> friends) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.address = address;
            this.friends = friends;
        }

        private String name;
        String sex;
        int age;
        private String address;
        List<Person> friends;
    }
}