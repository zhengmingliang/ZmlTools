/*
 * Created by 郑明亮 on 2021/5/11 17:39.
 */

//

package top.wys.utils;

/**
 * <ol>
 * Class处理工具类
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/5/11 17:39
 * @email mpro@vip.qq.com
 */
public class ClassUtils {
    /**
     * 返回要使用的默认 ClassLoader：通常是线程上下文 ClassLoader（如果可用）；加载 ClassUtils 类的 ClassLoader 将用作后备。
     * 如果您打算在显然更喜欢非空 ClassLoader 引用的场景中使用线程上下文 ClassLoader，
     * 请调用此方法：例如，用于类路径资源加载（但不一定适用于 Class.forName，它也接受空类加载器引用）
     * @return 默认类加载器（仅当系统类加载器不可访问时为空）
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                }
                catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }
}
