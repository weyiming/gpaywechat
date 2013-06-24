package com.lionnet.gpay.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* 操作xml文件辅助类，读写和生成xml操作使用DOM4J，xml转java对象使用XStream */
public class MyXMLController {
	private SAXReader reader;
	private Document document;
	private Element root;
	
	
	/* 初始化有输入源的文档，对文档进行相关操作，主要为读取信息,此处为输入流，未加密，来源为微信或其它  */
	public void initInputDocument(InputStream in)
	{
		reader = new SAXReader();
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/* 初始化有输入源的文档，对文档进行相关操作，主要为读取信息，此处为string，解密后，来源为北京服务器  */
	public void initInputDocument(String in)
	{
		reader = new SAXReader();
		try {
			document = reader.read(new ByteArrayInputStream(in.getBytes("utf-8")));
		} catch (DocumentException e) {
			e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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

    /* 是否包含指定名称的节点 */
    public boolean isHas(String nodeName)
    {
        return XMLToString().contains(nodeName);
    }

    /* 移除指定的element */
    public boolean removeElement(String name)
    {
        root = document.getRootElement();
        return root.remove(root.selectSingleNode("//" + name));
    }

    /* 在xml报文中加入md5校验码 */
    public void appendMD5(String checksum)
    {
        Element md5ELement = root.addElement("md5");
        md5ELement.addText(checksum);
    }

    /* 创建账户绑定的xml报文 */
	public void createBindMessage(String openID, String gpayAccount, String pinblock)
	{
        createOpenIDAndGpayAccountMessage(openID, gpayAccount);
		
		Element gpayPasswordElement = root.addElement("pinblock");
		gpayPasswordElement.addText(pinblock);
	}

    /* 创建商户查询xml报文 */
    public void createMerchantMessage(Integer page, String type, String area)
    {
        createPageMessage(page);

        Element typeElemnt = root.addElement("type");
        typeElemnt.addText(type);

        Element areaElement = root.addElement("area");
        areaElement.addText(area);
    }

    /* 创建包含page的xml报文，可用于商户查询和网点查询 */
    public void createPageMessage(Integer page)
    {
        Element pageElement = root.addElement("page");
        pageElement.addText(page.toString());
    }

    /* 创建包含openID和gpayAccount的xml报文,可用于账户绑定和余额查询、明显查询 */
	public void createOpenIDAndGpayAccountMessage(String openID, String gpayAccount)
	{
		Element openIDElement = root.addElement("openID");
		openIDElement.addText(openID);

        if (gpayAccount == null || gpayAccount.equals(""))
        {
            //System.out.println("为空");
            Element gpayAccountElement = root.addElement("gpayAccount");
            gpayAccountElement.addText("");
        }

        else
        {
            Element gpayAccountElement = root.addElement("gpayAccount");
        }
        //System.out.println(gpayAccount);
        //gpayAccountElement.addText(gpayAccount);
	}

    /* 创建投诉消息 */
    public void createAdviceMessage(String openID, String title, String text, String phone)
    {
        Element openIDElement = root.addElement("openID");
        openIDElement.addText(openID);

        Element titleElement = root.addElement("title");
        titleElement.addText(title);

        Element textElement = root.addElement("text");
        textElement.addText(text);

        Element phoneElement = root.addElement("phone");
        phoneElement.addText(phone);
    }

	/* 创建文本回复消息 */
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

    /* xml转为java对象,调用了xstream */
    public Object xmlToBean(String lstTag, String tag, Class lstClazz, Class clazz, String xml)
    {
        XStream xStream = new XStream();
        xStream.alias(lstTag, lstClazz);
        xStream.addImplicitCollection(lstClazz, lstTag);
        xStream.alias(tag, clazz);
        return xStream.fromXML(xml.substring(xml.indexOf("<xml>") + 5, xml.indexOf("</xml>")));
    }

	public static void main(String[] args)
	{
		/*MyXMLController m = new MyXMLController();
        *//*m.initOutputDocument();
		System.out.println(m.creatTextMessage("aaaaaa", "我了个擦"));
        m.appendMD5("md5xxxxxxxx");
        System.out.println(m.XMLToString());*//*
        m.initInputDocument(new ByteArrayInputStream(("<merchants>" +
                            "<merchant>" +
                            "<area>area1</area>" +
                "<name>name1</name><address>address1</address><tel>tel1</tel></merchant>" +
                "<merchant><area>area2</area><name>name2</name><address>address2</address><tel>tel2</tel></merchant>" +
                "</merchants>").getBytes()));
        String xml = "<xml><merchants>" +
        "<merchant>" +
                "<area>area1</area>" +
                "<name>name1</name><address>address1</address><tel>tel1</tel></merchant>" +
                "<merchant><area>area2</area><name>name2</name><address>address2</address><tel>tel2</tel></merchant>" +
                "</merchants><aaa></aaa></xml>";
        Merchants merchants = (Merchants)m.xmlToBean("merchants", "merchant", Merchants.class, Merchant.class, xml);
        ArrayList<Merchant> merchantArrayList = merchants.getMerchants();
        for (Merchant merchant:merchantArrayList)
        {
            System.out.println(merchant.getArea());
        }*/
        MyXMLController my = new MyXMLController();
	}
}
