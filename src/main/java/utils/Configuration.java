package utils;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

	public Properties prop;

	public Configuration() {
		prop = new Properties();
		try {
			String projectPath = System.getProperty("user.dir");
			String propertiesFilePath = "/src/main/resources/configuration.properties";
			prop.load(new FileInputStream(projectPath + propertiesFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProps(Keys key) {
		return prop.getProperty(key.toString());
	}

}
