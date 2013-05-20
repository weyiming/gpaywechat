package com.lionnet.gpay.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;

/**
 * Servlet implementation class AccountBindLink
 */
@WebServlet("/AccountBindLink")
public class AccountBindLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountBindLink() {
        super();
        // TODO Auto-generated constructor stub
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
		ProcessHandler handler = new ProcessHandler(request, response);
		handler.setMode(ProcessHandlerMode.READ_MODE);
		String userName = handler.getUserName();
		String content = "请使用以下链接绑定您的微信账号和智惠支付账号：http://xxxxx?wechatOpenID=" + userName;
		handler.setMode(ProcessHandlerMode.WRITE_MODE);
		handler.pushToUser(content);
	}

}
