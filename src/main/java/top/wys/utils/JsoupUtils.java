/**
 * Created by 郑明亮 on 2022/2/13 16:04.
 */
package top.wys.utils;

import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;

/**
 * <p> jsoup 二次处理增强类</p>
 *
 * @author 郑明亮
 * @version 1.0.0
 * @time 2022/2/13 16:04
 */
public class JsoupUtils {

    /**
     * @param elements
     * @param xpath
     * @return
     */
    public static NodeList selectXpath(Elements elements, String xpath){
      return selectXpath(elements.first(),xpath);
    }

    /**
     * @param element
     * @param xpath
     * @return
     */
    public static NodeList selectXpath(Element element, String xpath){
        W3CDom w3c = new W3CDom();
        org.w3c.dom.Document wDoc = w3c.fromJsoup(element);
        return w3c.selectXpath(xpath, wDoc);
    }

    /**
     * 找到元素节点
     *
     * @param document    jsoup文档
     * @param cssQuery    css选择器
     * @param content     要查找组件包含的内容
     * @return {@link Element}
     */
    public static Element findElement(Document document,String cssQuery,String content) {
        return findElement(document, cssQuery, content, false);
    }

    /**
     * 找到元素节点
     *
     * @param document    jsoup文档
     * @param cssQuery    css选择器
     * @param content     要查找组件包含的内容
     * @param parent      是否找其父节点，如果是，默认只找其上一级父节点
     * @return {@link Element}
     */
    public static Element findElement(Document document,String cssQuery,String content,boolean parent) {
        return findElement(document, cssQuery, content, parent,1);
    }

    /**
     * 找到元素节点
     *
     * @param document    jsoup文档
     * @param cssQuery    css选择器
     * @param content     要查找组件包含的内容
     * @param parent      是否找其父节点
     * @param parentLevel 向上找几个父节点
     * @return {@link Element}
     */
    public static Element findElement(Document document,String cssQuery,String content,boolean parent,int parentLevel) {
        Elements elements = document.select(cssQuery);
        Element data = null;
        for (Element element : elements) {
            if (element.text().contains(content)) {
                if(!parent){
                    return element;
                } else {
                    data = element;
                    for (int i = 0; i < parentLevel; i++) {
                        data = data.parent();
                    }
                    return data;

                }

            }
        }
        return new Element("");
    }

    /**
     * 找到当前节点的所有父节点
     * @param document
     * @param cssQuery
     * @param content
     * @return
     */
    public static Elements findElements(Document document,String cssQuery,String content) {
        Elements elements = document.select(cssQuery);
        for (Element element : elements) {
            if (element.text().contains(content)) {
                return element.parents();
            }
        }
        return new Elements();
    }
}
