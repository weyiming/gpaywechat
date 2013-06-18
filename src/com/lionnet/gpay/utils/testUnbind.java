package com.lionnet.gpay.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-18
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class testUnbind {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(
                "http://localhost:8080/AccountUnbound?openID=oCDaNjoSSn6mVPI5sFRAWSWIqQhw&gpayAccount=9516820000000013&gpayPassword=123456");
        HttpResponse httpResponse = httpClient.execute(httpPost);
        BufferedInputStream bin = new BufferedInputStream(httpResponse.getEntity().getContent());
        byte[] buf = new byte[1024];
        int i = 0;
        while ((i = bin.read(buf)) != -1)
            System.out.println(new String(buf, 0, i));
        bin.close();
        httpClient.getConnectionManager().shutdown();

    }
}
