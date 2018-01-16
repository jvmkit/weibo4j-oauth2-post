package weibo4j.examples.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import weibo4j.Oauth;
import weibo4j.examples.test2.ChartGraphics;
import weibo4j.examples.test2.HttpClien;
import weibo4j.examples.test2.PostWB;
import weibo4j.examples.test2.Util;
import weibo4j.examples.test2.WeatherUtil;
import weibo4j.examples.test2.login;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class test {
	
	public static void main(String[] args) throws WeiboException, ParseException, ClientProtocolException, IOException {
//		  Oauth oauth = new Oauth();
//		  JSONObject js = new JSONObject(WeiboConfig.getValue("cookie"));
//	        HttpResponse response = new HttpClien().code(js, oauth);
//	        System.out.println(response);
//	        Header header = response.getLastHeader("Location");
//	        String code = header.getValue();
//	        //String request = HttpTools.getRequest(client, oauth.authorize("code")); https://www.baidu.com/?code=00dfe2d2da54c2fa8384774dccef9168
//	        //String access_token = WeiboConfig.getValue("access_token");
//	       code =code.substring(code.indexOf("=")+1);
//	       code.indexOf("=");
//	       System.out.println(oauth.getAccessTokenByCode(code));
//	       System.out.println(code);
		
		
		login.Login();
		
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//	    Date date = sdf.parse("2015-05-22");  
//	    Date date2 = new Date();
//	    System.out.println(differentDays(date,date2));
//	    System.out.println(daysBetween(date,date2));
//	    Map<String, String> map = WeatherUtil.getWeather();
//		ChartGraphics.imgFactory("7");
//		WeatherUtil.getWeather();
//		 JSONObject js = new JSONObject(WeiboConfig.getValue("cookie"));
//		
//		 String response = new HttpClien().PostWeiboImage(js,Util.image2byte("C:\\Users\\陈英杰\\Pictures\\lovewallpaper\\006.jpg"));
//		
//		System.out.println(response);
	}
	 public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));           
	    }    
	 
	 
	 
	 
	 public static int differentDays(Date date1,Date date2)
	 {
	 Calendar cal1 = Calendar.getInstance();
	 cal1.setTime(date1);
	 
	 Calendar cal2 = Calendar.getInstance();
	 cal2.setTime(date2);
	 int day1= cal1.get(Calendar.DAY_OF_YEAR);
	 int day2 = cal2.get(Calendar.DAY_OF_YEAR);
	 
	 int year1 = cal1.get(Calendar.YEAR);
	 int year2 = cal2.get(Calendar.YEAR);
	 if(year1 != year2) //同一年
	 {
	  int timeDistance = 0 ;
	  for(int i = year1 ; i < year2 ; i ++)
	  {
	  if(i%4==0 && i%100!=0 || i%400==0) //闰年  
	  {
	   timeDistance += 366;
	  }
	  else //不是闰年
	  {
	   timeDistance += 365;
	  }
	  }
	  
	  return timeDistance + (day2-day1) ;
	 }
	 else //不同年
	 {
	  System.out.println("判断day2 - day1 : " + (day2-day1));
	  return day2-day1;
	 }
	 }
	 
}
