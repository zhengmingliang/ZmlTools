//package top.wys.utils;
//
//import lombok.Data;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//
//import java.io.Serializable;
//import java.util.List;
//
///**
// * @author: gangfangdong
// * @date: 2019年12月3日
// * @description: 字段过滤条件
// * @fileName: FieldFilter.java
// */
//@Data
//@ToString
//@Accessors(chain = true)
//public class FieldFilter implements Serializable {
//
//	private static final long serialVersionUID = 9130425265999363298L;
//
//	/**
//	 * 过滤类型  0为值查询 1为灵活查询
//	 */
//	Integer filterType;
//
//
//	/**
//	 *  in的类型  true => in   |  false => not in
//	 */
//	Boolean flag;
//
//	/**
//	 * 值
//	 */
//	List<Object> value;
//
//	/**
//	 * 灵活查询
//	 */
//	List<FieldSubFilter> fieldSubFilters;
//
//
//	/**
//	 * 日期类型格式，add by 郑明亮 2022年3月7日 15:51:20
//	 * 使筛选的日期类型格式和返回结果的日期类型格式分离
//	 */
//	String timePattern;
//}
