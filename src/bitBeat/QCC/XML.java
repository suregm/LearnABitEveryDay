package bitBeat.QCC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sure GM on 2016/8/8 0:00.
 */
public class XML {

	//	Java通过DOM解析XML
	public static void parseXMLByDom() throws Exception {

		//      1>得到DOM解析器的工厂实例
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		//	得到javax.xml.parsers.DocumentBuilderFactory;类的实例就是我们要的解析器工厂

		//		2>从DOM工厂获得DOM解析器
		DocumentBuilder dombuilder = domfac.newDocumentBuilder();
		//	通过javax.xml.parsers.DocumentBuilderFactory实例的静态方法newDocumentBuilder()得到DOM解析器

		//		3>把要解析的XML文档转化为输入流，以便DOM解析器解析它
		InputStream is = new FileInputStream("bin/library.xml");
		//	InputStream是一个接口。

		//		4>解析XML文档的输入流，得到一个Document
		Document doc = dombuilder.parse(is);
		//	由XML文档的输入流得到一个org.w3c.dom.Document对象，以后的处理都是对Document对象进行的

		//		5>得到XML文档的根节点
		Element root = doc.getDocumentElement();
		//	在DOM中只有根节点是一个org.w3c.dom.Element对象。

		//		6>得到节点的子节点
		NodeList books = root.getChildNodes();
		//	 这是用一个org.w3c.dom.NodeList接口来存放它所有子节点的，还有一种轮循子节点的方法，后面有介绍
		for (int i = 0; i < books.getLength(); i++) {
			Node book = books.item(i);
			//	注意，节点的属性也是它的子节点。它的节点类型也是Node.ELEMENT_NODE

			//		7>取得节点的属性值
			String email = book.getAttributes().getNamedItem("email").getNodeValue();
			System.out.println(email);

			//		8>轮循子节点
			for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getNodeName().equals("name")) {
						String name = node.getNodeValue();
						String name1 = node.getFirstChild().getNodeValue();
						//      ...
					}
					if (node.getNodeName().equals("price")) {
						String price = node.getFirstChild().getNodeValue();
						//		...
					}
				}
			}
		}
	}




//	Java通过SAX解析XML

//			Simple API for XML（简称SAX）是个循序存取XML的解析器API。
//			一个实现SAX的解析器（也就是“SAX Parser”）以一个串流解析器的型式作用，拥有事件驱动API。由使用者定义回调函数，解析时，若发生事件的话会被调用。SAX事件包括：
//			XML 文字 节点
//			XML 元素 节点
//			XML 处理指令
//			XML 注释
//			Java代码

	//<person>
	//    <user>
	//        <username>Sure</username>
	//        <password>1234</password>
	//        <sex>male</sex>
	//        <birthday>1989/12/02</birthday>
	//        <headpic>
	//            <pictitle>suregm</pictitle>
	//            <picurl>images/avatar.jpg</picurl>
	//        </headpic>
	//    </user>
	//</person>
	// 此为下面即将解析度简单xml结构，并将其封装成一个User对象。

//	Java代码
	public static void parseXMLBySax {
		try {
			//1.获取factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//2.获取parser
			SAXParser parser = factory.newSAXParser();
			//3.获取解析时的监听器对象
			SaxUtil su = new SaxUtil();
			//4.开始解析
			parser.parse(new File("src/user-params.xml"), su);

			System.out.println(su.getUser());

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
