package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AccountBindLink
 */
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
		//handler.setMode(ProcessHandlerMode.READ_MODE);
		String openID = (String)request.getAttribute("userName");
		String content = "绑定您的微信账号和智惠支付账号：" +
                "<a href=\"" + Contants.getUrlProperty(this, "BIND_LINK") + "?openID=" + openID + "\">请点击我</a>";
		handler.setMode(ProcessHandlerMode.WRITE_MODE);
		handler.pushToUser(openID, content);
	}

}
