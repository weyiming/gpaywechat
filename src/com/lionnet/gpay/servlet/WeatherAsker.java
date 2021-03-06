package com.lionnet.gpay.servlet;

import com.lionnet.gpay.core.ProcessHandler;
import com.lionnet.gpay.core.ProcessHandlerMode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 * Servlet implementation class WeatherAsker
 */
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
		//
        String openID = (String)request.getAttribute("userName");
		String city = (String)request.getAttribute("content");
        if (city == null || city.equals(""))
        {
            handler.setMode(ProcessHandlerMode.WRITE_MODE);
            handler.pushToUser(openID, "请按照 天气#城市 的格式输入来获取天气情况");
            return;
        }
		String content = getWeather(city, handler);
		handler.setMode(ProcessHandlerMode.WRITE_MODE);
		handler.pushToUser(openID, content);
	}
	
	private String getWeather(String city, ProcessHandler handler) throws MalformedURLException, UnsupportedEncodingException {
		/* 从新浪天气接口中获取近3日天气，温度，穿衣建议 */
		String weatherUrl;
		StringBuffer weather = new StringBuffer(city + "3日天气：\n");
		for (int day = 0; day<3; day++)
		{
			/*从url返回的xml文档中获取天气信息*/
			weatherUrl = "http://php.weather.sina.com.cn/xml.php?city=" + URLEncoder.encode(city, "gb2312")
						+ "&password=DJOYnieT8234jlsK&day=" + day;
			handler.setURLMode(weatherUrl);
			if (day == 0)
				weather.append("今天：");				
			if (day == 1)
				weather.append("明天：");
			if (day == 2)
				weather.append("后天：");
				weather.append(handler.getMessageByNodeName("temperature2"));
				weather.append("℃~");
				weather.append(handler.getMessageByNodeName("temperature1"));
				weather.append("℃，");
				weather.append(handler.getMessageByNodeName("status1"));
				weather.append("，适合穿");
				weather.append(handler.getMessageByNodeName("chy_l"));
				weather.append("\n");
		}
		return weather.toString();
	}

}
