package top.wys.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 郑明亮
 * @time 2017年6月21日 上午8:55:18
 */
public class AutoLoadProperties implements Runnable {
	private static Logger log = LoggerFactory.getLogger(AutoLoadProperties.class);
	  
    private Map<String,Long> configFileModifyDate = new HashMap<String, Long>();  
    /** 
     * 系统配置文件，包含系统classpath目录下config*.properties中的内容，5秒钟检查一次，如果有变化自动重新加载。 
     */  
    public static final  Map<String,String> SYSTEM_CONFIG = new HashMap<String,String>();  
  
    //配置文件路径    
    private static String defaultPropFileName = "/resources";    
  
  
    /** 
     * 系统配置文件监测，每5秒检测一次，如果配置文件有变化，则重新加载。 
     */  
	@Override
	public void run() {
		 int checkDely= 5*1000 ;     // 配置文件自动检查间隔；  
	        int beginDely = 60*1000;    // 1分钟后运行配置文件自动检查功能。  
	        try {  
	            Thread.sleep(beginDely);          
	            log.info("启动 配置文件检查 线程，当前检测频率："+checkDely);  
	        } catch (InterruptedException e1) {  
	            e1.printStackTrace();  
	        }  
	        while(true){  
	            try {  
	                this.loadAllConfigFiles();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	  
	            try {  
	                Thread.currentThread();  
	                Thread.sleep(checkDely);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }  

	}
	 private void loadAllConfigFiles(){  
		
	        String tempPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();//this.getClass().g; 
	        File fileDir = new File(tempPath);    
	  
	        fileDir.listFiles(new FileFilter() {  
	            @Override  
	            public boolean accept(File file) {  
	                try {  
	                    String name = file.getName();  
	                    String fullPath = file.getAbsolutePath();  
	  
	                    if(name.matches("^sys.*\\.properties$")){//properties  
	                        Long value = configFileModifyDate.get(fullPath);  
	                        if(value==null || value.longValue()!=file.lastModified()){  
	                            log.info("加载配置文件："+file);  
	                            loadPropertieFile(file);  
	                            configFileModifyDate.put(fullPath,file.lastModified());  
	                        }  
	                    }  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	  
	                return false;  
	            }  
	        });  
	  
	    }  
	 
	 /**
	 * @author 郑明亮
	 * @time 2017年6月21日 上午8:58:01
	 * @param configFile 文件
	 */
	private void loadPropertieFile(File configFile) {  
	        try {  
	            Properties properties = PropertiesLoaderUtils.loadProperties(new FileSystemResource(configFile));  
	            for(String key:properties.stringPropertyNames()){  
	                String value=properties.getProperty(key);  
	  
	                log.info("load property:"+ key+"->"+value);  
	                SYSTEM_CONFIG.put(key,value);  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	 public static void main(String[] args) {  
	        AutoLoadProperties sys = new AutoLoadProperties();  
	        sys.run();
//		 String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		 System.out.println(path);
		 
//		 for (int i = 0; i < 1000; i++) {
//			 try {
//			String json =	HttpUtils.getHttpData("http://foooooot.com/api/v3/huodong/list/?page_num=2&page_size=10");
//			System.out.println(json);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("报错的index："+i);
//			}
//			
//		}
	    }  
}
