package com.lionnet.gpay.utils;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/* 操作xml文件辅助类，使用DOM4J */
public class MyXMLController {
	private InputStream fromXML;
	private SAXReader reader;
	private Document document;
	private Element root;
	
	
	/* 初始化有输入源的文档，对文档进行相关操作，主要为读取信息  */
	public void initInputDocument(InputStream in)
	{
		fromXML = in;
		reader = new SAXReader();
		try {
			document = reader.read(fromXML);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/* 初始化无输入源文档，用以输出，自建根元素为xml的文档，并取得根元素 */
	public void initOutputDocument()
	{
		document = DocumentHelper.createDocument();
		root = document.addElement("xml");
	}
	
	/* 取得特定名称的元素文本信息 */
	public String getNodeContent(String nodeName)
	{
		//使用XPath表达式
		Element element = (Element)document.selectSingleNode("//" + nodeName);
		return element.getText();
	}
	
	public String creatTextMessage(String toUserName, String content)
	{
		/* 创建文本回复消息，在根元素下分别创建符合格式的元素 */
		Element toUserNameElement = root.addElement("ToUserName");
		toUserNameElement.addCDATA(toUserName);
		
		Element fromUserNameElement = root.addElement("FromUserName");
		fromUserNameElement.addCDATA(Contants.DEVELOPER_WECHAT_ACCOUNT);
		
		/* 返回时间戳  */
		Element createTimeElement = root.addElement("CreateTime");
		createTimeElement.addText(String.valueOf(System.currentTimeMillis()));
		
		Element msgTypeElement = root.addElement("MsgType");
		msgTypeElement.addCDATA("text");
		
		Element contentElement = root.addElement("Content");
		contentElement.addCDATA(content);
		
		Element funcFlagElement = root.addElement("FuncFlag");
		funcFlagElement.addText("0");
		
		return XMLToString();
	}
	
	/* 创建音乐信息 */
	public String creatMusicMessage()
	{
		return null;
	}
	
	/* 创建图文信息 */
	public String creatPictureMessage()
	{
		return null;
	}
	
	/* 将xml文档转化为string方便输出 */
	public String XMLToString()
	{		
		String docString = document.asXML();
		return docString.substring(docString.indexOf("<xml>"));
	}
	
	public static void main(String[] args)
	{
		MyXMLController m = new MyXMLController();
		System.out.println(m.creatTextMessage("aaaaaa", "我了个擦"));
	}
}
