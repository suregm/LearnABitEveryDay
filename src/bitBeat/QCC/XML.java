package bitBeat.QCC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
	public static void parseXMLBySax() {
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


/**
 * 定义xml解析时的监听类
 *
 * 实现方式有很多，可以实现接口：ContentHandler，DTDHandler， EntityResolver 和 ErrorHandler
 * 但我们常用的继承：DefaultHandler
 */
class SaxUtil extends DefaultHandler {

	private User user;
	private HeadPic headPic;
	private String content;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = new String(ch, start, length);
	}

	//当解析到文本开始时触发
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	//当解析到文本结束时触发
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	//当解析到元素开始时触发
	@Override
	public void startElement(String uri, String localName, String name,
	                         Attributes attributes) throws SAXException {
		if ("user".equals(name)) {
			user = new User();
		}
		if ("headpic".equals(name)) {
			headPic = new HeadPic();
		}
	}

	//当解析到元素结束时触发
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		if ("username".equals(name)) {
			user.setUserName(content);
		}
		if ("password".equals(name)) {
			user.setPassword(content);
		}
		if ("sex".equals(name)) {
			user.setSex(content);
		}
		if ("birthday".equals(name)) {
			try {
				user.setBirthday(sdf.parse(content));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ("pictitle".equals(name)) {
			headPic.setPicTitle(content);
		}
		if ("picurl".equals(name)) {
			headPic.setPicUrl(content);
			user.setHeadPic(headPic);
		}

	}

	public User getUser() {
		return user;
	}
}

class User {
	private String userName;
	private Date birthday;
	private String password;
	private String sex;
	private HeadPic headPic;

	public HeadPic getHeadPic() {
		return this.headPic;
	}

	public void setHeadPic(HeadPic headPic) {
		this.headPic = headPic;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}

class HeadPic {
	private String picTitle;
	private String picUrl;

	public String getPicTitle() {
		return this.picTitle;
	}

	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}