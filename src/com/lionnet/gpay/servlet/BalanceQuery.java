package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.entity.AccountBalance;
import com.lionnet.gpay.entity.AccountBalanceList;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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

        /* 向北京服务器发送查询xml报文 */
        handler.setEncryptionURLMode(Contants.BALANCE_URL);
        handler.postToServer(openID, gpayAccount);

        /* 错误检查，若非北京发来的消息或者返回的是错误报文则返回错误信息给用户 */
        if (!handler.checkFrom() || handler.isError())
        {
            handler.setMode(ProcessHandlerMode.WRITE_MODE);
            handler.pushToUser(openID, Contants.SERVLET_ERROR);
        }

        /* 整理返回的结果集返回给用户 */
        ArrayList<AccountBalance> accountBalanceList =
                ((AccountBalanceList)handler.getResult(
                        "accountBalanceList",
                        "accountBalance",
                        AccountBalanceList.class,
                        AccountBalance.class)
                ).getAccountBalanceList();

        String content = "";
        for (AccountBalance accountBalance:accountBalanceList)
        {
            content += "您账号为" + accountBalance.getGpayAccount() +
                    "的账户余额为：" + accountBalance.getBalance() + "元\n" +
                    "查询时间：" + accountBalance.getQueryTime() + "\n";
        }

        handler.setMode(ProcessHandlerMode.WRITE_MODE);
        handler.pushToUser(openID, content);
	}

}
