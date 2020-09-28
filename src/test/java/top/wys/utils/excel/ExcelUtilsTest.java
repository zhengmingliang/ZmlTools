//package top.wys.utils.excel;
//
//import com.monitorjbl.xlsx.StreamingReader;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
///**
// * Created by 郑明亮 on 2018/10/10 20:41.
// */
//public class ExcelUtilsTest {
//
//
//
////    @Test
//    public void readTest() throws IOException {
//        String filePath = "C:\\Users\\zml\\Desktop\\当前任务\\利息收入测算1008.xlsx";
////        String filePath = "C:\\Users\\zml\\Desktop\\当前任务\\HPC伪造数据.xlsx";
//        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
//        Workbook workbook = StreamingReader.builder()
//                .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
//                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
//                .open(fileInputStream);            // InputStream or File for XLSX file (required)
//        Sheet sheet = workbook.getSheetAt(0);
//        for (Row cells : sheet) {
//            System.out.println(cells.toString());
//            StringBuilder sb = new StringBuilder();
//            for (Cell cell : cells) {
//                sb.append(cell.getStringCellValue()).append("\t");
//            }
//            System.out.println(sb.toString());
//        }
//
//    }
//
//
//}