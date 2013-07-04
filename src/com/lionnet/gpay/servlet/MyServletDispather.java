package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class MyServletDispather
 */
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
		/*String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String[] list = {timestamp, nonce, echostr};
		Arrays.sort(list);
		String temp = list[0] + list[1] + list[2];*/

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String echo = request.getParameter("echostr");
        echo = new String(echo.getBytes("ISO-8859-1"),"UTF-8");
        pw.println(echo);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProcessHandler handler = new ProcessHandler(request, response);

        /*handler.setMode(ProcessHandlerMode.READ_MODE);
        String username = handler.getUserName();
        String content = handler.getUserDirective();
        handler.setMode(ProcessHandlerMode.WRITE_MODE);
        handler.pushToUser(username, content);*/
        boolean ifDispatch = handler.switchServletAndDispatch();
        if (!ifDispatch)
        {
            handler.setMode(ProcessHandlerMode.WRITE_MODE);
            handler.pushToUser((String)request.getAttribute("userName"), Contants.UNKNOW_DIRECTIVE_ERROR + Contants.HELP_TEXT);
        }
	}
}
