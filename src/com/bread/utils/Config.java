package com.bread.utils;

/**
 * 
 * APP_URL APP_PHONE 需要配置
 *
 */
public class Config {
	public static final String APP_KEY = "BUPTBread";
	public static final String APP_URL = "http://www.uaide.net/wemall/";
	public static final String PHONE = "10086";
	
	public static final String API_GET_GOODS = APP_URL
			+ "index.php/App/Index/appgetgood";
	public static final String API_UPLOADS = APP_URL + "Public/Uploads/";
	public static final String API_REGISTER = APP_URL
			+ "index.php/App/Index/appregister";
	public static final String API_LOGIN = APP_URL
			+ "index.php/App/Index/applogin";
	public static final String API_DO_ADDRESS = APP_URL
			+ "index.php/App/Index/appdoaddress";
	public static final String API_DO_ORDER = APP_URL
			+ "index.php/App/Index/appdoorder";
}
