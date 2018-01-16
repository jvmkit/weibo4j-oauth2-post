package weibo4j.examples.shorturl;

import weibo4j.ShortUrl;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class ShareCountsOfUrl {

	public static void main(String[] args) {
		String access_token = WeiboConfig.getValue("access_token");
		String url = "http://baidu.com";
		ShortUrl su = new ShortUrl(access_token);
		try {
			JSONObject jo = su.shareCountsOfUrl(url);
			System.out.println(jo.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
