package com.lionnet.gpay.utils;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyXMLController {
	private InputStream fromXML;
	private SAXReader reader;
	public Document document;
	
	public MyXMLController()
	{
		super();
		String root = "<xml></xml>";
		try {
			document = DocumentHelper.parseText(root);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public MyXMLController(InputStream in)
	{
		fromXML = in;
		reader = new SAXReader();
		try {
			document = reader.read(fromXML);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public String getNodeContent(String nodeName)
	{
		Element element = (Element)document.selectSingleNode("//" + nodeName);
		return element.getText();
	}
	
	public String creatTextMessage(String toUserName, String fromUserName, String content)
	{
		return null;
	}
	
	public String creatMusicMessage()
	{
		return null;
	}
	
	public String creatPictureMessage()
	{
		return null;
	}
	
	public String XMLToString()
	{	
		return document.asXML();
	}
	
	public static void main(String[] args)
	{
		MyXMLController m = new MyXMLController();
		m.document.addComment("sadfasf");
		System.out.println(m.XMLToString().substring(m.XMLToString().indexOf("<xml")));
	}
}
