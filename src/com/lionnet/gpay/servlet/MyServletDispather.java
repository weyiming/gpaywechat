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
import java.util.Arrays;

/**
 * Servlet implementation class MyServletDispather
 */
@WebServlet("/MyServletDispather")
public class MyServletDispather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletDispather() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String[] list = {timestamp, nonce, echostr};
		Arrays.sort(list);
		String temp = list[0] + list[1] + list[2];
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProcessHandler handler = new ProcessHandler(request, response);
        if (!handler.switchServletAndDispatch())
        {
            handler.setMode(ProcessHandlerMode.WRITE_MODE);
            handler.pushToUser(Contants.UNKNOW_DIRECTIVE_ERROR + Contants.HELP_TEXT);
        }
	}
}
