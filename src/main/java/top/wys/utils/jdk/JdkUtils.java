/**
 * Created by 郑明亮 on 2023/8/13 13:10.
 */
package top.wys.utils.jdk;

/**
 * <p> jdk工具类</p>
 *
 * @author 郑明亮
 * @time 2023/8/13 13:10
 * @since 1.4.3
 */
public class JdkUtils {
    public static final int JAVA_VERSION;
    public static final String JVM_NAME;
    static {
        int jvmVersion = -1;
        boolean openj9 = false, android = false, graal = false;
        JVM_NAME = System.getProperty("java.vm.name");
        try {
            String javaSpecVer = System.getProperty("java.specification.version");
            // android is 0.9
            if (javaSpecVer.startsWith("1.")) {
                javaSpecVer = javaSpecVer.substring(2);
            }
            if (javaSpecVer.indexOf('.') == -1) {
                jvmVersion = Integer.parseInt(javaSpecVer);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        JAVA_VERSION = jvmVersion;
    }
}
