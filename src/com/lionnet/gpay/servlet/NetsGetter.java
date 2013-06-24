package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.entity.NetAddress;
import com.lionnet.gpay.entity.NetAddressList;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class NetsGetter
 */
public class NetsGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NetsGetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ProcessHandler handler = new ProcessHandler(request, response);

        Integer page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        /* 向北京服务器发送查询xml报文 */
        handler.setEncryptionURLMode(Contants.getUrlProperty(this, "NET_URL"));
        handler.postToServer(page);

        int currentPage = Integer.parseInt(handler.getMessageByNodeName("currentPage"));
        int totalPage = Integer.parseInt(handler.getMessageByNodeName("totalPage"));

        /* 整理返回的结果集返回给用户 */
        ArrayList<NetAddress> nets =
                ((NetAddressList)handler.getResult(
                        "netAddressList",
                        "netAddress",
                        NetAddressList.class,
                        NetAddress.class)
                ).getNetAddressList();

        request.setAttribute("nets", nets);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher("view/showNets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProcessHandler handler = new ProcessHandler(request, response);
        String openID = (String)request.getAttribute("userName");
        String content = "<a href=\"" + Contants.getUrlProperty(this, "NET_LINK") + "?page=1\">请点击我</a>进行购卡网点的查询!";
        handler.setMode(ProcessHandlerMode.WRITE_MODE);
        handler.pushToUser(openID, content);
	}

}
