package top.wys.utils;

import com.google.common.collect.Maps;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by 郑明亮 on 2018/10/14 16:49.
 */
public class RandomUtilsTest {

//    @Test
    public void getRandomIp() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomIp());
        }
    }

//    @Test
    public void getRandomEmail() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomEmail(5,10));
        }
    }

//    @Test
    public void getNum() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getNum(5,10));
        }
    }

//    @Test
    public void getRandomNumCode() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomNumCode(5));
        }
    }

//    @Test
    public void getRandomCode() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.getRandomCode(5));
        }
    }

//    @Test
    public void getDoubleNum() {
        long start = System.currentTimeMillis();
        System.out.println("------start--------"+DateUtils.getStringByPattern(new Date(start),"yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 10000; i++) {

            System.out.println(RandomUtils.getDoubleNum(9, 10));
        }
        long end = System.currentTimeMillis();
        System.out.println("------end--------"+DateUtils.getStringByPattern(new Date(end),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时："+(end - start)+"ms");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("------start--------"+DateUtils.getStringByPattern(new Date(start),"yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 1000000; i++) {
            RandomUtils.getDoubleNum(9, 10);
//            System.out.println();
//            System.out.println();
        }
        long end = System.currentTimeMillis();
        System.out.println("------end--------"+DateUtils.getStringByPattern(new Date(end),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时："+(end - start)+"ms");
    }
//    @Test
    public void getDoubleNum2() {
        long start = System.currentTimeMillis();
        System.out.println("------start--------"+DateUtils.getStringByPattern(new Date(start),"yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 10000; i++) {

            System.out.println(RandomUtils.getDoubleNum(9, 10));
        }
        long end = System.currentTimeMillis();
        System.out.println("------end--------"+DateUtils.getStringByPattern(new Date(end),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("耗时："+(end - start)+"ms");
    }

    @Test
    public void getRandomUserAgent() {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.getRandomUserAgent());
        }
    }

    @Test
    public void cookieJarTest() throws IOException {
        Map<String,Object> params = Maps.newHashMap();
        params.put("name","zhangsan");
        String testResult = HttpUtils.get("http://localhost:8084/conf/test",params);
        System.out.println("testResult = " + testResult);
        String testCookieResult = HttpUtils.get("http://localhost:8084/conf/testCookie");
        System.out.println("testCookieResult = " + testCookieResult);
    }


    @Test
    public void randomPerson() {
        for (int i = 0; i < 10; i++) {

            Map randomPerson = RandomUtils.getRandomPerson();
            System.out.println("randomPerson = " + randomPerson);
        }
    }

    @Test
    public void randomIdCard() {
        String randomIdCard = RandomUtils.getRandomIdCard();
        System.out.println("randomIdCard = " + randomIdCard);

    }

    String[] checkArrays = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    @Test
    public void isLegalIdCard() {
//        String id1 = "500231199610070431";
        String ids = "230925198107088926,130481199506132562,130104199601231850,130302199605081624,130123199511287526,130921199507134624,130582199501173041,130705199605270020,130225199511130041,130632199610165210,130229199603204840,130131199411205140,130828199508205526,130828199501272718,130823199509297520,522131199411065242,513901199612305243,420203199512103329,500240199511043960,231004199508191216,331082199511096941,131126199611170026,130903199509142326,130181199607017321,130623199511170026,130982199308110921,130534199512187720,130181199402117927,131026199602200021,130682199408291380,130129199612192129,130532199602023049,130684199610280661,131122199408080820,13110219951229142X,130185199603300512,130628199512131821,130582199603133665,130927199501043623,130532199702138046,131102199501090614,130525199711200047,130429199402136520,130582199411211227,13090219951228322X,13032219960515004X,130221199610140047,130426199412011917,130632199211171015,132201199512211610,130824199508070021,130630199506093621,130826199602055015,130828199508280040,130828199501206710,132626199411135013,432503199610075042,500230199509094870,420922199702080049,340111199602134545,232326199601210042,330302199605136821,130104199602032423,130404199604261226,130902199501093631,130322199410220626,130927199603190034,130528199611300824,130727199410061948,130481199511162125,130603199608250342,131122199601183442,130723199312314520,131181199501070045,13090219950209031X,130205199509121220,130730199502273823,130221199410150064,130425199603030022,130533199506022042,130622199609198012,130528199506081842,131182199611254247,130121199610133028,130632199410154429,130426199506045229,130283199611120629,131122199503203219,130636199504167314,130702199506090914,130722199505262126,130225199509193318,130125199406211514,130722199307012521,130125199608064524,130321199310070636,430424199610102922,61232719961014032X,612323199604256013,342623199611262332,231026199509250327,13082519941128242X,130802199307052013,130321199401035924,130823199410120020,460104199610010933,130804199602131024,131127199611060069,130182199409132421,131126199403303023,131082199607140268,522427199609207025,130106199709290329,130133199406100056,131121199510282027,130604199509231820,131002199508162427,130434199512161621";
        for (String id : ids.split(",")) {
            checkIdCard(id);
        }
    }

    private void checkIdCard(String id1) {
        char[] chars = id1.toCharArray();
        int total = Integer.parseInt(chars[0] + "") * 7
                + Integer.parseInt(chars[1] + "") * 9
                + Integer.parseInt(chars[2] + "") * 10
                + Integer.parseInt(chars[3] + "") * 5
                + Integer.parseInt(chars[4] + "") * 8
                + Integer.parseInt(chars[5] + "") * 4
                + Integer.parseInt(chars[6] + "") * 2
                + Integer.parseInt(chars[7] + "") * 1
                + Integer.parseInt(chars[8] + "") * 6
                + Integer.parseInt(chars[9] + "") * 3
                + Integer.parseInt(chars[10] + "") * 7
                + Integer.parseInt(chars[11] + "") * 9
                + Integer.parseInt(chars[12] + "") * 10
                + Integer.parseInt(chars[13] + "") * 5
                + Integer.parseInt(chars[14] + "") * 8
                + Integer.parseInt(chars[15] + "") * 4
                + Integer.parseInt(chars[16] + "") * 2;
        int check = total % 11;
//        System.out.println("checkNum = " + check);
        String checkStr = checkArrays[check];
//        System.out.println("check:" + checkStr);
        boolean equals = checkStr.equals(id1.charAt(17) + "");
        System.out.printf("id:%s 校验结果为 %s,校验位为：%s \r\n", id1, equals, checkStr);
    }
}
