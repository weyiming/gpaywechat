package com.lionnet.gpay.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lionnet.gpay.utils.MyXMLController;

public class ProcessHandler {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String servletToGo;
	
	public ProcessHandler(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}
	
	public String getUserMessageNode(String nodeName)
	{
		MyXMLController xmlController;
		try {
			xmlController = new MyXMLController();
			xmlController.initInputDocument(request.getInputStream());
			return xmlController.getNodeContent(nodeName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/* 跟据用户指令设定要转发的servlet */
	public void switchServlet()
	{
		String userMessage = getUserMessageNode("Content");
		switch(userMessage)
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
				other(userMessage);
				break;
		}
	}
	
	/* 其他指令，#后跟具体信息 */
	private void other(String userMessage)
	{
		if (userMessage.contains(WordsDic.BALANCE))
			servletToGo = WordsDic.BALANCE_SERVLET;
		if (userMessage.contains(WordsDic.DETAIL))
			servletToGo = WordsDic.RECORD_SERVLET;
		if (userMessage.contains(WordsDic.WEATHER))
			servletToGo = WordsDic.WEATHER_SERVLET;
	}
	
	/* 派发到指定的servlet */
	public void dispatch()
	{
		try {
			if (servletToGo == WordsDic.BIND_SERVLET)
				{	
					String userName = getUserMessageNode("FromUserName");
					response.sendRedirect("redirect:/AccountBind?uderName=" + userName);
				}
			else
				request.getRequestDispatcher(servletToGo).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
