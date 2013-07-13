package com.lionnet.gpay.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-16
 * Time: 下午9:24
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main(String[] args) throws IOException {
       /* openID=oCDaNjoSSn6mVPI5sFRAWSWIqQhw*/
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/MyServletDispatcher");
        httpPost.addHeader("Content-Type", "text/xml");
        StringEntity xmlEntity = new StringEntity(
                "<xml>" +
                "<FromUserName>oCDaNjoSSn6mVPI5sFRAWSWIqQhw</FromUserName>" +
                "<ToUserName>oCDaNjoSSn6mVPI5sFRAWSWIqQhw</ToUserName>" +
                "<Content>明细#9516820000000013</Content>" +
                "</xml>",
                "UTF-8");
        httpPost.setEntity(xmlEntity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();
        /*HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://php.weather.sina.com.cn/xml.php?city="+ URLEncoder.encode("杭州", "gb2312")+"&password=DJOYnieT8234jlsK&day=0");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();*/
        InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
        char[] buff = new char[1024];
        int length = 0;
        while ((length = reader.read(buff)) != -1) {
            System.out.println(new String(buff, 0, length));
        }
        httpClient.getConnectionManager().shutdown();
    }
}
