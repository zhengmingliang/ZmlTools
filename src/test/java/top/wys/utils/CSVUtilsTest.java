package top.wys.utils;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by 郑明亮 on 2019/11/13 17:25.
 */
public class CSVUtilsTest {
    Stopwatch watch = Stopwatch.createStarted();

    @Test
    public void getCSVParser() throws IOException {
        int lineCount = 1;
        int errLineCount = 0;
        watch.start();
        String name = "C:\\Users\\zml\\Desktop\\City.csv";
        CSVParser parse = CSVUtils.getCSVParser(name);

        for (CSVRecord line : parse) {
            System.out.println(line.toString());
            /*for (String headerName : parse.getHeaderNames()) {
                if (StringUtils.isEmpty(headerName)) {
                    continue;
                }
                String value = line.get(headerName);
                String format = String.format("%s : %s", headerName, value);
                System.out.println(format);
            }*/
            System.out.println("------------------");
        }
        parse.close();
        System.out.println("lineCount = " + lineCount);
        System.out.println("errLineCount = " + errLineCount);
        long elapsed = watch.elapsed(TimeUnit.SECONDS);
        System.out.println(watch);
        long fileSize = FileUtils.getFileSize(new File(name));
        double size = fileSize / 1024.0 / 1024;
        System.out.println(size + " MB");
        double spead = size / (elapsed);
        System.out.println("spead = " + spead);
    }


    @Test
    public void write() {
        Random random = new Random();
        List<Object> list = Lists.newArrayList();
        List<Object> itemList = Lists.newArrayList();
        String[] header = {"name","sex","company","road","telephone"};
        Map person = null;
        for (int i = 0; i < 10; i++) {
             person = RandomUtils.getRandomPerson();
//            itemList.add(person.get("name"));
//            itemList.add(person.get("sex"));
//            itemList.add(person.get("company"));
//            itemList.add(person.get("road"));
//            itemList.add(person.get("telephone"));
           list.add(person.values());
        }
        CSVUtils.write(new File("G:\\test\\random.csv"),list,header);
    }
}
