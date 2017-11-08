package top.wys.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * This class provides methods to create new file/directory
 *
 */
public class FileUtils {
	
	/**
	 * Enhancement of java.io.File#createNewFile()
	 * Create the given file. If the parent directory  don't exists, we will create them all.
	 * @param file the file to be created
	 * @return true if the named file does not exist and was successfully created; false if the named file already exists
	 * @see java.io.File#createNewFile
	 * @throws IOException
	 */
	public static boolean createFile(File file) throws IOException {
		if(! file.exists()) {
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}
	
	/**
	 * Enhancement of java.io.File#mkdir()
	 * Create the given directory . If the parent folders don't exists, we will create them all.
	 * @see java.io.File#mkdir()
	 * @param dir the directory to be created
	 */
	public static void makeDir(File dir) {
		if(! dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	
    /**
     * @author 郑明亮
     * @Time 2017年3月10日 上午10:30:44
     * @Description <p> 向文本文件中写入内容或追加新内容,如果append为true则直接追加新内容,<br>
     * 如果append为false则覆盖原来的内容<br>  </p>
     * @param path 文件路径及文件名称
     * @param content 要写入的内容
     * @param append 是否追加 新内容在原有内容中，{@code ture}追加   {@code false}不追加
     */
    public static void writeFile(String path, String content,boolean append) {
        File writefile;
        try {
            writefile = new File(path);

            // 如果文本文件不存在则创建它
            if (writefile.exists() == false) {
                writefile.createNewFile();
                writefile = new File(path); // 重新实例化
            }

            FileOutputStream fw = new FileOutputStream(writefile,true);
            System.out.println("###content:" + content);
            fw.write(content.getBytes());
            fw.flush();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
	
    /**
     * 复制文件
     * @param source
     * @param target
     */
    public static void nioTransferCopyFile(File source, File target) {  
        FileChannel in = null;  
        FileChannel out = null;  
        FileInputStream inStream = null;  
        FileOutputStream outStream = null;  
        try {  
            inStream = new FileInputStream(source);  
            outStream = new FileOutputStream(target);  
            in = inStream.getChannel();  
            out = outStream.getChannel();  
          
            in.transferTo(0, in.size(), out);  
           
            inStream.close();
            outStream.close(); 
            in.close();  
        	out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
        	  
        }  
    }  
    
    /** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径 
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @return 
     */  
    @SuppressWarnings("resource")
	public static String fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
//        boolean flag = false;  
        File sourceFile = new File(sourceFilePath); 
        FileInputStream fis = null;
        
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
        String zipName = "";  
        
        if(sourceFile.exists() == false){  
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");
        }else{  
            try {  
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile.exists()){  
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
                }else{  
                    File[] sourceFiles = sourceFile.listFiles();
                    if(null == sourceFiles || sourceFiles.length<1){  
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    }else{  
                        fos = new FileOutputStream(zipFile);  
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                        byte[] bufs = new byte[1024*10];  
                        for(int i=0;i<sourceFiles.length;i++){  
                            //创建ZIP实体，并添加进压缩包  
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
                            zos.putNextEntry(zipEntry);  
                            //读取待压缩的文件并写进压缩包里  
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024*10);  
                            int read = 0;  
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                                zos.write(bufs,0,read);  
                            }  
                        }  
//                        flag = true;  
                    }  
                }  
                zipName = zipFile.getName();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } finally{  
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                    if(null != zos) zos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return zipName;  
    }  
    
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     * @throws FileNotFoundException 
     */
    public static boolean deleteDir(File dir) throws FileNotFoundException {
    	
    	if (dir == null) {
			throw new FileNotFoundException();
		}
    	
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; children !=null && i <children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
	public static void main(String args[]) {
		String fName =" D:\\slient\\a\\2016070500000001.zip ";  
		  
        File tempFile =new File( fName.trim());  
        String fileName = tempFile.getName();  
        System.out.println("fileName = " + fileName);  
		
		
		/*String filePath = "D:/slient/a/b/g.xml";
		String filePath1  = "D:/slient/a/b/g.jpg";
		String filePath2  = "D:/test.jpg";
		File file = new File(filePath);
		File file1 = new File(filePath1);
		File file2 = new File(filePath2);
		try {
//			System.out.println("file.exists()? " + file.exists());
			boolean created = createFile(file);
//			
			writeFile(filePath,"11");
//			
//			System.out.println(created?"File created":"File exists, not created.");
//			System.out.println("file.exists()? " + file.exists());
			
			
			nioTransferCopyFile(file2,file1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
//		String sourceFilePath = "D:\\var\\image\\1";  
//        String zipFilePath = "D:\\var\\image";  
//        String fileName = "12700153file";  
//        boolean flag = fileToZip(sourceFilePath, zipFilePath, fileName);  
//        if(flag){  
//            System.out.println("文件打包成功!");  
//        }else{  
//            System.out.println("文件打包失败!");  
//        }  
		
//		String filePath = "D:\\slient\\a\\QLCXD20160629LYGJ00000000";
//		deleteDir(new File(filePath));
		
//		String dirPath = Constants.IMAGE_TRANS_FOLDER + "QLCXD20160707" + "/JPG";
//		
//		FileUtils.deleteDir(new File(dirPath));
	}

	/**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年3月28日 下午5:51:05
	 * @Description <p> 读取文件中的内容 </P>
	 * @param fileName
	 * @return
	 */
	/**读取纯文本文件内容
     * @param filePath  纯文本文件路径
     * @param encoding 该文件编码 ，若输入null则自动判断文件编码
     * @return
     */
    public static String readTxtFile(String filePath,String encoding) {
    	try {
    		encoding =  encoding==null?EncodingDetect.getJavaEncode(filePath):encoding;
    		File file = new File(filePath);
    		System.out.println(file.getAbsolutePath());
    		String str = "";
    		if (file.isFile() && file.exists()) { // 判断文件是否存在
    			InputStreamReader read = new InputStreamReader(
    					new FileInputStream(file), encoding);// 考虑到编码格式
    			BufferedReader bufferedReader = new BufferedReader(read);
    			String lineTxt = null;
    			while ((lineTxt = bufferedReader.readLine()) != null) {
//    				System.out.println(lineTxt);
    				str += lineTxt + "\r\n";
    			}
    			read.close();
    		} else {
    			System.out.println("找不到指定的文件");
    		}
    		return str;
    	} catch (Exception e) {
    		System.out.println("读取文件内容出错");
    		e.printStackTrace();
    	}
    	return "";
    }

    /**
     * @author 郑明亮
     * @Email zhengmingliang911@gmail.com
     * @Time 2017年4月13日 下午7:21:57
     * @Description <p>获取指定文件夹大小  </P>
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSizes(File f) throws Exception {  
        long size = 0;  
        if (f == null) {
			throw new UnsupportedOperationException("传入file不能为空");
		}
        if (f.isFile()) {
			return getFileSize(f);
		}
        if (f.exists()) {
        	File flist[] = f.listFiles();  
            for (int i = 0; flist != null && i < flist.length; i++) {  
                if (flist[i].isDirectory()) {  
                    size = size + getFileSizes(flist[i]);  
                } else {  
                    size = size + getFileSize(flist[i]);  
                }  
            }  
		}else{
			System.err.println(f.getAbsolutePath()+"不存在");
//			throw new UnavailableException(f.getAbsolutePath()+"不存在");
		}
        
        return size;  
    }  
    
    /**
     * @author 郑明亮
     * @Email zhengmingliang911@gmail.com
     * @Time 2017年4月13日 下午7:21:43
     * @Description <p>获取指定文件大小    </P>
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {  
        long size = 0;  
        if (file.exists()) {  
            try(FileInputStream fis = new FileInputStream(file); ){
				size = fis.available();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        } else {  
            file.createNewFile();  
           System.err.println(file.getAbsolutePath()+"不存在"); 
        }  
        return size;  
    }  
}
