package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AccountBind
 */
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
		//System.out.println(request.getParameter("openID"));
        request.setAttribute("openID", request.getParameter("openID"));
        request.getRequestDispatcher("view/accountBind.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String openID = request.getParameter("openID");
		String gpayAccount = request.getParameter("gpayAccount");
		String gpayPassword = request.getParameter("gpayPassword");

		/* 向北京智惠支付服务器递交绑定请求 */
		ProcessHandler handler = new ProcessHandler(request, response);
		handler.setEncryptionURLMode(Contants.BIND_URL);    //此url为绑定账户
		handler.postToServer(openID, gpayAccount, gpayPassword);

		/* 从服务器取得绑定结果 */
		int result = Integer.parseInt(handler.getMessageByNodeName("bindResult"));
		request.setAttribute("result", result);
        request.setAttribute("openID", openID);
		request.getRequestDispatcher("view/bindResult.jsp").forward(request, response);
	}

}
