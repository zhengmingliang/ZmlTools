package top.wys.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import top.wys.utils.entity.QQCollector;

import java.io.IOException;

public class JsoupUtilsTest extends TestCase {


    public void testQQCollector() throws IOException {
        String url = "https://sharechain.qq.com/c84877008c089cae4476936b633a38d6";

        String html = HttpUtils.get(url);
        Document document = Jsoup.parse(html);
        String script = document.selectFirst("script[type=text/javascript]").html();
        int index = script.indexOf("=");
        if(index > 0){
            String substring = script.substring(index + 1,script.length() - 1 );
            QQCollector qqCollector = JSON.parseObject(substring, QQCollector.class);
            QQCollector.ShareDataDTO shareData = qqCollector.getShareData();
            if(shareData != null){
                QQCollector.ShareDataDTO.CollectionDTO collection = shareData.getCollection();
                QQCollector.ShareDataDTO.CollectionDTO.AuthorDTO author = collection.getAuthor();

                System.out.println(author);
            }
        }

    }

}