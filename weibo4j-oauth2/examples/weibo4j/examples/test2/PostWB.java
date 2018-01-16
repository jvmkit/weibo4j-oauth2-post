package weibo4j.examples.test2;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class PostWB implements Job {
	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String access_token = WeiboConfig.getValue("access_token");
			HttpClient client = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(
					"https://api.weibo.com/2/statuses/share.json");
			Map<String, String> map = WeatherUtil.getWeather();
			ChartGraphics.imgFactory(map.get("code"));
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
											+ "℃。祝你每天开开心心(๑ơ ₃ ơ) @傻子是傻不是瞎 ​​​​  http://loveran.tk",
									Charset.forName("utf-8")));
			mutiEntity.addPart("pic", new FileBody(file));

			httpPost.setEntity(mutiEntity);
			HttpResponse response = client.execute(httpPost);
			// 打印服务器返回的状态
			System.out.println(returnstr + "★★★★★★★★★★★");
			System.out.println(response);
			// 释放连接
			httpPost.releaseConnection();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}