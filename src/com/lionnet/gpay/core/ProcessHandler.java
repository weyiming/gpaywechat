package com.lionnet.gpay.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lionnet.gpay.utils.MyXMLController;

public class ProcessHandler {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String servletToGo;
	private MyXMLController xmlController;
	private EncryptionHandler ehandler;
	private URL url;
	
	public ProcessHandler(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}
	
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
	
	public void setEncryptionURLMode(String url)
	{
		try {
			this.url = new URL(url);
			if (xmlController == null)
				xmlController = new MyXMLController();
			if (ehandler == null)
				ehandler = new EncryptionHandler();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessageByNodeName(String nodeName)
	{
		return xmlController.getNodeContent(nodeName);
	}
	
	private String getUserDirective()
	{
		return getMessageByNodeName("Content");
	}
	
	public String[] getUserDirectiveContent()
	{
		String fullDirective = getUserDirective();
		if (fullDirective.contains("#"))
		{
			return fullDirective.substring(fullDirective.indexOf("#")).split("#");
		}
		return null;
	}
	
	public String getUserName()
	{
		return getMessageByNodeName("FromUserName");
	}
	
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
	
	/* 跟据用户指令设定要转发的servlet */
	public void switchServletAndDispatch()
	{
		setMode(ProcessHandlerMode.READ_MODE);
		String userDirective = getUserDirective();
		switch(userDirective)
		{
			case WordsDic.BIND:
				servletToGo = WordsDic.BIND_SERVLET;
				break;
			case WordsDic.BALANCE:
				servletToGo = WordsDic.BALANCE_SERVLET;
				break;
			case WordsDic.DETAIL:
				servletToGo = WordsDic.RECORD_SERVLET;
				break;
			case WordsDic.MERCHANT:
				servletToGo = WordsDic.MERCHANT_SERVLET;
				break;
			case WordsDic.BRANCH:
				servletToGo = WordsDic.BALANCE_SERVLET;
				break;
			case WordsDic.SERVICE:
				servletToGo = WordsDic.SREVICE_SERVLET;
				break;
			case WordsDic.WEATHER:
				servletToGo = WordsDic.WEATHER_SERVLET;
				break;
			default:
				other(userDirective);
				break;
		}
		
		/* 派发到指定的servlet */
		try {
			request.getRequestDispatcher(servletToGo).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 其他指令，#后跟具体信息 */
	private void other(String userDirective)
	{
		if (userDirective.contains(WordsDic.BALANCE))
			servletToGo = WordsDic.BALANCE_SERVLET;
		if (userDirective.contains(WordsDic.DETAIL))
			servletToGo = WordsDic.RECORD_SERVLET;
		if (userDirective.contains(WordsDic.WEATHER))
			servletToGo = WordsDic.WEATHER_SERVLET;
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
	
	public void postToServer(String gpayAccount, String gpayPassword, String wechatOpenID)
	{
		xmlController.initOutputDocument();
		String postText = xmlController.createBindMessage(gpayAccount, gpayPassword, wechatOpenID);
		postAndInitInput(url, postText);
	}
	
	public void postToServer(String wechatOpenID)
	{
		xmlController.initOutputDocument();
		String postText = xmlController.createWechatOpenIDMessage(wechatOpenID);
		postAndInitInput(url, postText);
	}
	
	private void postAndInitInput(URL url, String postText)
	{
		ehandler.setOriginalText(postText);
		String postTextAfterEncrypt = ehandler.encoder();
		try {
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.connect();
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			writer.write(postTextAfterEncrypt);
			writer.flush();
			String textFromConn = getStringFromConn(conn.getInputStream());
			ehandler.setOriginalText(textFromConn);
			String textAfterDecoder = ehandler.decoder();
			xmlController.initInputDocument(textAfterDecoder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
