package api;

import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import endpoints.UserProfile;
import io.restassured.http.ContentType;
import utils.Configuration;
import utils.Keys;
import utils.RunJsonServer;

public class TestUserProfile {
	UserProfile uf = new UserProfile();
	Configuration config = new Configuration();

	@BeforeTest
	public void UserProfileSetup() {
		RunJsonServer.runServer();
	}

	@Test(priority = 1)
	public void testUserProfile_GET() {
//		uf.logRequest();
		uf.request_GET(config.getProps(Keys.profile));
		uf.logResponse();
	}

	@Test(priority = 3)
	public void testUserProfile_PUT() {
//		uf.logRequest();
		uf.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("name", "imran after update");
		uf.addRequestBody(payload);
		uf.request_PUT(config.getProps(Keys.profile));
		uf.logResponse();
		uf.responseCodeValidation(200);
	}

	@AfterTest
	public void UserPostsTeardown() {
		RunJsonServer.quitServer();
	}

}
