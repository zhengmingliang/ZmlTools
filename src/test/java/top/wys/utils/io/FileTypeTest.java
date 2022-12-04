package top.wys.utils.io;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.wys.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 郑明亮 on 2022/4/23 18:05.
 */
public class FileTypeTest extends TestCase {
    public void testDrawlAllTypes() throws IOException {
        String url = "https://mimetype.io/all-types/";
        String result = HttpUtils.get(url);
        Document document = Jsoup.parse(result);
        Elements main = document.select("#gatsby-focus-wrapper > main > div.all-types__Container-ct8q6r-0.bYvOUx > div > div.right");
        Elements mimeTypes = main.select(".mimetype");
        List dataList = new ArrayList();
        for (Element mimeType : mimeTypes) {
            String title = mimeType.select(".title").text();
            Elements extensions = mimeType.select(".extensions span");
            List<String> extensionList = extensions.stream().map(ext -> ext.text()).collect(Collectors.toList());
            String description = mimeType.select(".description").text();
            Map<String, Object> data = new HashMap<>();
            data.put("mimeType",title);
            data.put("extensions",extensionList);
            data.put("description",description);
            dataList.add(data);
        }
        System.out.println(JSON.toJSONString(dataList,true));
        System.out.println(dataList.size());
    }
}