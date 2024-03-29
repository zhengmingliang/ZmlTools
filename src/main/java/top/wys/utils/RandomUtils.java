package top.wys.utils;
/**
 * Created by 郑明亮 on 2018/10/1 15:58.
 */

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author 郑明亮
 * @version 1.0
 * @description 随机数生成工具类
 */
public class RandomUtils {
    private RandomUtils() {
        throw new UnsupportedOperationException("you can not instantiate me !");
    }

    private static String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final int[][] RANGE_IP = {{607649792, 608174079},//36.56.0.0-36.63.255.255
            {1038614528, 1039007743},//61.232.0.0-61.237.255.255
            {1783627776, 1784676351},//106.80.0.0-106.95.255.255
            {2035023872, 2035154943},//121.76.0.0-121.77.255.255
            {2078801920, 2079064063},//123.232.0.0-123.235.255.255
            {-1950089216, -1948778497},//139.196.0.0-139.215.255.255
            {-1425539072, -1425014785},//171.8.0.0-171.15.255.255
            {-1236271104, -1235419137},//182.80.0.0-182.92.255.255
            {-770113536, -768606209},//210.25.0.0-210.47.255.255
            {-569376768, -564133889}, //222.16.0.0-222.95.255.255
    };
    private static final String[] TEL_FIRST ="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static final String BASE = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String FIRST_NAME ="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    private static final String GIRL ="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static final String BOY ="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    private static final String[] ROAD ="重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");
    private static final String[] EMAIL_SUFFIX ="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    private static final String[] USER_AGENT_PC ={"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",//chrome
            "Mozilla/5.0 (Windows NT 7.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36 OPR/56.0.3051.52 (Edition B2)",//欧朋
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0",//Firefox
            "User-Agent: Mozilla/5.0 (Windows 7.0; WOW32; Trident/7.0; Touch; rv:11.0) like Gecko",//IE
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134"};//microsoft edge

    private static final String[] USER_AGENT_APP ={"Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)",//windows phone
            "Mozilla/5.0 (Linux; U; Android 4.1.1; ja-jp; Galaxy Nexus Build/JRO03H) ",//
            "Opera/9.80 (Android 4.0.4; Linux; Opera Mobi/ADR-1205181138; U; pl) Presto/2.10.254 Version/12.00",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows Phone OS 7.0; Trident/3.1; IEMobile/7.0) Asus;Galaxy6",
            "Dalvik/2.1.0 (Linux; U; Android 8.1.0; COL-AL10 Build/HUAWEICOL-AL10)",
            "Dalvik/2.1.0 (Linux; U; Android 7.0; BTV-W09 Build/HUAWEIBEETHOVEN-W09)"};
    private static final Random RANDOM = new SecureRandom();

    /**
     *  随机生成省、自治区、直辖市代码 1-2
     */
    private static final String PROVINCES[] = { "11", "12", "13", "14", "15", "21", "22", "23",
            "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82" };

    private static final char[] ID_CARD_SEX_CHECK_NUMS = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    /**
     * 获取随机ip地址
     *
     * @return
     */
    public static String getRandomIp() {
        //ip的范围

        //生成一个随机数
//        Random random = new Random();
        int index = RANDOM.nextInt(10);
        String ip = numToip(RANGE_IP[index][0] + RANDOM.nextInt(RANGE_IP[index][1] - RANGE_IP[index][0]));//获取ip

        return ip;
    }

    /**
     * 数字拼接成ip字符串
     *
     * @param ip
     * @return
     */
    private static String numToip(int ip) {
        int[] b = new int[4];

        b[0] = (ip >> 24) & 0xff;
        b[1] =  (ip >> 16) & 0xff;
        b[2] = (ip >> 8) & 0xff;
        b[3] = ip & 0xff;
        String ipStr = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
        return ipStr;
    }
    /**
     * 返回随机手机号码
     */
    public static String getRandomTel() {
        int index=getNum(0, TEL_FIRST.length-1);
        String first= TEL_FIRST[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }


    /**
     * 返回随机Email
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getRandomEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = nextInt(BASE.length());
            sb.append(BASE.charAt(number));
        }
        sb.append(EMAIL_SUFFIX[nextInt(EMAIL_SUFFIX.length)]);
        return sb.toString();
    }

    public static final String COMPANY ="谷信维点讯云网科宝壳久恒唐神洲百度雷米格子美团饿了么拉钩智联";
    public static String getRandomCompany(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuilder sb = new StringBuilder("北京");
        for (int i = 0; i < length; i++) {
            int number = nextInt(BASE.length());
            sb.append(COMPANY.charAt(number% COMPANY.length()));
        }
        sb.append("科技有限公司");
        return sb.toString();
    }

    /**
     * 获取随机身份证号
     * @return
     */
    public static String getRandomIdCard() {
        String id = "";

        // 随机生成省、自治区、直辖市代码 1-2
        String province = randomOne(PROVINCES);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirth(20, 50);
        // 随机生成顺序号 15-17(随机性别)
        String no = RANDOM.nextInt(899) + 100+"";
        String partId = province + city + county + birth + no;
        // 计算第 18 位校验码
//        String check = randomOne(ID_CARD_SEX_CHECK_NUMS);
        char check = getIdCardCheckNum(partId);
        // 拼接身份证号码
        id = partId + check;
        return id;
    }

    /**
     * 获取随机身份证号
     * @return
     */
    public static String getRandomIdCard(int minAge,int maxAge) {
        return IdCardGenerator.generate(minAge, maxAge);
    }

    /**
     * 获取随机身份证号
     * @see RandomUtils#getRandomIdCard(int, int)
     * @return
     */
    @Deprecated
    public static String getRandomIdCard2(int minAge,int maxAge) {
        String id = "";

        // 随机生成省、自治区、直辖市代码 1-2
        String province = randomOne(PROVINCES);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirth(minAge, maxAge);
        // 随机生成顺序号 15-17(随机性别)
        String no = RANDOM.nextInt(899) + 100+"";
        String partId = province + city + county + birth + no;
        // 计算第 18 位校验码
//        String check = randomOne(ID_CARD_SEX_CHECK_NUMS);
        char check = getIdCardCheckNum(partId);
        // 拼接身份证号码
        id = partId + check;
        return id;
    }

    /**
     * 获取身份证号的校验位数字（第18位校验码）
     * @param id
     */
    public static char getIdCardCheckNum(String id) {
        char[] chars = id.toCharArray();
        if(chars.length < 17){
            return ' ';
        }
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
       return ID_CARD_SEX_CHECK_NUMS[check];
    }

    /**
     * 从String[] 数组中随机取出其中一个String字符串
     *
     * @param randomArray
     * @return
     */
    public static String randomOne(String[] randomArray) {
        return randomArray[RANDOM.nextInt(randomArray.length - 1)];
    }

    /**
     * 从char[] 数组中随机取出其中一个char
     * @param randomArray
     * @return
     */
    public static String randomOne(char[] randomArray) {
        return randomArray[RANDOM.nextInt(randomArray.length - 1)]+"";
    }

    /**
     * 随机生成两位数的字符串（01-max）,不足两位的前面补0
     *
     * @param max 最大值
     * @return
     */
    private static String randomCityCode(int max) {
        int i = RANDOM.nextInt(max) + 1;
        return i > 9 ? i + "" : "0" + i;
    }

    /**
     * 随机生成minAge到maxAge年龄段的人的生日日期
     *
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return
     */
    public static String randomBirth(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + RANDOM.nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }

    /**
     *
     * 获取【start，end】之间的整数,包含start和end
     * @param start 最小值
     * @param end 最大值
     * @return
     */
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    /**
     * @param bound 最大不超过的数字
     * @return 返回[0,bound) 范围内的整数
     */
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * 获取【start，end】之间的浮点数
     * @param start 最小值
     * @param end 最大值
     * @return
     */
    public static double getDoubleNum(int start,int end) {
        return (Math.random()*(end-start)+start);
    }

    /**
     * 获取【start，end】之间的浮点数
     * @param start 最小值
     * @param end 最大值
     * @return
     */
    public static double getDoubleNum(double start,double end) {
        return (Math.random()*(end-start)+start);
    }

    /**
     * 获取【start，end】之间的浮点数
     * @param start 最小值
     * @param end 最大值
     * @param point 保留小数位数
     * @return
     */
    public static String getDoubleNum(double start,double end,int point) {
        double doubleNum = getDoubleNum(start, end);
        return NumberUtils.cutByPoint(doubleNum + "",point);
    }

    /**
     * 返回中文姓名
     */
    private static String name_sex = "";
    public static String getChineseName() {
        int index=getNum(0, FIRST_NAME.length()-1);
        String first= FIRST_NAME.substring(index, index+1);
        int sex=getNum(0,1);
        String str= BOY;
        int length= BOY.length();
        if(sex==0){
            str= GIRL;
            length= GIRL.length();
            name_sex = "0";
        }else {
            name_sex="1";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }

    /**
     * 返回地址
     * @return
     */
    public static String getRandomRoad() {
        int index=getNum(0, ROAD.length-1);
        String first= ROAD[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }

    /**
     * 数据封装
     * @return
     */
    public static Map getRandomPerson() {
        Map map=new HashMap(7);
        map.put("name", getChineseName());
        map.put("sex", name_sex);
        map.put("company", getRandomCompany(2,4));
        map.put("road", getRandomRoad());
        map.put("telephone", getRandomTel());
        map.put("mailbox", getRandomEmail(6,9));
        map.put("idCard", getRandomIdCard(18,40));
        return map;
    }


    /**
     * 获取几位随机数
     *
     * @param number
     * @return
     */
    public static String getRandomNumCode(int number) {
        StringBuilder codeNum = new StringBuilder();

//        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = RANDOM.nextInt(10000);
            codeNum .append(NUMBERS[next % 10]);
        }
        return codeNum.toString();
    }
    /**
     * 生成随机码值，包含数字、大小写字母
     *
     * @param number 生成的随机码位数
     * @return
     * @author 郑明亮
     */
    public static String getRandomCode(int number) {
        StringBuilder codeNum = new StringBuilder();
        int[] code = new int[3];
//        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int num = RANDOM.nextInt(10) + 48;
            int uppercase = RANDOM.nextInt(26) + 65;
            int lowercase = RANDOM.nextInt(26) + 97;
            code[0] = num;
            code[1] = uppercase;
            code[2] = lowercase;
            codeNum.append((char) code[RANDOM.nextInt(3)]);
        }

        return codeNum.toString();
    }

    /**
     * 将数字戳进行编码，生成指定字符串
     * @param num 大于0的整数
     * @return
     */
      public static String encoding(long num) {
        if (num < 1L) {
            throw new RuntimeException("num must be greater than 0.");
        } else {
            StringBuilder sb;
            for(sb = new StringBuilder(); num > 0L; num /= 62L) {
                sb.append(ALPHABET.charAt((int)(num % 62L)));
            }

            return sb.toString();
        }
    }

    /**
     * 将字符串进行解码为大于0的整数
     * @param str 进行过编码的字符串
     * @return 解码后的数字
     */
    public static long decoding(String str) {
        str = str.trim();
        if (str.length() < 1) {
            throw new RuntimeException("str must not be empty.");
        } else {
            long result = 0L;

            for(int i = 0; i < str.length(); ++i) {
                result += (long)((double)ALPHABET.indexOf(str.charAt(i)) * Math.pow(62.0D, (double)i));
            }

            return result;
        }
    }

    /**
     * @return 32位UUID
     * @author 郑明亮
     * @time 2017年2月5日 下午2:50:58
     * @description <p>
     * 获取唯一标识id字符串（32位）
     * <br>
     */
    public static String getUUID() {
        //生成一个唯一的36位UUID
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        //把"-"去掉，则剩下为32位数字和字母随机组合的唯一字符id
        id = id.replaceAll("-", "");
        return id;
    }

    /**
     * 获取随机User-Agent
     * @return
     */
    public static String getRandomUserAgent() {
        int index = RANDOM.nextInt(2);
        if(index %2 == 0){
            return USER_AGENT_APP[getNum(0,USER_AGENT_APP.length-1)];
        }
        return USER_AGENT_PC[RandomUtils.getNum(0,USER_AGENT_PC.length-1)];
    }
}
