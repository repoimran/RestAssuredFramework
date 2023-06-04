package utils;

public enum Keys {

	baseUrl("baseUrl"), server_run("server_run"), server_end("server_end"), db_path("dbPath"), port("port"),
	posts("posts"), comment("comment"), profile("profile"), userDir("userDir");

	String name;

	Keys(String name) {
		this.name = name;
	}

	public String getKey() {
		return name;
	}

}
