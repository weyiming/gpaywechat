package com.lionnet.gpay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		MyXMLController xmlController = new MyXMLController();
		xmlController.initInputDocument(request.getInputStream());
		String userMessage = xmlController.getNodeContent("Content");
		String city = userMessage.substring(userMessage.indexOf("#"));
		String toUserName = xmlController.getNodeContent("FromUserName");
		String content = getWeather(city);
		xmlController.initOutputDocument();
		String textMessage = xmlController.creatTextMessage(toUserName, content);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer =  response.getWriter();
		writer.print(textMessage);
		writer.flush();
	}
	
	private String getWeather(String city)
	{
		URL weatherUrl;
		StringBuffer weather = new StringBuffer(city + "3日天气：\n");
		MyXMLController xmlController = new MyXMLController();
		for (int day = 0; day<3; day++)
		{
			try {
				weatherUrl = new URL("http://php.weather.sina.com.cn/xml.php?city="+city+"&password=DJOYnieT8234jlsK&day="+day);
				xmlController.initInputDocument(weatherUrl.openStream());
				if (day == 0)
					weather.append("今天：");
				if (day == 1)
					weather.append("明天：");
				if (day == 2)
					weather.append("后天：");
				weather.append(xmlController.getNodeContent("temperature2"));
				weather.append(xmlController.getNodeContent("℃~"));
				weather.append(xmlController.getNodeContent("temperature1"));
				weather.append(xmlController.getNodeContent("℃，"));
				weather.append(xmlController.getNodeContent("status1"));
				weather.append(xmlController.getNodeContent("，适合穿"));
				weather.append(xmlController.getNodeContent("chy_l"));
				weather.append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return weather.toString();
	}

}
