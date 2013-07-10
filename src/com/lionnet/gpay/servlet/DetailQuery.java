package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.entity.Tran;
import com.lionnet.gpay.entity.TranList;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class DetailQuery
 */
public class DetailQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProcessHandler handler = new ProcessHandler(request, response);
        //handler.setMode(ProcessHandlerMode.READ_MODE);
        String openID = (String) request.getAttribute("userName");
        String gpayAccount = (String) request.getAttribute("content");

        /* 向北京服务器发送查询xml报文 */
        handler.setEncryptionURLMode(Contants.getUrlProperty(this, "DETAIL_URL"));
        handler.postToServer(openID, gpayAccount);

        /* 错误检查，若非北京发来的消息或者返回的是错误报文则返回错误信息给用户 */
        if (!handler.checkFrom() || handler.isError())
        {
            handler.setMode(ProcessHandlerMode.WRITE_MODE);
            handler.pushToUser(openID, Contants.UNBOUND +
                    "<a href=\"" + Contants.getUrlProperty(this, "BIND_LINK") + "?openID=" + openID + "\">请点击我</a>" +
                    "进行绑定操作。");
            return;
        }

        /* 整理返回的结果集返回给用户 */
        ArrayList<Tran> tranList =
                ((TranList)handler.getResult(
                        "tranList",
                        "tran",
                        TranList.class,
                        Tran.class)
                ).getTranList();

        String content = "";
        for (Tran tran:tranList)
        {
            content += "您卡号为" + tran.getGpayAccount() +
                    "的智惠卡余额于" + tran.getDate() + " " + tran.getTrantime() +
                    "进行了一笔" + tran.getTranAmt() + "元的交易\n" +
                    "交易类型：" + tran.getTranType() + "\n" +
                    "交易状态：" + tran.getTranStat() + "\n" +
                    "交易商户：" + tran.getMerchantName() + "\n" +
                    "交易编号：" + tran.getTrace() + "\n";
        }

        handler.setMode(ProcessHandlerMode.WRITE_MODE);
        handler.pushToUser(openID, content);
	}

}
