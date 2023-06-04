package utils;

import java.io.IOException;

public class RunJsonServer {

	static ProcessBuilder processBuilder;

	static Configuration conf = new Configuration();
	static String projectPath = System.getProperty(conf.getProps(Keys.userDir));
	static String dbPath = conf.getProps(Keys.db_path);
	static String fullPath = projectPath + dbPath;
	static String port = conf.getProps(Keys.port);

	public static void runServer() {
		String serverRunCommand = conf.getProps(Keys.server_run);
		String runCommand = serverRunCommand + fullPath + " --port " + port;

		try {
			processBuilder = new ProcessBuilder("cmd", "/c", runCommand);
			processBuilder.inheritIO().start();
			System.out.println("Json-server started at port :8000");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void quitServer() {
		System.out.println("please right click on console --> terminate or disconnect all before closing eclipse");
	}

}