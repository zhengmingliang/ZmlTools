///**
// * Created by 郑明亮 on 2022/3/9 17:19.
// */
//package top.wys.utils;
//
//import com.github.jonathanhds.sqlbuilder.builder.QueryBuilder;
//import org.junit.Test;
//
///**
// * <p> this is your description</p>
// *
// * @author 郑明亮
// * @version 1.0.0
// * @time 2022/3/9 17:19
// */
//public class SqlBuilderTest {
//    @Test
//    public void sqlBuilder() {
//        QueryBuilder query = new QueryBuilder().select()
//                .column("s.name")
//                .column("count(s.impediments) AS total_impediments")
//                .from()
//                .table("sprint s")
//                .groupBy()
//                .column("s.name")
//                .having()
//                .column("total_impediments > 5");
//        System.out.println(query.toString());
//    }
//}
