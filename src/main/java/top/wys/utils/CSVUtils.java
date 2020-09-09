package top.wys.utils;
/**
 * Created by 郑明亮 on 2019/11/13 17:09.
 */

import com.google.common.collect.Lists;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  2019/11/13 17:09 <br>
 *
 * </p>
 *
 * @author 郑明亮
 * @version 1.0
 */
@Slf4j
public class CSVUtils {

    private static final int DEFAULT_BUFFERED_SIZE = 1024 * 8;
    public final static CSVFormat DEFAULT_CSVFORMAT = CSVFormat.ORACLE
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withAllowMissingColumnNames()
            .withIgnoreEmptyLines()
            .withTrim();
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static CSVParser getCSVParser(String fileName, CSVFormat csvFormat) {
        return getCSVParser(new File(fileName), csvFormat);
    }

    public static CSVParser getCSVParser(File file) {
        return getCSVParser(file, DEFAULT_CSVFORMAT);
    }

    public static CSVParser getCSVParser(File file, String encoding) {
        return getCSVParser(file, encoding, DEFAULT_CSVFORMAT);
    }

    public static CSVParser getCSVParser(String fileName) {
        return getCSVParser(new File(fileName), DEFAULT_CSVFORMAT);
    }

    public static CSVParser getCSVParser(String fileName, String encoding) {
        return getCSVParser(new File(fileName), encoding, DEFAULT_CSVFORMAT);
    }

    public static CSVParser getCSVParser(File file, CSVFormat csvFormat) {
        return getCSVParser(file, DEFAULT_ENCODING, csvFormat);
    }

    public static CSVParser getCSVParser(File file, String charsetName, CSVFormat csvFormat) {
        CSVParser parse = null;
        try {
            FileInputStream fis = new FileInputStream(file);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, charsetName), DEFAULT_BUFFERED_SIZE);

            parse = new CSVParser(bufferedReader, csvFormat);
        } catch (IOException e) {
            log.error("parse csv error", e);
        }
        return parse;
    }


    public static CSVPrinter getCSVPrinter(File outputFile, CSVFormat csvFormat) {
        return getCSVPrinter(outputFile, DEFAULT_ENCODING, csvFormat);
    }

    public static CSVPrinter getCSVPrinter(File outputFile, String encoding, CSVFormat csvFormat) {
        CSVPrinter csvPrinter = null;
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos, Charset.forName(encoding)), DEFAULT_BUFFERED_SIZE);

            csvPrinter = new CSVPrinter(bufferedWriter, csvFormat);

        } catch (IOException e) {
            log.error("parse csv error", e);
        }
        return csvPrinter;
    }


    public static void write(File outputFile, Iterable<?> datas, String... header) {

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos, Charset.forName(DEFAULT_ENCODING)), DEFAULT_BUFFERED_SIZE);
             CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withHeader(header));) {
            csvPrinter.printRecords(datas);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 按行切割文件
     *
     * @param sourceFilePath
     * @param targetFolderPath
     * @param maxLine
     * @date 2019年11月15日 16:19:50
     */
    public static void curFileByLine(String sourceFilePath, String targetFolderPath, long maxLine) {
        curFileByLine(new File(targetFolderPath), new File(targetFolderPath), maxLine);
    }


    /**
     * 按行切割文件
     *
     * @param targetFolderFile
     * @param targetFolder
     * @param maxLine
     * @date 2019年11月15日 16:19:43
     */
    public static void curFileByLine(File targetFolderFile, File targetFolder, long maxLine) {


        String fullName = targetFolder.getName();
        int beginIndex = fullName.indexOf(".");
        //源文件名称（不含后缀名）
        String fileName = fullName.substring(0, beginIndex);
        // 源文件后缀名
        String suffixName = fullName.substring(beginIndex);

        CSVParser csvParser = CSVUtils.getCSVParser(targetFolder);
        List<String> headerNames = csvParser.getHeaderNames()
                .stream()
                .filter(name -> !StringUtils.isEmpty(name))
                .collect(Collectors.toList());
        // 行计数器
        int count = 0;
        //拆分的目标文件数量计数器
        int suffixCount = 0;
        //拆分的目标文件名称
        String targetFileName = "";
        CSVPrinter write = null;
        for (CSVRecord record : csvParser) {
            try {
                if (count == 0) {
                    suffixCount++;
                    // 拆分的新文件名称
                    targetFileName = String.format("%s%s%s_%s%s", targetFolderFile.getAbsolutePath(), File.separator,
                            fileName, suffixCount, suffixName);
                    File file = new File(targetFileName);
                    if (!targetFolder.exists()) {
                        targetFolder.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(targetFileName);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos, Charset.forName(DEFAULT_ENCODING)), DEFAULT_BUFFERED_SIZE);
                    write = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withHeader(headerNames.toArray(new String[0])));
                }

                List<String> item = Lists.newArrayList();
                for (String headerName : headerNames) {
                    item.add(record.get(headerName));
                }
                //写入缓冲区
                write.printRecord(item);
                count++;

                if (count >= maxLine) {
                    // 写入磁盘
                    write.flush();
                    write.close();
                    write = null;
                    count = 0;
                }
            } catch (IOException e) {
                log.error("write error", e);
            }
        }
        //刷盘、关闭IO
        IOUtils.flush(write);
        IOUtils.close(write);

    }
}
