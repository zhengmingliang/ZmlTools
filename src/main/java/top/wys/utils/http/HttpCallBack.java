/*
 * Created by 郑明亮 on 2020/11/4 11:27.
 */

//

package top.wys.utils.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2020/11/4 11:27
 * @email mpro@vip.qq.com
 */
public interface HttpCallBack<T> {
    void onFailure(Call call, IOException e);

    void onResponse(Call call, Response response,T result) throws IOException;
}
