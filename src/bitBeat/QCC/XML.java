package bitBeat.QCC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by sure GM on 2016/8/8 0:00.
 */
public class XML {

	//	Java通过DOM解析XML
	public static void parseXML() throws Exception {

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
		Element root=doc.getDocumentElement();
		//	在DOM中只有根节点是一个org.w3c.dom.Element对象。
				
		//		6>得到节点的子节点
		NodeList books=root.getChildNodes();
		//	 这是用一个org.w3c.dom.NodeList接口来存放它所有子节点的，还有一种轮循子节点的方法，后面有介绍
		for(int i=0;i<books.getLength();i++){
			Node book=books.item(i);
			//	注意，节点的属性也是它的子节点。它的节点类型也是Node.ELEMENT_NODE

			//		7>取得节点的属性值
			String email=book.getAttributes().getNamedItem("email").getNodeValue();
			System.out.println(email);
		}

	}


}
