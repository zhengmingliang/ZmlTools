//package top.wys.utils;
//
//import com.alibaba.fastjson.annotation.JSONField;
//import com.google.gson.annotations.SerializedName;
//import com.owlike.genson.annotation.JsonDateFormat;
//import com.owlike.genson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//import org.noear.snack.annotation.ONodeAttr;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Objects;
//
///**
// * @author: gangfangdong
// * @date: 2019年12月3日
// * @description: 查询的字段 (维度和指标)
// * @fileName: FieldDataVo.java
// */
//@Data
//@Accessors(chain = true)
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class FieldDataVo implements Serializable{
//
//
//	private static final long serialVersionUID = 3048556605156440239L;
//
//	/**
//	 * @param field
//	 */
//	public FieldDataVo(String field) {
//		this.field = field;
//	}
//
//
//	/**
//	 * 类型 维度1还是指标0, 过滤字段2
//	 */
//	Integer type;
//
//
//	/**
//	 * code字段
//	 */
//	String code;
//
//	/**
//	 * 汉语字段
//	 */
//	String textCode;
//	/**
//	 * 维度字段 是关联维表还是没有关联
//	 */
//	Integer dimType;
//
//	/**
//	 * 显示字段
//	 */
//	String fieldText;
//	/**
//	 * @since 2021年6月30日 12:06:35
//	 *  用于记录前端字段所处位置，如维度面板（dimension_panel）、指标面板（index_panel）、属性面板(property_panel)等
//	 */
//	String position;
//
//	/**
//	 * 字段类型
//	 */
//	@SerializedName(value = "fieldtype")
//	@JSONField(name = "fieldtype")
//	@JsonProperty(aliases = {"fieldtype"})
//	@ONodeAttr(name = "fieldtype")
//	String fieldType;
//
//
//	/**
//	 * 字段
//	 */
//	String field;
//
//
//	/**
//	 * 对应的表id
//	 */
//	String tableid;
//	/**
//	 * add by 郑明亮 2020年12月11日 14:34:08
//	 * 字段格式化格式，主要用于日期格式化
//	 */
//	String timePattern;
//
//
//	/**
//	 * 排序类型  升序还是降序
//	 */
//	String sortType;
//
//
//	/**
//	 * 聚合方式
//	 */
//	String aggType;
//
//
//	/**
//	 * 过滤条件
//	 */
//	List<FieldFilter> fieldFilter;
//
//
//	/**
//	 * 是否显示
//	 */
//	Boolean show;
//
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		FieldDataVo that = (FieldDataVo) o;
//		return Objects.equals(textCode, that.textCode) &&
//				Objects.equals(field, that.field) &&
//				Objects.equals(tableid, that.tableid);
//	}
//
//	public String getFieldText() {
//		return fieldText == null ? field : fieldText;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(type, textCode, fieldText, fieldType, field, tableid, sortType, aggType, fieldFilter);
//	}
//}
