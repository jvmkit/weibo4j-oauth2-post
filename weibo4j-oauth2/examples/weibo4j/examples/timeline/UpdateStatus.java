package weibo4j.examples.timeline;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.examples.test2.ChartGraphics;
import weibo4j.examples.test2.Util;
import weibo4j.examples.test2.WeatherUtil;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class UpdateStatus {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String access_token = WeiboConfig.getValue("access_token");
		HttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(
				"https://api.weibo.com/2/statuses/share.json");
		/*
		 * httpPost.setHeader("User-Agent","SOHUWapRebot");
		 * httpPost.setHeader("Accept-Language","zh-cn,zh;q=0.5");
		 * httpPost.setHeader("Accept-Charset","GBK,utf-8;q=0.7,*;q=0.7");
		 * httpPost.setHeader("Connection","keep-alive");
		 */
		Map<String, String> map = WeatherUtil.getWeather();
		ChartGraphics.imgFactory(map);
		MultipartEntity mutiEntity = new MultipartEntity();
		File file = new File(WeiboConfig.getValue("upImgPath"));
		mutiEntity.addPart("access_token", new StringBody(access_token));
		mutiEntity
				.addPart(
						"status",
						new StringBody(
								"今天天气"
										+ map.get("text_day")
										+ "，最高气温"
										+ map.get("high")
										+ "℃最低气温"
										+ map.get("low")
										+ "℃。祝你每天开开心心(๑ơ ₃ ơ)  http://loveran.tk",
								Charset.forName("utf-8")));
		mutiEntity.addPart("pic", new FileBody(file));

		httpPost.setEntity(mutiEntity);
		HttpResponse response = client.execute(httpPost);
		// 打印服务器返回的状态
		System.out.println(response);
		// 释放连接
		httpPost.releaseConnection();
		/*
		 * String statuses = "微博测试！ http://open.weibo.com"; String
		 * path="G:\\Pictures\\dbb666865b9219245f28b877795637a1.jpg";
		 * Util.image2byte(path); ImageItem item = new
		 * ImageItem("love",Util.image2byte(path));
		 * 
		 * Timeline tm = new Timeline(access_token); try { Status status =
		 * tm.uploadStatus(statuses,item); Log.logInfo(status.toString()); }
		 * catch (WeiboException e) { e.printStackTrace(); }
		 */
	}

}
