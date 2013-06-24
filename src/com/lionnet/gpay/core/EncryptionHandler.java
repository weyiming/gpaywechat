package com.lionnet.gpay.core;

import com.chinagpay.cgp.merch.util.PinBlock;
import com.chinagpay.mer.bean.DigestUtil;
import com.chinagpay.mer.bean.ProcessMessage;

import java.io.UnsupportedEncodingException;

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

//    报文发送以httppost发送xml格式
//        报文格式样例（账户绑定）：
//        <xml>
//        <gpayAccount>账户</gpayAccount>
//        <pinblock>16位16进制字符串</pinblock>
//        <openID>微信号</openID>
//        </xml>
//
//        以字符串形式再加上chinagoldenpay-xiaozhuan-wechat-123789456
//        得到MD5签名
//
//        错误报文(除账户绑定外)
//        <xml>
//        <errorCode>0098</errorCode>
//        <errorMsg>系统异常</ errorMsg >
//        <md5>MD5签名</md5>
//        </xml>
//
//        <xml>
//        <gpayAccount>账户</gpayAccount>
//        <pinblock>16位16进制字符串</pinblock>
//        <openID>微信号</openID>
//        <md5>MD5签名</md5>
//        </xml>
//
//        BASE64加密后得到密文
//
//        MD5签名生成方法：
//        导入PKIBASE.jar和smapi_20120920.jar
//        String sign=DigestUtil.hmacSign(“<xml><operID>1212</operID></xml>”,”chinagoldenpay-xiaozhuan-wechat-123789456”);
//        BASE64加密方法：
//        String base64Str=ProcessMessage.Base64Encode(“<xml><operID>1212</operID><md5>ade3ade3</md5></xml>”.getByte());
//
//        Pinblock生成方法：
//        1.导入 pinBlock.jar 和在src下放入CGPPinBlock.properties配置文件
//        2.调用 String pinBlock= PinBlock.getPinBlock(“卡号”,”密码”);
//

public class EncryptionHandler {
	private static final String MD5_KEY = "chinagoldenpay-xiaozhuan-wechat-123789456";

	public static String getChecksum(String originalText)
	{
		return DigestUtil.hmacSign(originalText, MD5_KEY);
    }

    public static String pinBlock(String gpayAccount, String gpayPassword)
    {
        try {
            return PinBlock.getPinBlock(gpayAccount, gpayPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public static String encoder(String originalText)
	{
		return ProcessMessage.Base64Encode(originalText.getBytes());
	}
	
	public static String decoder(String originalText)
    {
        String result = null;
        try {
            result = new String(ProcessMessage.Base64Decode(originalText), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result.trim();
	}

    public static void main(String [] args) throws UnsupportedEncodingException {
        //System.out.println(EncryptionHandler.pinBlock("1111111111111111", "222222"));
        System.out.println(new String(ProcessMessage.Base64Decode("PHhtbD48b3BlbklEPm9DRGFOam9TU242bVZQSTVzRlJBV1NXSXFRaHc8L29wZW5JRD48Z3BheUFjY291bnQ+OTUxNjgyMDAwMDAwMDAxMzwvZ3BheUFjY291bnQ+PHBpbmJsb2NrPjdCMUMwNDM3QTY4MEQ3MzQ8L3BpbmJsb2NrPjxtZDU+Njg3N2JhZTA5YTVlYmE0OTc4ZWI1NTk2OTNhNWRhNmI8L21kNT48L3htbD4=")));
    }
}
