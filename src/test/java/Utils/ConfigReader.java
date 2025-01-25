package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
	
	private static Properties prop;
	private static final Logger LOGGER = LogManager.getLogger(ConfigReader.class);

	public static Properties read_PropertiesFile() {
//		String env = System.getProperty("ENV");
		String env = "QA";
		LOGGER.info("Properties file loaded from env: "+env);
		prop = new Properties();
		String path = getPropFilePath(env);
//		ResourceBundle rb = ResourceBundle.getBundle("config");
//		rb.getString(path)
		
		if(env !=null) {
			try {
				FileInputStream fi = new FileInputStream(path);
				prop.load(fi);
			}catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load properties from "+path, e);
			}
		}
		return prop;
		

	}

	public static String getPropFilePath(String env) {
		switch (env.toUpperCase()) {
		case "QA":
			return "C:\\express_web_testing\\com.osbApp.test\\src\\test\\resources\\config.properties";
		case "STA":
			return "C:\\express_web_testing\\com.osbApp.test\\src\\test\\resources\\config.properties";
		default:
			return "C:\\express_web_testing\\com.osbApp.test\\src\\test\\resources\\config.properties";
		}
	}

}
