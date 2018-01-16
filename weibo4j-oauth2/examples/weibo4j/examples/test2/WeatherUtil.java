package weibo4j.examples.test2;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import weibo4j.Oauth;
import weibo4j.util.WeiboConfig;

import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

public class WeatherUtil {

	private String TIANQI_DAILY_WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json";

	private String TIANQI_API_SECRET_KEY = "4cps3akrumlexk2y"; //

	private String TIANQI_API_USER_ID = "U9F6480288"; //
	
	private static String code; //
	private static String lo; //
	private static String high; //
	private static String low; //
	private static String text_day; //
	

	/**
	 * Generate HmacSHA1 signature with given data string and key
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws SignatureException
	 */
	private String generateSignature(String data, String key)
			throws SignatureException {
		String result;
		try {
			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"),
					"HmacSHA1");
			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
			result = new sun.misc.BASE64Encoder().encode(rawHmac);
		} catch (Exception e) {
			throw new SignatureException("Failed to generate HMAC : "
					+ e.getMessage());
		}
		return result;
	}

	/**
	 * Generate the URL to get diary weather
	 * 
	 * @param location
	 * @param language
	 * @param unit
	 * @param start
	 * @param days
	 * @return
	 */
	public String generateGetDiaryWeatherURL(String location, String language,
			String unit, String start, String days) throws SignatureException,
			UnsupportedEncodingException {
		String timestamp = String.valueOf(new Date().getTime());
		String params = "ts=" + timestamp + "&ttl=30&uid=" + TIANQI_API_USER_ID;
		String signature = URLEncoder.encode(
				generateSignature(params, TIANQI_API_SECRET_KEY), "UTF-8");
		return TIANQI_DAILY_WEATHER_URL + "?" + params + "&sig=" + signature
				+ "&location=" + location + "&language=" + language + "&unit="
				+ unit + "&start=" + start + "&days=" + days;
	}
	public static Map<String, String> getWeather() {
		Map<String, String> map = new HashMap<String, String>();
		WeatherUtil demo = new WeatherUtil();
		try {
			String url = demo.generateGetDiaryWeatherURL(WeiboConfig.getValue("localWeather"), "zh-Hans",
					"c", WeiboConfig.getValue("dayWeather"), "1");
			HttpClient client = new HttpClient();
			HttpMethod method = new GetMethod(url);
			// 使用POST方法
			// HttpMethod method = new PostMethod("http://java.sun.com");
			client.executeMethod(method);
			String string = method.getResponseBodyAsString();
			JSONObject js = new JSONObject(string);
			
			code = js.get("results").toString();
			js = new JSONObject(code.substring(1, code.length() - 1));
			code = js.get("daily").toString();
			lo = js.get("location").toString();
			code = code.substring(1, code.length() - 1);
			js= new JSONObject(code);
			code =js.get("code_day").toString();//状态码
			high =js.get("high").toString();//状态码
			low =js.get("low").toString();//状态码
			text_day =js.get("text_day").toString();//状态码
			map.put("code", code);
			map.put("high", high);
			map.put("low", low);
			map.put("text_day", text_day);
			js= new JSONObject(lo);
			lo = js.getString("name").toString();//地址
			map.put("lo", lo);
			System.out.println("心知天气API-log=>"+code);
			System.out.println("心知天气API-log=>"+lo);
			// 打印服务器返回的状态
			System.out.println("心知天气API-log=>"+method.getStatusLine());
			System.out.println("心知天气API-log=>"+method.getResponseBodyAsString());
			System.out.println("心知天气API-log=>"+"URL:" + url);
			// 释放连接
			method.releaseConnection();
			return map;
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return map;
	}
}
