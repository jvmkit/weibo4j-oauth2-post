package weibo4j.examples.test2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class delayToken implements Job {
	SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date();
	String returnstr = DateFormat.format(d);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

//		Oauth oauth = new Oauth();
//		JSONObject js = new JSONObject(WeiboConfig.getValue("cookie"));
//		HttpResponse response = new HttpClien().code(js, oauth);
//		System.out.println(response);
//		Header header = response.getLastHeader("Location");
//		String code = header.getValue();
//		code = code.substring(code.indexOf("=") + 1);
//		try {
//			System.out.println(oauth.getAccessTokenByCode(code));
//		} catch (WeiboException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(code);
		
		try {
			login.Login();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		System.out.println(returnstr + "★★★★★★★★★★★");
	}

}