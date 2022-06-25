package top.wys.utils;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static top.wys.utils.StringUtils.delimitedListToStringArray;


/**
 * This class provides methods to create new file/directory
 */
public class FileUtils {


    private static final String FOLDER_SEPARATOR = "/";

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    private static final String TOP_PATH = "..";

    private static final String CURRENT_PATH = ".";

    private static final char EXTENSION_SEPARATOR = '.';

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 默认缓存区大小（默认50KB）
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 50;
    /**
     * 读取大文件时的默认缓存区大小（默认5M）
     */
    private static final int DEFAULT_LARGE_BUFFER_SIEZE = 5 * 1024 * 1024;
    /**
     * 默认读写文件编码
     */
    private static String DEFAULT_ENCODING  = "UTF-8";;

    /**
     * Enhancement of java.io.File#createNewFile()
     * Create the given file. If the parent directory  don't exists, we will create them all.
     *
     * @param file the file to be created
     * @return true if the named file does not exist and was successfully created; false if the named file already exists
     * @throws IOException
     * @see File#createNewFile
     */
    public static boolean createFile(File file) throws IOException {
        boolean flag = false;
        try {
            if (!file.exists()) {
                makeDir(file.getParentFile());
            }
            flag = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Enhancement of java.io.File#mkdir()
     * Create the given directory . If the parent folders don't exists, we will create them all.
     *
     * @param dir the directory to be created
     * @see File#mkdir()
     */
    public static void makeDir(File dir) {
        try {
            if (!dir.getParentFile().exists()) {
                makeDir(dir.getParentFile());
            }
            dir.mkdir();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @param path    文件路径及文件名称
     * @param content 要写入的内容
     * @param append  是否追加 新内容在原有内容中，{@code ture}追加   {@code false}不追加
     * @author 郑明亮
     * @time 2017年3月10日 上午10:30:44
     * @description <p> 向文本文件中写入内容或追加新内容,如果append为true则直接追加新内容,<br>
     * 如果append为false则覆盖原来的内容<br>  <br>
     */
    public static void writeFile(String path, String content, boolean append) {
        File writefile;
        FileOutputStream fw = null;
        try {
            writefile = new File(path);

            // 如果文本文件不存在则创建它
            // modify by zml 修正当传入的文件是相对路径时，直接获取parent会有空指针问题
            String parentPath = writefile.getAbsoluteFile().getParent();
            File parentFile = new File(parentPath);
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!writefile.exists()) {
                writefile.createNewFile();
                writefile = new File(path); // 重新实例化
            }

            fw = new FileOutputStream(writefile, append);
            log.debug("###content:{}", content);
            fw.write(content.getBytes());
            fw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.close(fw);
        }
    }

    /**
     * 复制文件
     *
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


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(outStream);
            IOUtils.close(inStream);
            IOUtils.close(in);
            IOUtils.close(out);
        }
    }

    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路径
     * @param fileName       :压缩后文件的名称
     * @return
     */
    @SuppressWarnings("resource")
    public static String fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;

        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        String zipName = "";

        if (sourceFile.exists() == false) {
            log.debug("待压缩的文件目录：{}不存在.", sourceFilePath);
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if (zipFile.exists()) {
                    log.debug("{}目录下存在名字为:{}.zip 打包文件.", zipFilePath, fileName);
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if (null == sourceFiles || sourceFiles.length < 1) {
                        log.debug("待压缩的文件目录：{} 里面不存在文件，无需压缩.", sourceFilePath);
                    } else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[DEFAULT_BUFFER_SIZE];
                        for (int i = 0; i < sourceFiles.length; i++) {
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            //fixme 这里需要对流关闭一下，不然可能会因为文件过大，导致内存溢出
                            bis = new BufferedInputStream(fis, DEFAULT_BUFFER_SIZE);
                            int read = 0;
                            while ((read = bis.read(bufs, 0, DEFAULT_BUFFER_SIZE)) != -1) {
                                zos.write(bufs, 0, read);
                            }
                        }
                    }
                }
                zipName = zipFile.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭流
                IOUtils.close(zos);
                IOUtils.close(bis);
                IOUtils.close(fis);
                IOUtils.close(fos);
            }
        }
        return zipName;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     * @throws FileNotFoundException
     */
    public static boolean deleteDir(File dir) throws FileNotFoundException {

        if (dir == null) {
            throw new FileNotFoundException();
        }

        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; children != null && i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

//        Files.delete();
        // 目录此时为空，可以删除
        return dir.delete();
    }


    /**
     * @param filePath 纯文本文件路径
     * @param encoding
     * @return
     */
    public static String readTxtFile(String filePath, String encoding) {
        return readTxtFile(new File(filePath), encoding);
    }

    /**
     * @param filePath 纯文本文件路径
     * @since 1.1.2
     * @return
     */
    public static String readTxtFile(String filePath) {
        return readTxtFile(new File(filePath), DEFAULT_ENCODING);
    }

    /**
     * @param file 纯文本文件路径
     * @since 1.1.2
     * @return
     */
    public static String readTxtFile(File file) {
        return readTxtFile(file, DEFAULT_ENCODING);
    }

    /**
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年3月28日 下午5:51:05
     * @description <p> 读取文件中的内容 </P>
     * @param fileName
     * @return
     */
    /**
     * 读取纯文本文件内容
     *
     * @param file
     * @param encoding 该文件编码 ，若输入null则自动判断文件编码
     * @return
     */
    public static String readTxtFile(File file, String encoding) {
        FileInputStream fis = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            String filePath = file.getAbsolutePath();
            encoding = encoding == null ? EncodingDetect.getJavaEncode(filePath) : encoding;
            log.debug(filePath);
            StringBuilder sb = new StringBuilder();
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                fis = new FileInputStream(file);
                read = new InputStreamReader(fis, encoding);// 考虑到编码格式
                bufferedReader = new BufferedReader(read, DEFAULT_LARGE_BUFFER_SIEZE);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt).append("\r\n");
                }

            } else {
                log.error("找不到指定的文件");
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("读取文件内容出错", e);
        } finally {
            IOUtils.close(read);
            IOUtils.close(fis);
            IOUtils.close(bufferedReader);
        }
        return "";
    }

    public static void readLargeFile(File file, String encoding, long maxLinePerTime, Callback callback) {

        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
             // 用5M的缓冲读取文本文件
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis, encoding), DEFAULT_LARGE_BUFFER_SIEZE);) {
            String line = reader.readLine();
            callback.getFirstLine(line);
            int lineCount = 0;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\r\n");
                lineCount++;
                if (lineCount >= maxLinePerTime) {
                    callback.apply(sb.toString());
                    lineCount = 0;
                    sb = new StringBuilder();
                }
            }
            if (sb.length() > 0) {
                callback.apply(sb.toString());
                sb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readLargeFileByLine(File file, String encoding, Callback callback) {

        try(FileInputStream fis = new FileInputStream(file);
            // 用5M的缓冲读取文本文件
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, encoding), DEFAULT_LARGE_BUFFER_SIEZE)) {

            String line = reader.readLine();
            callback.getFirstLine(line);
            while ((line = reader.readLine()) != null) {
                callback.apply(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readLargeFileByLineNIO(String path, String encoding, Callback callback) {

        try {
            Optional<String> first = Files.lines(Paths.get(path), Charset.forName(encoding)).findFirst();
            Stream<String> lines = Files.lines(Paths.get(path), Charset.forName(encoding));

            callback.getFirstLine(first.isPresent() ? first.get() : "");

            lines.forEach(strings -> {
                callback.apply(strings);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public static void readLargeFile(File file, String encoding, Callback callback) {

        try {
            long counts = 0;
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            int offset = 0;
            byte[] buffer = new byte[2048];

            while((offset = fc.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                byteBuffer.get(buffer,0,byteBuffer.limit());
//                if(offset <= 10000){
                    String str = new String(buffer, "UTF-8");
//                    System.out.println(str);

//                }
                counts = counts + offset;
                byteBuffer.clear();
            }
            fc.close();
            fis.close();
//            return counts;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 按指定文件大小切割文件（可能会存在字符被切割的问题）
     *
     * @param sourceFileName 需要切割的源文件
     * @param targetFolder   切割的目标文件放置的文件夹
     * @param size           每个文件的大小（单位：字节）
     */
    public static void cutFile(String sourceFileName, String targetFolder, int size) {
        BufferedOutputStream bos = null;
        File targetFolderFile = new File(targetFolder);
        File sourceFile = new File(sourceFileName);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));) {
            String fullName = sourceFile.getName();
            int beginIndex = fullName.indexOf(".");
            if (beginIndex == -1) {
                beginIndex = fullName.length() - 1;
            }
            //源文件名称（不含后缀名）
            String fileName = fullName.substring(0, beginIndex);
            // 源文件后缀名
            String suffixName = fullName.substring(beginIndex, fullName.length());

            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            int count = 0;
            int len = 0;
            int suffixCount = 0;
            while ((len = bis.read(bytes)) != -1) {
                if (bos == null) {
                    suffixCount++;
                    String targetFileName = String.format("%s%s%s_%s%s", targetFolderFile.getAbsolutePath(), File.separator,
                            fileName, suffixCount, suffixName);
                    bos = new BufferedOutputStream(new FileOutputStream(new File(targetFileName)));
                }
                bos.write(bytes, 0, len);
                count += len;

                // 分次写入文件
                if (count >= size) {
                    bos.flush();
                    bos.close();
                    bos = null;
                    count = 0;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.flush(bos);
            IOUtils.close(bos);
        }
    }


    /**
     * 将源文件拆分成多个子文件（根据行数）
     *
     * @param sourceFileName 需要拆分的源文件全路径文件名
     * @param encoding       源文件编码
     * @param targetFolder   拆分的文件存储路径
     * @param maxLine        每个拆分的文件最多多少行
     * @description 该方法在拆分大文件（几个G）时最多需要消耗1G多的内存，可使用 top.wys.utils.FileUtils#cutFileByNIO(java.lang.String, java.lang.String, java.lang.String, long)方法拆分大文件
     * @see FileUtils#cutFileByLine(String, String, String, long)
     */
    @Deprecated
    public static void cutFile(String sourceFileName, String encoding, String targetFolder, long maxLine) {
        FileInputStream fis = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {

            File targetFolderFile = new File(targetFolder);
            if (!targetFolderFile.exists()) {
                targetFolderFile.mkdirs();
            }
            File sourceFile = new File(sourceFileName);
            String fullName = sourceFile.getName();
            int beginIndex = fullName.indexOf('.');
            if (beginIndex == -1) {
                beginIndex = fullName.length() - 1;
            }
            //源文件名称（不含后缀名）
            String fileName = fullName.substring(0, beginIndex);
            // 源文件后缀名
            String suffixName = fullName.substring(beginIndex, fullName.length());

            fis = new FileInputStream(sourceFile);
            reader = new BufferedReader(new InputStreamReader(fis, encoding), DEFAULT_LARGE_BUFFER_SIEZE);// 用5M的缓冲读取文本文件
            String line = "";
            writer = null;
            int lineCount = 0;
            int suffixCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (writer == null) {
                    // 文件后缀数量累计
                    suffixCount++;
                    // 拆分的新文件名称
                    String targetFileName = String.format("%s%s%s_%s%s", targetFolderFile.getAbsolutePath(), File.separator,
                            fileName, suffixCount, suffixName);
                    writer = new BufferedWriter(new FileWriter(new File(targetFileName)), DEFAULT_LARGE_BUFFER_SIEZE);
                }
                writer.write(line + "\r\n");

                // 当总行数大于等于设定最大行时，写入文件，并重新初始化
                if (lineCount >= maxLine) {
                    writer.flush();
                    writer.close();
                    writer = null;
                    // 重新初始化累计和
                    lineCount = 0;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.flush(writer);
            IOUtils.close(writer);
            IOUtils.close(reader);
            IOUtils.close(fis);
        }
    }


    /**
     * 将源文件拆分成多个子文件（根据行数）
     *
     * @param sourceFileName 需要拆分的源文件全路径文件名
     * @param encoding       源文件编码
     * @param targetFolder   拆分的文件存储路径
     * @param maxLine        每个拆分的文件最多多少行
     * @description 该方法在拆分大文件（5个G）时最多需要消耗800M的内存，后续稳定在几十到200M内存之间
     * @see FileUtils#cutFile(String, String, String, long)
     */
    public static void cutFileByLine(String sourceFileName, String encoding, String targetFolder, long maxLine) {
        try {
            File targetFolderFile = new File(targetFolder);
            if (!targetFolderFile.exists()) {
                targetFolderFile.mkdirs();
            }
            File sourceFile = new File(sourceFileName);
            String fullName = sourceFile.getName();
            int beginIndex = fullName.indexOf('.');
            if (beginIndex == -1) {
                beginIndex = fullName.length() - 1;
            }
            //源文件名称（不含后缀名）
            String fileName = fullName.substring(0, beginIndex);
            // 源文件后缀名
            String suffixName = fullName.substring(beginIndex);

            final BufferedWriter[] writer = {null};
            // 行计数器
            final int[] lineCount = {0};
            //拆分的目标文件数量计数器
            final int[] suffixCount = {0};
            //拆分的目标文件名称
            final String[] targetFileName = {""};

            Files.lines(Paths.get(sourceFileName), Charset.forName(encoding)).forEach(
                    line -> {
                        try {
                            //1. 需要写入新的文件时，对相关参数进行初始化
                            if (lineCount[0] == 0) {
                                // 文件后缀数量累计
                                suffixCount[0]++;
                                // 拆分的新文件名称
                                targetFileName[0] = String.format("%s%s%s_%s%s", targetFolderFile.getAbsolutePath(), File.separator,
                                        fileName, suffixCount[0], suffixName);
                                File file = new File(targetFileName[0]);
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                writer[0] = Files.newBufferedWriter(Paths.get(targetFileName[0]), Charset.forName(encoding));

                            }
                            //写入缓冲区
                            writer[0].write(line + "\r\n");
                            lineCount[0]++;

                            //写入文件行数达到要求行数时，数据刷盘，并重新计数
                            if (lineCount[0] >= maxLine) {
                                // 从缓冲区刷盘，并关闭释放io资源
                                writer[0].flush();
                                writer[0].close();
                                writer[0] = null;
                                // 重新初始化累计和
                                lineCount[0] = 0;

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param f
     * @return
     * @throws Exception
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年4月13日 下午7:21:57
     * @description 获取指定文件夹大小
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
        } else {
            log.error("{}不存在", f.getAbsolutePath());
        }

        return size;
    }

    /**
     * @param file
     * @return
     * @author 郑明亮
     * @email zhengmingliang911@gmail.com
     * @time 2017年4月13日 下午7:21:43
     * @description <p>获取指定文件大小    </p>
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists() && file.isFile()) {
            return file.length();
        } else {
//            file.createNewFile();
            log.error("{}不存在,已创建新文件", file.getAbsolutePath());
        }
        return size;
    }


    /**
     * 修改文件或目录的文件名，不变更路径，只是简单修改文件名<br>
     *
     *
     * <pre>
     * FileUtil.rename(file, "aaa.jpg", false) xx/xx.png =》xx/aaa.jpg
     * </pre>
     *
     * @param file 被修改的文件
     * @param newName 新的文件名，包括扩展名
     * @param isOverride 是否覆盖目标文件
     * @return 目标文件
     * @since 1.1.2
     */
    public static File rename(File file, String newName, boolean isOverride) {

        final Path path = file.toPath();
        final CopyOption[] options = isOverride ? new CopyOption[] { StandardCopyOption.REPLACE_EXISTING } : new CopyOption[] {};
        try {
            return Files.move(path, path.resolveSibling(newName), options).toFile();
        } catch (IOException e) {
            log.error("rename异常",e);
        }
        return null;
    }

    /**
     * 获取文件Content-Type(Mime-Type)
     * @param filePath
     * @return
     */
    public static String getContentType(String filePath){
        String contentType = "application/octet-stream";
        Path path = Paths.get(filePath);
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File content type is : " + contentType);
        return contentType;
    }

    /**
     * 获取文件Content-Type(Mime-Type)
     * @param file
     * @return
     */
    public static String getContentType(File file){
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentType;
    }


    /**
     * 通过抑制像 "../" 和 "." 这样的序列来规范化路径
     * <p>结果便于路径比较。对于其他用途，请注意 Windows 分隔符 ("\") 被简单的斜杠替换
     * @param path 原始路径
     * @return 规范化后的路径
     */
    public static String cleanPath(String path) {
        if (path == null) {
            return null;
        }
        String pathToUse = StringUtils.replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

        // Strip prefix from path to analyze, to not treat it as part of the
        // first path element. This is necessary to correctly parse paths like
        // "file:core/../core/io/Resource.class", where the ".." should just
        // strip the first "core" directory while keeping the "file:" prefix.
        int prefixIndex = pathToUse.indexOf(":");
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            if (prefix.contains("/")) {
                prefix = "";
            }
            else {
                pathToUse = pathToUse.substring(prefixIndex + 1);
            }
        }
        if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
            prefix = prefix + FOLDER_SEPARATOR;
            pathToUse = pathToUse.substring(1);
        }

        String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
        List<String> pathElements = new LinkedList<String>();
        int tops = 0;

        for (int i = pathArray.length - 1; i >= 0; i--) {
            String element = pathArray[i];
            if (CURRENT_PATH.equals(element)) {
                // Points to current directory - drop it.
            }
            else if (TOP_PATH.equals(element)) {
                // Registering top path found.
                tops++;
            }
            else {
                if (tops > 0) {
                    // Merging path element with element corresponding to top path.
                    tops--;
                }
                else {
                    // Normal path element found.
                    pathElements.add(0, element);
                }
            }
        }

        // Remaining top paths need to be retained.
        for (int i = 0; i < tops; i++) {
            pathElements.add(0, TOP_PATH);
        }

        return prefix + StringUtils.collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
    }

    /**
     * @param url 网络请求
     * @return 文件名称
     * @throws IOException 网络超时等异常
     * @author 郑明亮
     * @time 2017年3月19日 下午4:35:13
     * @description <p>获取 从网络请求中请求到的文件名称  <br>
     */
    public static String getFileNameFromHttp(String url) throws IOException {
        String fileName = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String disposition = response.header("Content-Disposition");
        if (disposition != null) {
            fileName = disposition.substring(disposition.indexOf('\"'), disposition.lastIndexOf('\"'));
        }
        //如果从Head中获取失败，则从URL中进行截取文件名称
        return fileName == null ? getFileNameFromUrl(url) : fileName;

    }

    /**
     * @param head OkHttp3  Header
     * @return 文件名称
     * @author 郑明亮
     * @time 2017年3月19日 下午4:35:13
     * @description <p>从网络请求中获取请求到的文件名称   <br>
     */
    public static String getFileNameFromHttp(Headers head){
        log.info("Header中的值:{}" , head);
        String fileName = null;
        String disposition = head.get("Content-Disposition");
        if (StringUtils.isNotEmpty(disposition)) {

            disposition = disposition.replaceAll("\"", "");
            fileName = disposition.substring(disposition.indexOf('=') + 1);
        }
        log.info("从Header中获取到的文件名称为：{}", fileName);
        return fileName;

    }

    /**
     * @param url 从http URL中截取文件名称
     * @return
     * @throws UnsupportedEncodingException 解码异常
     * @author 郑明亮
     * @time 2017年3月19日 下午4:41:50
     * @description <p> 从URL中截取文件名称  <br>
     */
    public static String getFileNameFromUrl(String url){
        String fileName = "";
        //如果URL结尾不是文件名，而是相关参数，则截取?前的内容
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf('?'));
        }

        //对URL进行解码处理
        try {
            fileName = URLDecoder.decode(url.substring(url.lastIndexOf('/') + 1), "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("从URL中截取到的文件名：{}", fileName);
        return fileName;
    }

    public static String getFileNameFromPath(String path){
        if (path.startsWith("http")) {
            return getFileNameFromUrl(path);
        }
        File file = new File(path);
        return file.getName();
    }
}
