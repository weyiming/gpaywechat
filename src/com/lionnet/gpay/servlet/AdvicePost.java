package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.utils.Contants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-20
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class AdvicePost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProcessHandler handler = new ProcessHandler(request, response);
        String openID = request.getParameter("openID");
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String phone = request.getParameter("phone");
        handler.setEncryptionURLMode(Contants.getUrlProperty(this, "ADVICE_URL"));
        handler.postToServer(openID, title, text, phone);

        request.setAttribute("openID", openID);
        request.getRequestDispatcher("view/adviceResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
