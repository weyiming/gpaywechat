package com.lionnet.gpay.core;

import java.io.IOException;
import java.io.PrintWriter;
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
	
	public ProcessHandler(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}
	
	public void setMode(ProcessHandlerMode mode)
	{	
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
	
	public void setURLMode(URL url)
	{
		try {
			xmlController.initInputDocument(url.openStream());
		} catch (IOException e) {
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
		String textMessage = xmlController.creatTextMessage(getUserName(), content);
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
}
