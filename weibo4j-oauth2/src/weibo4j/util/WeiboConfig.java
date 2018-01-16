package weibo4j.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WeiboConfig {
	public WeiboConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(new File("config.properties")));
//			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			props.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
