package com.lionnet.gpay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String userName = request.getParameter("userName");
		String content = "请使用一下链接绑定您的微信账号和智惠支付账号：" + "http://xxxxx";
		MyXMLController xmlController = new MyXMLController();
		xmlController.initOutputDocument();
		String textMessage = xmlController.creatTextMessage(userName, content);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer =  response.getWriter();
		writer.print(textMessage);
		writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gpayAccount = request.getParameter("gpayAccount");
		String gpayPassword = request.getParameter("gpayPassword");
		
		/* 向北京智惠支付服务器递交绑定请求 */
		URL url = new URL("http://test.com");	//现为拟造网址，后期要替换为北京服务器接口网址
		
		/* 从服务器取得绑定结果 */
		MyXMLController xmlController = new MyXMLController();
		xmlController.initInputDocument(url.openStream());
		int result = Integer.parseInt(xmlController.getNodeContent("bingResult"));
		request.setAttribute("result", result);
		request.getRequestDispatcher("view/bindResult.jsp").forward(request, response);
	}

}
