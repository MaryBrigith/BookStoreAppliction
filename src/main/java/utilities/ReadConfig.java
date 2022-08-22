package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties props;
	public ReadConfig()  {
		try {
		File src = new File("./src/test/resources/runConfig.properties");
		
		FileInputStream fis = new FileInputStream(src);
        props = new Properties();
        props.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getBaseUrl() {
		return props.getProperty("baseUrl");
	}
	
	public String getBrowser() {
		return props.getProperty("browser");
	}
	
	public String getUsername() {
		return props.getProperty("username");
	}
	
	public String getPassword() {
		return props.getProperty("password");
	}
	
	public String getChromePath() {
		return props.getProperty("chromepath");
	}
	
	public String getFirefoxPath() {
		return props.getProperty("firefoxpath");
	}
	
	public String getEdgePath() {
		return props.getProperty("edgepath");
	}

}
