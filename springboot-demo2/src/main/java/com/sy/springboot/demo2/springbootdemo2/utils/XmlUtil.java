package com.sy.springboot.demo2.springbootdemo2.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlUtil {
    private static XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

    /**
     * @Description: Object转xml
     * @Param: [object, cls]
     * @return: java.lang.String
     * @Author: OverlookView
     */
    public static String objectToXml(Object object, Class cls) {
        return objectToXml(object, cls, null);
    }

    /**
     * @Description: Object转xml
     * @Param: [object, cls, itemCls]
     * @return: java.lang.String
     * @Author: OverlookView
     */
    public static String objectToXml(Object object, Class cls, Class itemCls) {
        xstream.alias("xml", cls);
        if (itemCls != null)
            xstream.alias("item", itemCls);
        return xstream.toXML(object);
    }

    /**
     * @Description: request转map
     * @Param: [request]
     * @return: java.util.Map<java.lang.String , java.lang.String>
     * @Author: OverlookView
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws IOException, DocumentException {
        // 将解析结果存储在 HashMap 中
        Map<String, String> map = new HashMap<String, String>();

        // 从 request 中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到 xml 根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();

        return map;
    }

    public static Map<String, String> parseXml(String xmlStr) throws IOException, DocumentException {
        // 将解析结果存储在 HashMap 中
        Map<String, String> map = new HashMap<String, String>();
        // 读取字符串
        Document document = DocumentHelper.parseText(xmlStr);
        // 得到 xml 根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        return map;
    }

    public static String mapToxml(Map<String, String> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            root.addElement(key).addText(map.get(key));
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        return sw.toString();
    }

}
