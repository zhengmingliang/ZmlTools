package top.wys.utils;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 郑明亮 on 2018/10/14 19:31.
 */
public class FileUtilsTest {
    @Test
    public void delete() throws IOException {
        String first = "C:\\Users\\zml\\Desktop\\reward.txt";
        String more = "C:\\Users\\zml\\Desktop\\hpc.txt";
        String prefix = "C:\\Users\\zml\\Desktop\\";
        String fileName = "fileauth.txt";

        Path path = Paths.get( more);

        List<String> lines = Files.readLines(path.toFile(), Charset.forName("UTF-8"));
        /*for (String line : lines) {
            System.out.println(line);
        }*/
        System.out.println(lines);

    }
    String getContentNIO(){
        String more = "C:\\Users\\zml\\Desktop\\hpc.txt";
        String prefix = "C:\\Users\\zml\\Desktop\\";
        String fileName = "fileauth.txt";

        Path path = Paths.get( more);

        List<String> lines = null;
        try {
            lines = Files.readLines(path.toFile(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toString();
    }



    public String getContentIO() {
        String more = "C:\\Users\\zml\\Desktop\\hpc.txt";
        return FileUtils.readTxtFile(new File(more), "UTF-8");
    }

    @Test
    public void readTest() {

        long start2 = System.currentTimeMillis();
        getContentNIO();
        long end2 = System.currentTimeMillis();
        System.out.println("2:"+(end2 - start2));


        long start1 = System.currentTimeMillis();
        getContentIO();
        long end1 = System.currentTimeMillis();
        System.out.println("1:"+(end1 - start1));
    }


    @Test
    public void testStore(){

        String filePathFromBase64 = DataUtils.getFilePathFromBase64("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","test.png");
        System.out.println(filePathFromBase64);
        String filePath = "C:\\Users\\zml\\Downloads\\AtomSetup-x64.exe";
        Path path = Paths.get(filePath);
        FileStore fileStore = null;
        try {
            fileStore = java.nio.file.Files.getFileStore(path);
            System.out.println(fileStore.name());
            System.out.println(fileStore.type());
            System.out.println(JSON.toJSONString(fileStore));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String fileExtension = Files.getFileExtension(filePath);
//        System.out.println(fileExtension);
//        String nameWithoutExtension = Files.getNameWithoutExtension(filePath);
//        System.out.println(nameWithoutExtension);
//        Files.
    }

    @Test
    public void writeTest() throws Exception {
        Stopwatch watch = Stopwatch.createStarted();
//        String read = Files.asCharSource(new File("C:\\softwares\\AshampooWinOptimizer 17\\automatic_protocol.txt"), Charset.forName("UTF-8")).read();
//        String read = Files.asCharSource(new File("G:\\test\\test1.txt"), Charset.forName("UTF-8")).read();
        File file = new File("G:\\test\\test1.txt");
        String read = FileUtils.readTxtFile(file, "UTF-8");
        System.out.println(watch.toString());
        watch.reset().start();
        for (int i = 0; i < 10; i++) {
            FileUtils.readTxtFile(file, "UTF-8");
//            FileUtils.writeFile("G:\\test\\test1.txt",read,true);
        }
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed/10);
        System.out.println("spead = " + spead);
    }

    @Test
    public void write1Test() throws Exception {
        Stopwatch watch = Stopwatch.createStarted();
//        String read = Files.asCharSource(new File("C:\\softwares\\AshampooWinOptimizer 17\\automatic_protocol.txt"), Charset.forName("UTF-8")).read();
//        String read = Files.asCharSource(new File("G:\\test\\test1.txt"), Charset.forName("UTF-8")).read();
        File file = new File("G:\\test\\test1.txt");
//        String read = FileUtils.readFile(file, "UTF-8");
        System.out.println(watch.toString());
        watch.reset().start();
        for (int i = 0; i < 10; i++) {
//            FileUtils.readFile(file, "UTF-8");
//            FileUtils.writeFile("G:\\test\\test1.txt",read,true);
        }
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed/10);
        System.out.println("spead = " + spead);
    }

    @Test
    public void fileCopy() throws Exception {
        writeTest();
        System.out.println("------");
        write1Test();

    }

    @Test
    public void fileRead() {
        File file = new File("G:\\test\\test1.txt");
        String read = FileUtils.readTxtFile(file, "UTF-8");
        System.out.println(read);
    }
    Stopwatch watch = Stopwatch.createUnstarted(); ;
    @Test
    public void fileReadCallback() throws Exception {

        System.out.println("sleep 3 s");
        TimeUnit.SECONDS.sleep(3);
        File file = new File("G:\\test\\test3.txt");
        watch.start();
        System.out.println(watch.toString());
        FileUtils.readLargeFile(file, "UTF-8",1000, new Callback() {
            long len = 0;

            @Override
            public void apply(String data) {

                len+=1000;
                System.out.println(len);
            }

            @Override
            public void getFirstLine(String firstLine) {

            }
        });

        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }


    @Test
    public void fileReadByLineCallback() throws Exception {
        Stopwatch watch = Stopwatch.createStarted(); ;
        File file = new File("G:\\test\\test1.txt");
        FileUtils.readLargeFileByLine(file, "UTF-8", new Callback() {
            long len = 0;

            @Override
            public void apply(String line) {
                String[] split = line.split("\t");

            }

            @Override
            public void getFirstLine(String firstLine) {

            }
        });

        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }

    @Test
    public void fileSize() throws Exception {
        File file = new File("G:\\test");
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
//        double spead = size / (elapsed);
//        System.out.println("spead = " + spead);
    }

    @Test
    public void byteBufferReader() throws IOException {
        File fff = new File("G:\\test\\test1.txt");
        int bufSize = 1024;
        byte[] bs = new byte[bufSize];
        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
        FileChannel channel = new RandomAccessFile(fff, "r").getChannel();
        while (channel.read(byteBuf) != -1) {
            int size = byteBuf.position();
            byteBuf.rewind();
            byteBuf.get(bs); // 把文件当字符串处理，直接打印做为一个例子。
            System.out.print(new String(bs, 0, size));
            byteBuf.clear();
        }
    }


    @Test
    public void largeFileIoTest() throws IOException {
        watch.start();
        File file = new File("G:\\test\\test.txt");

//        largeFileIO2("G:\\test\\test.txt","G:\\test\\test6.txt");
        java.nio.file.Files.copy(Paths.get("G:\\test\\test.txt"),Paths.get("G:\\test\\test7.txt"));

        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }

    void largeFileIO(String inputFile, String outputFile) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));

            BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);// 10M缓存
            FileWriter fw = new FileWriter(outputFile);
            while (in.ready()) {
                String line = in.readLine();
                fw.append(line).append("\r\n");
            }
            in.close();
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void largeFileIO2(String inputFile, String outputFile) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outputFile)));

             byte[] bytes = new byte[1024*50];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes,0,len);
            }
            bos.flush();
            bos.close();
            bis.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void randomFileReader() throws IOException {
        String path = "你要读的文件的路径";
        RandomAccessFile br = new RandomAccessFile(path, "r");// 这里rw看你了。要是之都就只写r
        String str = null, app = null;
        int i = 0;
        FileChannel channel = br.getChannel();
        long size = channel.size();
        channel.map(FileChannel.MapMode.READ_ONLY,0,size);

        while ((str = br.readLine()) != null) {
            i++;
            app = app + str;
            if (i >= 100) {// 假设读取100行
                i = 0;
                // 这里你先对这100行操作，然后继续读
                app = null;
            }
        }
        br.close();
    }


    @Test
    public void writeFileTest() throws IOException {

        String str = "world";
        java.nio.file.Files.write(Paths.get("G:\\test\\hello.txt"),str.getBytes());
    }


    @Test
    public void readTxtFile2() {
        watch.start();
        String splitStr = "\t";
//        File file = new File("C:\\Users\\zml\\Documents\\sql_bak\\east\\EAST_300102_YGB_O.txt");
        File file = new File("G:\\test\\EAST_300102_YGB_O.txt");
        FileUtils.readLargeFile(file, "UTF-8",500, new Callback() {

            int size = 0;
            String[] head;
            @Override
            public void apply(String data) {
                String[] lines = data.split("\r\n");
                for (String line : lines) {
                    String[] split = line.split(splitStr);
                    if(split.length < size){
                        String[] newArr = Arrays.copyOf(split, size);
                        System.err.println(JSON.toJSONString(newArr));
                        continue;
                    }
                    for (int i = 0; i < head.length; i++) {
//                    String format = String.format("%s : %s", head[i], split[i]);
//                    System.out.println(format);
                    }
                }

//                System.out.println("-------------------------\r\n");
            }

            @Override
            public void getFirstLine(String firstLine) {
                if (firstLine != null) {
                    head = firstLine.split(splitStr);
                    size = head.length;
                }
            }
        });

        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }
    int lineCount = 1;
    int errLineCount = 0;

    @Test
    public void readTxtFile() {

        watch.start();
        String splitStr = ",";
//        File file = new File("C:\\Users\\zml\\Documents\\sql_bak\\east\\EAST_300102_YGB_O.txt");// line: 8839
//        File file = new File("C:\\Users\\zml\\Documents\\sql_bak\\east\\EAST_300204_GRHQCKFHZ_O.txt"); //line: 2296563
        File file = new File("G:\\test\\EAST_300201_ZZHJQKMB_O.txt"); //line: 25037591

//        File file = new File("C:\\Users\\zml\\Desktop\\City.csv"); //line: 2296563
        FileUtils.readLargeFileByLine(file, "UTF-8", new Callback() {

            int size = 0;
            String[] head;

            @Override
            public void apply(String data) {
                data.split("@#@");
//               lineCount++;
                /* String[] split = data.split(splitStr);
                if(split.length < size){
                    String[] newArr = Arrays.copyOf(split, size);
                    System.err.println(JSON.toJSONString(newArr));
                    errLineCount++;
                    return;
                }

                for (int i = 0; i < head.length; i++) {

//                    String format = String.format("%s : %s", head[i], split[i]);
//                    System.out.println(format);
                }*/
//                System.out.println("-------------------------\r\n");
            }

            @Override
            public void getFirstLine(String firstLine) {
                if (firstLine != null) {
                    System.out.println(firstLine);
//                    head = firstLine.split(splitStr);
//                    size = head.length;
                }
            }
        });
        System.out.println("lineCount = " + lineCount);
        System.out.println("errLineCount = " + errLineCount);
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(file);
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }

    int count = 0;
    @Test
    public void nioLines() throws IOException {
//        Stream<String> lines = java.nio.file.Files.lines(Paths.get());
/*

        lines.map(line -> {
            count ++;
           return line.split("@#@");

        }).forEach(strings -> {

        });
*/
//        Stream<String> lines = java.nio.file.Files.lines(Paths.get(path));
        FileUtils.readLargeFileByLineNIO("G:\\test\\EAST_300201_ZZHJQKMB_O.txt", "UTF-8", new Callback() {
            @Override
            public void apply(String data) {
                String[] split = data.split("@#@");
                count++;
            }

            @Override
            public void getFirstLine(String firstLine) {
                System.out.println(firstLine);
            }
        });
        System.out.println(count);

//        callback.getFirstLine(first.isPresent() ? first.get() : "");
//        streamSupplier.get().forEach(strings -> {
//            callback.apply(strings);
//        });
//        System.out.println(count);
    }

    @Test
    public void cutFile() {
        watch.start();
        String sourceFile = "G:\\test\\test2.txt";
        FileUtils.cutFile(sourceFile,"G:\\test\\split",500 * 1024* 1024);
        System.out.println("lineCount = " + lineCount);
        System.out.println("errLineCount = " + errLineCount);
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(new File(sourceFile));
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }

    @Test
    public void cutFile1() {
        watch.start();
        //TODO 待优化，目前最大内存占用的大小和被拆分的文件大小一致
        String sourceFile = "G:\\test\\test2.txt";
//        FileUtils.cutFileByNIO(sourceFile,"UTF-8","G:\\test\\split\\3",1210770);
        System.out.println("lineCount = " + lineCount);
        System.out.println("errLineCount = " + errLineCount);
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(new File(sourceFile));
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }


 @Test
    public void cutFile2() {
        watch.start();
        String sourceFile = "G:\\test\\test2.txt";
        cutFile(sourceFile,"G:\\test\\split",500 * 1024 * 1024);
        System.out.println("lineCount = " + lineCount);
        System.out.println("errLineCount = " + errLineCount);
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(new File(sourceFile));
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size +" MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }



    private static void cutFile(String src, String endsrc, int num) {
        FileInputStream fis = null;
        File file = null;
        try {
            fis = new FileInputStream(src);
            file = new File(src);
            //创建规定大小的byte数组
            byte[] b = new byte[num];
            int len = 0;
            //name为以后的小文件命名做准备
            int name = 1;
            //遍历将大文件读入byte数组中，当byte数组读满后写入对应的小文件中
            while ((len = fis.read(b)) != -1) {
                //分别找到原大文件的文件名和文件类型，为下面的小文件命名做准备
                String name2 = file.getName();
                int lastIndexOf = name2.lastIndexOf(".");
                String substring = name2.substring(0, lastIndexOf);
                String substring2 = name2.substring(lastIndexOf, name2.length());
                FileOutputStream fos = new FileOutputStream(endsrc + "\\\\"+ substring + "-" + name + substring2);
                //将byte数组写入对应的小文件中
                fos.write(b, 0, len);
                //结束资源
                fos.close();
                name++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    //结束资源
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void rename() {
        File fodler = new File("D:\\bakup\\sunland");
        for (File file : fodler.listFiles()) {
            String oldName = file.getName();
            if(!oldName.contains("%3A")){
                continue;
            }
            String newName = oldName.replace("%3A", "");
            System.out.printf("oldName:%s ,newName: %s \n",oldName,newName);
            FileUtils.rename(file,newName,false);
        }

    }

    @Test
    public void reanme2() throws Exception {
        File fodler = new File("D:\\bakup\\sunlands_live");
        for (File file : fodler.listFiles()) {
            File[] courseInfos = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return "course_info".equals(pathname.getName()) || pathname.getName().length() == 32 || pathname.getName().contains(".mp4");
                }

            });
            if(courseInfos == null || courseInfos.length != 2){
                continue;
            }
            File courseInfo = null;
            File video = null;
            if(courseInfos[0].getName().length() == 32){
                video = courseInfos[0];
                courseInfo = courseInfos[1];
            }else{
                courseInfo = courseInfos[0];
                video = courseInfos[1];
            }
            String json = FileUtils.readTxtFile(courseInfo, "UTF-8");
            CourseInfoDTO courseInfoDTO = GsonTools.getBeanFromJson(json, CourseInfoDTO.class);
            String sName = courseInfoDTO.getRoomInfo().getSName();
            sName = sName.replaceAll("\\<","(").replaceAll("\\>",")");

            String oldName = video.getName();
            String targetName = sName + ".mp4";
            System.out.printf("oldName:%s ,newName: %s \n", oldName,targetName);
            FileUtils.rename(video,targetName,false);


            oldName = file.getName();
            targetName = sName;


            String targetFullPath = file.getParent() + File.separator + targetName;
            File targetFile = new File(targetFullPath);
            if(targetFile.exists()){
                long targetFileSize = FileUtils.getFileSizes(targetFile);
                long sourceFileSize = FileUtils.getFileSizes(file);
                if (targetFileSize < sourceFileSize) {
                    String newName = targetFile.getName() + "【精华版】";
                    System.out.printf("文件已存在，重命名：oldName:%s ,newName: %s \n", targetFile.getName(),newName);
                    FileUtils.rename(targetFile, newName,false);
                }else{
                    targetName += "【精华版】";
                }
            }

            System.out.printf("oldName:%s ,newName: %s \n", oldName,targetName);
                FileUtils.rename(file,targetName,false);



        }


    }

    @Test
    public void readLargeFile() throws Exception {
      File file = new File("D:\\bakup\\sunlands_live\\【数学】管理类联考(必修课1)");
        long fileSizes = FileUtils.getFileSizes(file);
        String size = DataUtils.getSize(fileSizes);
//        file.renameTo()
        System.out.println("size = " + size);
    }
}
