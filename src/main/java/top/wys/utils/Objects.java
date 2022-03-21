/**
 * Created by 郑明亮 on 2022/3/20 11:56.
 */
package top.wys.utils;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * <p> 对象工具类</p>
 *
 * @author 郑明亮
 * @since  2.0.0
 * @time 2022/3/20 11:56
 */
public class Objects {
    /**
     * 打开给定对象，该对象可能是被 {@link Optional} 包裹的对象
     *
     * @param obj 候选对象
     * @return 被 {@code Optional} 包裹的对象; 如果 {@code Optional} 为空, 或为null时，返回{@code null}
     *
     * @since 5.0
     */
    @Nullable
    public static Object unwrapOptional(@Nullable Object obj) {
        if (obj instanceof Optional) {
            Optional<?> optional = (Optional<?>) obj;
            if (!optional.isPresent()) {
                return null;
            }
            Object result = optional.get();
            Assert.isTrue(!(result instanceof Optional), "Multi-level Optional usage not supported");
            return result;
        }
        return obj;
    }
}
