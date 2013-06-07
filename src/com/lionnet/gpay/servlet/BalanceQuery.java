package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class BalanceQuery
 */
@WebServlet("/BalanceQuery")
public class BalanceQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceQuery() {
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
        String openID = handler.getUserName();
        String gpayAccount = handler.getUserDirectiveContent();

        /* 像北京服务器发送查询xml报文 */
        handler.setEncryptionURLMode(Contants.BALANCE_URL);
        handler.postToServer(openID, gpayAccount);

        if (!handler.checkFromAndError())
        {
            handler.setMode(ProcessHandlerMode.WRITE_MODE);
            handler.pushToUser(openID, Contants.SERVLET_ERROR);
        }
	}

}
