package Misc;

import utils.Configuration;
import utils.Keys;
import utils.RunJsonServer;

public class UnitTest {
	static Configuration conf = new Configuration();

	public static void main(String[] args) {
		System.out.println(conf.getProps(Keys.baseUrl));
		TestServerConfiguration();
	}

	public static void TestServerConfiguration() {
		RunJsonServer.runServer();
	}

}
