package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.entity.Merchant;
import com.lionnet.gpay.entity.MerchantList;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class MerchantsGetter
 */
public class MerchantsGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MerchantsGetter() {
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

        String type = request.getParameter("type");
        String area = request.getParameter("area");
        String city = request.getParameter("city");

        /* 向北京服务器发送查询xml报文 */
        if (city.equals("bj"))
            handler.setEncryptionURLMode(Contants.getUrlProperty(this, "BJ_MERCHSNT_URL"));
        if (city.equals("sh"))
            handler.setEncryptionURLMode(Contants.getUrlProperty(this, "SH_MERCHSNT_URL"));
        handler.postToServer(page, type, area);

        int currentPage = Integer.parseInt(handler.getMessageByNodeName("currentPage"));
        int totalPage = Integer.parseInt(handler.getMessageByNodeName("totalPage"));

        /* 整理返回的结果集返回给用户 */
        ArrayList<Merchant> merchants =
                ((MerchantList)handler.getResult(
                        "merchantList",
                        "merchant",
                        MerchantList.class,
                        Merchant.class)
                ).getMerchantList();

        request.setAttribute("merchants", merchants);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher("view/showMerchants.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {ProcessHandler handler = new ProcessHandler(request, response);
        String openID = (String)request.getAttribute("userName");
        String content = "<a href=\"" + Contants.getUrlProperty(this, "MERCHANT_LINK") + "?page=1\">请点击我</a>进行特约商户的查询!";
        handler.setMode(ProcessHandlerMode.WRITE_MODE);
        handler.pushToUser(openID, content);
	}

}
