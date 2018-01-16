package weibo4j.examples.test2;

import java.text.SimpleDateFormat;
import java.util.Date;

import weibo4j.util.WeiboConfig;

public class QuartzStart {

	/** */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = new Date();
		String returnstr = DateFormat.format(d);

		PostWB pt = new PostWB();
		String post = "postwb";
		String token = "token";
		delayToken dt = new delayToken();
		try {
			System.out.println(returnstr + "【系统启动】");
			QuartzManager.addJob(post, pt,WeiboConfig.getValue("postWbtime"));
			QuartzManager.addJob(token, dt,WeiboConfig.getValue("delayToken"));

			// Thread.sleep(10000);
			// System.out.println("【修改时间】");
			// QuartzManager.modifyJobTime(job_name,"0/10 * * * * ?");
			// Thread.sleep(20000);
			// System.out.println("【移除定时】");
			// QuartzManager.removeJob(job_name);
			// Thread.sleep(10000);
			//
			// System.out.println("/n【添加定时任务】");
			// QuartzManager.addJob(job_name,job,"0/5 * * * * ?");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}