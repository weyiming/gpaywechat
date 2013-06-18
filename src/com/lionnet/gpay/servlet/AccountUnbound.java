package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-18
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
public class AccountUnbound extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openID = request.getParameter("openID");
        String gpayAccount = request.getParameter("gpayAccount");
        String gpayPassword = request.getParameter("gpayPassword");

        ProcessHandler handler = new ProcessHandler(request, response);
        handler.setEncryptionURLMode("http://124.207.79.71/WeChat/unboundServlet");
        handler.postToServer(openID, gpayAccount, gpayPassword);

        PrintWriter writer = response.getWriter();
        writer.println(handler.getMessageByNodeName("unbindResult"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
