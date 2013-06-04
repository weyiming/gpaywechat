package com.lionnet.gpay.core;

import com.lionnet.gpay.utils.Contants;
import com.lionnet.gpay.utils.MyXMLController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*  ProcessHandler是控制整个流程的核心类
    主要功能有3点
    1：根据用户指令，派发请求到本地的servlet响应，只用于MyServletDispatch中；
    2：用于读取微信所post的xml报文中的内容，并进行处理返回给用户微信开发api中特定格式的xml报文；
    3：用于与北京服务器交互，其中加密解密用到了EncryptionHandler类
 */
public class ProcessHandler {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String servletToGo;
	private MyXMLController xmlController;
	private URL url;
	
	/* 构造函数用于转发请求，也可读取request输入流中的内容,并向reaponse中输出流写入内容 */
    public ProcessHandler(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}

    /*  实现功能1
     *  跟据用户指令设定要转发的servlet
     */
    public void switchServletAndDispatch()
    {
        setMode(ProcessHandlerMode.READ_MODE);
        String userDirective = getUserDirective();

//        以下代码适用于java7，低于7的版本switch语句中无法使用String类型
//		switch(userDirective)
//		{
//			case Contants.BIND:
//				servletToGo = Contants.BIND_SERVLET;
//				break;
//			case Contants.BALANCE:
//				servletToGo = Contants.BALANCE_SERVLET;
//				break;
//			case Contants.DETAIL:
//				servletToGo = Contants.RECORD_SERVLET;
//				break;
//			case Contants.MERCHANT:
//				servletToGo = Contants.MERCHANT_SERVLET;
//				break;
//			case Contants.NET:
//				servletToGo = Contants.BALANCE_SERVLET;
//				break;
//			case Contants.SERVICE:
//				servletToGo = Contants.SREVICE_SERVLET;
//				break;
//			case Contants.WEATHER:
//				servletToGo = Contants.WEATHER_SERVLET;
//				break;
//			default:
//				other(userDirective);
//				break;
//		}

//        java7以下版本使用以下代码
        if (userDirective.equals(Contants.BIND))
            servletToGo = Contants.BIND_SERVLET;
        else if (userDirective.equals(Contants.BALANCE))
            servletToGo = Contants.BALANCE_SERVLET;
        else if (userDirective.equals(Contants.DETAIL))
            servletToGo = Contants.DETAIL_SERVLET;
        else if (userDirective.equals(Contants.NET))
            servletToGo = Contants.NET_SERVLET;
        else if (userDirective.equals(Contants.MERCHANT))
            servletToGo = Contants.MERCHANT_SERVLET;
        else if (userDirective.equals(Contants.ADVICE))
            servletToGo = Contants.ADVICE_SERVLET;
        else if (userDirective.equals(Contants.WEATHER))
            servletToGo = Contants.WEATHER_SERVLET;
        else if (userDirective.equals(Contants.HELP))
            servletToGo = Contants.HELP_SERVLET;
        else other(userDirective);


		/* 派发到指定的servlet */
        try {
            request.getRequestDispatcher(servletToGo).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 其他指令，#后包含具体信息的指令 */
    private void other(String userDirective)
    {
        if (userDirective.contains(Contants.BALANCE))
            servletToGo = Contants.BALANCE_SERVLET;
        if (userDirective.contains(Contants.DETAIL))
            servletToGo = Contants.DETAIL_SERVLET;
        if (userDirective.contains(Contants.WEATHER))
            servletToGo = Contants.WEATHER_SERVLET;
    }
    /* 功能1 end */

    /* 设定handler的模式，读取或输出,主要是与微信服务器进行xml报文的通信，不包括与北京服务器进行的加密通信 */
	public void setMode(ProcessHandlerMode mode)
	{	
		if (xmlController == null)
			xmlController = new MyXMLController();
		switch(mode)
		{
			case READ_MODE:
				try {
					xmlController.initInputDocument(request.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case WRITE_MODE:
				xmlController.initOutputDocument();
				break;
			default:
				break;
		}
	}
	
	/* 此模式用于与普通url进行不加密的通信，譬如调用新浪天气api取得天气信息 */
    public void setURLMode(String url)
	{
		try {
			this.url = new URL(url);
			xmlController.initInputDocument(this.url.openStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 此模式用于与北京服务器的加密通信 */
    public void setEncryptionURLMode(String url)
	{
		try {
			this.url = new URL(url);
			if (xmlController == null)
				xmlController = new MyXMLController();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessageByNodeName(String nodeName)
	{
		return xmlController.getNodeContent(nodeName);
	}
	
	/* 获取用户的指令 */
    private String getUserDirective()
	{
		return getMessageByNodeName("Content");
	}

    /* 获取用户指令包含的内容，例如天气后跟的城市名，余额后跟的想要查询的账户号 */
	public String getUserDirectiveContent()
	{
		String fullDirective = getUserDirective();
		if (fullDirective.contains("#"))
		{
			return fullDirective.split("#")[1];
		}
		return "";
	}

    /* 获取用户的username，也是openID */
	public String getUserName()
	{
		return getMessageByNodeName("FromUserName");
	}

    /* 此处为非页面信息，直接文字推送给用户 */
	public boolean pushToUser(String content)
	{
		// 生成xml类型的string串
		String textMessage = xmlController.creatTextMessage(getUserName(), content);
		
		// 输出给用户
		response.setCharacterEncoding("utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(textMessage);
			writer.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/* 从输入流中获取string，在此处为北京服务器的连接中获取，以便进行解码工作 */
	private String getStringFromConn(InputStream in) throws IOException
	{
		InputStream bin = new BufferedInputStream(in);
		byte[] buf = new byte[1024];
		StringBuilder sb = new StringBuilder("");
		while (bin.read(buf) != 1)
			sb.append(new String(buf));	//必须new一个String，否则使用byte.toString()方法会出现编码问题，造成结果不一致
		return sb.toString();
	}
	
	/*
	    以下postToServer()方法采用多态，向北京服务器推送请求的消息，
	    主要进行了消息的md5校验，然后进行加密传送给北京
	 */

    /* 绑定账户，其中密码进行了pinblock加密 */
    public void postToServer(String openID, String gpayAccount, String gpayPassword)
	{
		xmlController.initOutputDocument();
        String pinblock = EncryptionHandler.pinBlock(gpayAccount, gpayPassword);
        xmlController.createBindMessage(openID, gpayAccount, pinblock);
		postAndInitInput(url);
	}

    /* 可用于余额查询和明显查询 */
	public void postToServer(String openID, String gpayAccount)
	{
		xmlController.initOutputDocument();
		xmlController.createOpenIDAndGpayAccountMessage(openID, gpayAccount);
		postAndInitInput(url);
	}

    /*
        提取了公共的操作，
        1、进行校验码计算；
        2、在xml报文中加入校验码经过BASE64加密后推送给北京；
        3、url中获取并初始化北京返回的报文。
     */
	private void postAndInitInput(URL url)
	{
        String checksum = EncryptionHandler.getChecksum(xmlController.XMLToString());
        xmlController.appendMD5(checksum);
		String postTextAfterEncrypt = EncryptionHandler.encoder(xmlController.XMLToString());

		try {
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.connect();
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			writer.write(postTextAfterEncrypt);
			writer.flush();

			String textFromConn = getStringFromConn(conn.getInputStream());
			xmlController.initInputDocument(EncryptionHandler.decoder(textFromConn));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /*
        对信息的来源进行验证，确保来自北京服务器
        取出md5校验值与本地生成的校验值进行对比
     */
    public boolean checkFrom()
    {
        String md5 = xmlController.getNodeContent("md5");
        xmlController.removeElement("md5");
        return md5.equals(EncryptionHandler.getChecksum(xmlController.XMLToString()));
    }
}
