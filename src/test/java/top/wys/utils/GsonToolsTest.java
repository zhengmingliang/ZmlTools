package top.wys.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.Map;

/**
 * Created by 郑明亮 on 2020/4/3 14:19.
 */

//

public class GsonToolsTest {

    @Test
    public void getMapFromJson() {
        String json = "{\"age\":\"21\",\"height:\":187,\"name\":\"zml\"}";
        // 使用gson默认typeAdapter
        Map<String, Object> maps = new Gson().fromJson(json,new TypeToken<Map<String, Object>>() {
        }.getType());
        System.out.println("maps = " + JSON.toJSONString(maps,true));

        // 使用自定义typeAdapter
        Map<String, Object> mapBean = GsonTools.getMapFromJson(json);
        System.out.println("mapBean = " + JSON.toJSONString(mapBean,true));

        // 使用fastjson
        Map<Object, Object> jsonToMap = FastJsonTools.createJsonToMap(json);
        System.out.println("jsonToMap = " + JSON.toJSONString(jsonToMap,true));
    }
}
