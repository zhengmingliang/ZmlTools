package top.wys.utils;


import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
}