package com.lionnet.gpay.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/* 	
 * 	传送数据时的加密流程
 *	1.原始报文（<xml><bindResult>1</bindResult></xml>）
 *	+ 密钥（chinagoldenpay-xiaozhuan-wechat-123789456），
 *	使用MD5加密生成校验值e0380f796328f9719957296fe25262a6
 *
 *	2.base64加密：原始报文（<xml><bindResult>1</bindResult></xml>）
 *	+ 校验值（e0380f796328f9719957296fe25262a6），
 *	得到密文，最后传输
 *  */

/*	接受到数据后的解密流程
 * 	1.用base64解密，得到原始报文和校验值，
 * 	2.然后再把原始报文和密钥拼起来用MD5加密生成校验值，
 * 	3.用生成的校验值和报文中送来的校验值比较，
 * 	如果一致再进行后续业务逻辑处理，不一致就中断
 * */

public class EncryptionHandler {
	private static final String MD5_KEY = "chinagoldenpay-xiaozhuan-wechat-123789456";
	private MessageDigest md5;
	private String originalText;
	
	EncryptionHandler(String originalText)
	{
		this.originalText = originalText;
		try 
		{
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	private String append(String originalText, String appendText)
	{
		return originalText + appendText;
	}

	private String getOutputChecksum()
	{
		md5.update(append(originalText, MD5_KEY).getBytes());
		byte[] b = md5.digest();
		int i;
        StringBuilder buf = new StringBuilder("");
        for (int offset = 0; offset < b.length; offset++) 
        {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        
        return buf.toString();
	}
	
	public String getInputChecksum()
	{
		BASE64Decoder decoder = new BASE64Decoder();
	}
	
	public String encoder()
	{
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode((originalText + getOutputChecksum()).getBytes());
	}
	
	public String decoder()
	{
		//BASE64Decoder decoder = new BASE64Decoder();
	}
}
