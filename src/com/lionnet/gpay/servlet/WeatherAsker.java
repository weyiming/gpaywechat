package com.lionnet.gpay.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;
import com.lionnet.gpay.utils.MyXMLController;

/**
 * Servlet implementation class WeatherAsker
 */
@WebServlet("/WeatherAsker")
public class WeatherAsker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherAsker() {
        super();
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
		String city = handler.getUserDirectiveContent()[0];
		String content = getWeather(city, handler);
		handler.setMode(ProcessHandlerMode.WRITE_MODE);
		handler.pushToUser(content);
	}
	
	private String getWeather(String city, ProcessHandler handler) throws MalformedURLException
	{
		/* 从新浪天气接口中获取近3日天气，温度，穿衣建议 */
		URL weatherUrl;
		StringBuffer weather = new StringBuffer(city + "3日天气：\n");
		for (int day = 0; day<3; day++)
		{
			/*从url返回的xml文档中获取天气信息*/
			weatherUrl = new URL("http://php.weather.sina.com.cn/xml.php?city="+city+"&password=DJOYnieT8234jlsK&day="+day);
			handler.setURLMode(weatherUrl);
			if (day == 0)
				weather.append("今天：");				
			if (day == 1)
				weather.append("明天：");
			if (day == 2)
				weather.append("后天：");
				weather.append(handler.getMessageByNodeName("temperature2"));
				weather.append(handler.getMessageByNodeName("℃~"));
				weather.append(handler.getMessageByNodeName("temperature1"));
				weather.append(handler.getMessageByNodeName("℃，"));
				weather.append(handler.getMessageByNodeName("status1"));
				weather.append(handler.getMessageByNodeName("，适合穿"));
				weather.append(handler.getMessageByNodeName("chy_l"));
				weather.append("\n");
		}
		return weather.toString();
	}

}
