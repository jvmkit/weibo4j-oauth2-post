package weibo4j.examples.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;

public class TestShare {
	
	
	public static void main(String[] args) throws IOException, WeiboException {  
		
		 HttpClient client = new HttpClient();   
//	      // 设置代理服务器地址和端口        
//		 Oauth oauth = new Oauth();
//	      //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);   
//	      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https   
//	     System.out.println(oauth.authorize("code"));   
//		oauth.authorize("code")
		 HttpMethod method=new GetMethod("https://www.baidu.com/s?ie=UTF-8&wd=12");  
	      //使用POST方法  
	      //HttpMethod method = new PostMethod("http://java.sun.com");  
	      client.executeMethod(method);  
	  
	      //打印服务器返回的状态  
	       System.out.println(method.getStatusLine());  
	      //打印返回的信息  
	      System.out.println(method.getResponseBodyAsString());  
	      //释放连接  
	      method.releaseConnection();  
		
		
//	      HttpClient client = new HttpClient();  
//	      client.getHostConfiguration().setHost( "api.weibo.com" , 443, "https" );  
//	      HttpMethod method = getPostMethod();    // 使用 POST 方式提交数据   
//	      client.executeMethod(method);   //打印服务器返回的状态   
//	      System.out.println(method.getStatusLine());   //打印结果页面  
//	      String response= new String(method.getResponseBodyAsString().getBytes("8859_1"));  
//	  
//	      //打印返回的信息  
//	      System.out.println(response);  
//	      method.releaseConnection();  
	   }  
	  
	   /**  
	 
	    * 使用 GET 方式提交数据  
	    *@return  
	    */  
	  
	   private static HttpMethod getGetMethod(){  
	      return new GetMethod("/simcard.php?simcard=1330227");  
	   }  
	  
	  
	  
	    /**  
	     * 使用 POST 方式提交数据  
	     *@return  
	     * @throws UnsupportedEncodingException 
	     */  
	  
	    private static HttpMethod getPostMethod() throws UnsupportedEncodingException{  
	      PostMethod post = new PostMethod("/2/statuses/share.json");  
	      String string =URLEncoder.encode("你好123456789") ;
	      string+="http://open.weibo.com";
	      System.out.println(string);
	      NameValuePair access_token = new NameValuePair( "access_token","2.007Ly3HG05F5uV13c9398b267hREcC");
	     
	      
	      NameValuePair status = new NameValuePair("status",string);
	      
	      post.setRequestBody( new NameValuePair[] {access_token,status});  
	      return post;   
	   }   
	

}
