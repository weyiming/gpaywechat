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
 * Servlet implementation class Advice
 */
public class Advice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Advice() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openID = request.getParameter("openID");
        request.setAttribute("openID", openID);
        request.getRequestDispatcher("view/advice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProcessHandler handler = new ProcessHandler(request, response);
        String openID = (String)request.getAttribute("userName");
        String content = "进行投诉建议：" +
                "<a href=\"" + Contants.getUrlProperty(this, "ADVICE_LINK") + "?openID=" + openID + "\">请点击我</a>";
        handler.setMode(ProcessHandlerMode.WRITE_MODE);
        handler.pushToUser(openID, content);

	}

}
