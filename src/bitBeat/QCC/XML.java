package bitBeat.QCC;

import org.dom4j.io.SAXReader;
import org.jdom2.input.SAXBuilder;
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

//	解析XML常用的四种方法：

//	1）DOM（JAXP Crimson解析器）
//	DOM是用与平台和语言无关的方式表示XML文档的官方W3C标准。DOM是以层次结构组织的节点或信息片断的集合。这个层次结构允许开发人员在树中寻找特定信息。分析该结构通常需要加载整个文档和构造层次结构，然后才能做任何工作。由于它是基于信息层次的，因而DOM被认为是基于树或基于对象的。DOM
//	以及广义的基于树的处理具有几个优点。首先，由于树在内存中是持久的，因此可以修改它以便应用程序能对数据和结构作出更改。它还可以在任何时候在树中上下导航，而不是像SAX那样是一次性的处理。DOM使用起来也要简单得多。

//	2）SAX
//	SAX处理的优点非常类似于流媒体的优点。分析能够立即开始，而不是等待所有的数据被处理。而且，由于应用程序只是在读取数据时检查数据，因此不需要将数据存储在内存中。这对于大型文档来说是个巨大的优点。事实上，应用程序甚至不必解析整个文档；它可以在某个条件得到满足时停止解析。一般来说，SAX还比它的替代者DOM快许多。
//	选择DOM还是选择SAX？ 对于需要自己编写代码来处理XML文档的开发人员来说， 选择DOM还是SAX解析模型是一个非常重要的设计决策。
//	DOM采用建立树形结构的方式访问XML文档，而SAX采用的事件模型。
//	DOM解析器把XML文档转化为一个包含其内容的树，并可以对树进行遍历。用DOM解析模型的优点是编程容易，开发人员只需要调用建树的指令，然后利用navigation APIs
// 访问所需的树节点来完成任务。可以很容易的添加和修改树中的元素。然而由于使用DOM解析器的时候需要处理整个XML文档，所以对性能和内存的要求比较高，尤其是遇到很大的XML文件的时候。由于它的遍历能力，DOM解析器常用于XML文档需要频繁的改变的服务中。
//	SAX解析器采用了基于事件的模型，它在解析XML文档的时候可以触发一系列的事件，当发现给定的tag的时候，它可以激活一个回调方法，告诉该方法制定的标签已经找到。SAX对内存的要求通常会比较低，因为它让开发人员自己来决定所要处理的tag.特别是当开发人员只需要处理文档中所包含的部分数据时，SAX这种扩展能力得到了更好的体现。但用SAX解析器的时候编码工作会比较困难，而且很难同时访问同一个文档中的多处不同数据。

//	3）JDOM
//	JDOM的目的是成为Java特定文档模型，它简化与XML的交互并且比使用DOM实现更快。由于是第一个Java特定模型，JDOM一直得到大力推广和促进。正在考虑通过“Java规范请求JSR-102”将它最终用作“Java标准扩展”。从2000年初就已经开始了JDOM开发。
//	JDOM与DOM主要有两方面不同。首先，JDOM仅使用具体类而不使用接口。这在某些方面简化了API，但是也限制了灵活性。第二，API大量使用了Collections类，简化了那些已经熟悉这些类的Java开发者的使用。
//	JDOM文档声明其目的是“使用20%（或更少）的精力解决80%（或更多）Java/XML问题”（根据学习曲线假定为20%）。JDOM对于大多数
//	Java/XML应用程序来说当然是有用的，并且大多数开发者发现API比DOM容易理解得多。JDOM还包括对程序行为的相当广泛检查以防止用户做任何在XML中无意义的事。然而，它仍需要您充分理解XML以便做一些超出基本的工作（或者甚至理解某些情况下的错误）。这也许是比学习DOM或JDOM接口都更有意义的工作。
//	JDOM自身不包含解析器。它通常使用SAX2解析器来解析和验证输入XML文档（尽管它还可以将以前构造的DOM表示作为输入）。它包含一些转换器以将JDOM表示输出成SAX2事件流、DOM模型或XML文本文档。JDOM是在Apache许可证变体下发布的开放源码。

//	4）DOM4J
//	虽然DOM4J代表了完全独立的开发结果，但最初，它是JDOM的一种智能分支。它合并了许多超出基本XML文档表示的功能，包括集成的XPath支持、
//	XML Schema支持以及用于大文档或流化文档的基于事件的处理。它还提供了构建文档表示的选项，它通过DOM4J
//	API和标准DOM接口具有并行访问功能。从2000下半年开始，它就一直处于开发之中。
//	为支持所有这些功能，DOM4J使用接口和抽象基本类方法。DOM4J大量使用了API中的Collections类，但是在许多情况下，它还提供一些替代方法以允许更好的性能或更直接的编码方法。直接好处是，虽然DOM4J付出了更复杂的API的代价，但是它提供了比JDOM大得多的灵活性。
//	在添加灵活性、XPath集成和对大文档处理的目标时，DOM4J的目标与JDOM是一样的：针对Java开发者的易用性和直观操作。它还致力于成为比
//	JDOM更完整的解决方案，实现在本质上处理所有Java/XML问题的目标。在完成该目标时，它比JDOM更少强调防止不正确的应用程序行为。
//	DOM4J是一个非常非常优秀的Java XML API，具有性能优异、功能强大和极端易用使用的特点，同时它也是一个开放源代码的软件。如今你可以看到越来越多的Java软件都在使用DOM4J来读写XML，特别值得一提的是连Sun的JAXM也在用DOM4J.


//  比较
//	1）DOM4J性能最好，连Sun的JAXM也在用DOM4J.目前许多开源项目中大量采用DOM4J，例如大名鼎鼎的Hibernate也用DOM4J来读取XML配置文件。如果不考虑可移植性，那就采用DOM4J.
//	2）JDOM和DOM在性能测试时表现不佳，在测试10M文档时内存溢出。在小文档情况下还值得考虑使用DOM和JDOM.虽然JDOM的开发者已经说明他们期望在正式发行版前专注性能问题，但是从性能观点来看，它确实没有值得推荐之处。另外，DOM仍是一个非常好的选择。DOM实现广泛应用于多种编程语言。它还是许多其它与XML相关的标准的基础，因为它正式获得W3C推荐（与基于非标准的Java模型相对），所以在某些类型的项目中可能也需要它（如在JavaScript中使用DOM）。
//	3）SAX表现较好，这要依赖于它特定的解析方式－事件驱动。一个SAX检测即将到来的XML流，但并没有载入到内存（当然当XML流被读入时，会有部分文档暂时隐藏在内存中）。


//	四种XML操作方式的基本使用方法

	//	xml文件：
//    <?xml version="1.0" encoding="GB2312"?>
//    <RESULT>
//	    <VALUE>
//		    <NO>A1234</NO>
//		    <ADDR>四川省XX县XX镇XX路X段XX号</ADDR>
//	    </VALUE>
//	    <VALUE>
//		    <NO>B1234</NO>
//		    <ADDR>四川省XX市XX乡XX村XX组</ADDR>
//	    </VALUE>
//	</RESULT>

//  1）DOM
public class MyXMLReaderByDOM {
	public void main(String arge[]) {
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("data_10k.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList nl = doc.getElementsByTagName("VALUE");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.print("车牌号码:" +

						doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
				System.out.println("车主地址:" +

						doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//	 2）SAX
class MyXMLReaderBySAX extends DefaultHandler {

	java.util.Stack tags = new java.util.Stack();

	public MyXMLReaderBySAX() {
		super();
	}

	public void main(String args[]) {
		long lasting = System.currentTimeMillis();
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser sp = sf.newSAXParser();
			MyXMLReaderBySAX reader = new MyXMLReaderBySAX();
			//				sp.parse(new InputSource("data_10k.xml"), reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) +
				"毫秒");
	}

	public void characters(char ch[], int start, int length) throws
			SAXException {
		String tag = (String) tags.peek();
		if (tag.equals("NO")) {
			System.out.print("车牌号码：" + new String(ch, start, length));
		}
		if (tag.equals("ADDR")) {
			System.out.println("地址:" + new String(ch, start, length));
		}
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		tags.push(qName);
	}
}

//	3）JDOM
public class MyXMLReaderByJDOM {
	public void main(String arge[]) {
		long lasting = System.currentTimeMillis();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = (Document) builder.build(new File("data_10k.xml"));
			//				Element foo = doc.getRootElement();
			//				List allChildren = foo.getChildren();
			//				for(int i=0;i<allChildren.size();i++) {
			//					System.out.print("车牌号码:" +
			//							((Element)allChildren.get(i)).getChild("NO").getText());
			//					System.out.println("车主地址:" +
			//							((Element)allChildren.get(i)).getChild("ADDR").getText());
			//				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//    4）DOM4J
public class MyXMLReaderByDOM4J {
	public void main(String arge[]) {
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("data_10k.xml");
			SAXReader reader = new SAXReader();
			Document doc = (Document) reader.read(f);
			//				Element root = doc.getRootElement();
			//				Element foo;
			//				for (Iterator i = root.elementIterator("VALUE"); i.hasNext() {
			//					foo = (Element) i.next();
			//					System.out.print("车牌号码:" + foo.elementText("NO"));
			//					System.out.println("车主地址:" + foo.elementText("ADDR"));
			//				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




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

//[优点]
//			(1).节约内存开销
//			SAX解析器在某些方面优于DOM风格解析器，因为SAX解析器的内存使用量一般远低于DOM解析器使用量。DOM解析器在任何处理开始之前，必须将xml以整棵树放在内存，所以DOM解析器的内存使用量完全根据输入资料的大小。相对来说，SAX解析器的内存内容，是只基于XML档案的最大深度（XML树的最大深度）和单一XML项目上XML属性储存的最大资料。
//			(2)解析速度快
//			因为SAX事件驱动的本质，处理文件通常会比DOM风格的解析器快。
//[缺点]
//			SAX事件驱动的模型对于XML解析很有用，但它确实有某些缺点。
//			某些种类的XML验证需要存取整份文件。例如，一个DTD IDREF属性需要文件内有项目使用指定字串当成DTD ID属性。要在SAX解析器内验证，必须追踪每个之前遇过的ID和IDREF属性，检查是否有任何相符。更甚者，一个IDREF找不到对应的ID，使用者只会在整份文件都解析完后才发现，若这种连结对于建立有效输出是重要的，那用在处理整份文件的时间只是浪费。


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