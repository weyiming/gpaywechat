package com.lionnet.gpay.core;

public class WordsDic {
	/* servlet地址 */
	public static final String BIND_SERVLET = "/AccountBindLink";
	public static final String BALANCE_SERVLET = "/BalanceQuery";
	public static final String RECORD_SERVLET = "/RecordQuery";
	public static final String MERCHANT_SERVLET = "/MerchantsGetter";
	public static final String BRANCH_SERVLET = "/BranchesGetter";
	public static final String WEATHER_SERVLET = "/WeatherAsker";
	public static final String SREVICE_SERVLET = "/Service";
	
	/* 用户指令 */
	public static final String BIND = "绑定";
	public static final String BALANCE = "余额";
	public static final String MERCHANT = "商户";
	public static final String DETAIL = "明细";
	public static final String BRANCH = "网点";
	public static final String WEATHER = "天气";
	public static final String SERVICE = "客服";
	
	/* 返回信息 */
	public static final String WELCOME = "欢迎您关注智惠支付微信公众账户！";
	public static final String UNBOUND_TEXT = "很遗憾，您还未进行微信和智惠支付账户的绑定，不能执行刚才的操作，请回复“绑定”完成账户的绑定操作。";
	public static final String ERROR_TEXT = "很遗憾，您所输入的信息我不能识别，请按照帮助文档输入指令";
	public static final String HELP_TEXT = 
			"帮助信息：\n" +
			"1、输入“绑定”进行微信号与 智惠支付账户的绑定；\n" +
			"2、输入“余额”进行所有绑定的智惠支付账户的余额查询，\n" +
			"输入“余额#智惠支付账户”进行指定智惠支付账户的余额查询，例如“余额#12345678”；\n" +
			"3、输入“明细”进行所有绑定的 智惠支付账户的最近一条消费明细查询；\n" +
			"输入“明细#智惠支付账户”进行指定智惠支付账户的最近一条消费明细查询，例如“明细#12345678”；\n" +
			"4、输入“商户”进行特约商户的查询；\n" +
			"5、输入“网点”进行购卡网点的查询；\n" +
			"6、输入“天气”进行本地天气的查询；\n" +
			"输入“天气#城市”进行指定城市天气的查询，例如“天气#北京”；\n" +
			"7、输入“客服#问题描述#联系电话”进行问题的反馈，联系方式可不填，若不填则按照智惠支付账户中的联系方式进行回访；\n";
	public static final String SERVICE_FEEDBACK = "您的热心建议已经提交，请耐心等待客服人员的处理，我们会在第一时间联系您。";
}
