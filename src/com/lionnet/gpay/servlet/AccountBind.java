package com.lionnet.gpay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.utils.MyXMLController;

/**
 * Servlet implementation class AccountBind
 */
@WebServlet("/AccountBind")
public class AccountBind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountBind() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wechatOpenID = request.getParameter("wechatOpenID");
		String gpayAccount = request.getParameter("gpayAccount");
		String gpayPassword = request.getParameter("gpayPassword");
		
		/* 向北京智惠支付服务器递交绑定请求 */
		String bindUrl = "http://test.com";	//现为拟造网址，后期要替换为北京服务器接口网址
		
		/* 从服务器取得绑定结果 */
		ProcessHandler handler = new ProcessHandler(request, response);
		handler.setEncryptionURLMode(bindUrl);
		handler.postToServer(gpayAccount, gpayPassword, wechatOpenID);
		int result = Integer.parseInt(handler.getMessageByNodeName("bingResult"));
		request.setAttribute("result", result);
		request.getRequestDispatcher("view/bindResult.jsp").forward(request, response);
	}

}
