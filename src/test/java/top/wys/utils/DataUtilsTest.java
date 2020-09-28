package top.wys.utils;

import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
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
}