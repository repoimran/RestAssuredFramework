package api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import endpoints.UserPosts;
import io.restassured.http.ContentType;
import utils.Configuration;
import utils.Keys;
import utils.RunJsonServer;

public class TestUserPosts {

	UserPosts up = new UserPosts();
	Configuration config = new Configuration();

	@BeforeTest
	public void UserPostsSetup() {
		RunJsonServer.runServer();
	}

//	@Test
	public void testUserPosts_GET() {
		up.logRequest();
		up.request_GET(config.getProps(Keys.posts));
		up.logResponse();
	}

	// @Test
	public void testUserPosts_POST() {
		up.logRequest();
		up.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("id", 26);
		payload.put("title", "some title 23");
		payload.put("author", "aut-23");
		up.addRequestBody(payload);
		up.request_POST(config.getProps(Keys.posts));
		up.logResponse();
	}

	// @Test
	public void testUserPosts_PUT() {
		up.logRequest();
		up.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("id", 26);
		payload.put("title", "some title changed here again and again");
		payload.put("author", "aut-26*");
		up.addRequestBody(payload);
		up.request_PUT(config.getProps(Keys.posts) + "/" + payload.get("id"));
		up.logResponse();
		up.responseCodeValidation(200);
	}

	@Test
	public void testUserPosts_DELETE() {
		up.logRequest();
		String idToDel = "23";
		up.request_DELETE(config.getProps(Keys.posts) + "/" + idToDel);
		up.logResponse();
		up.responseCodeValidation(200);
	}

	@AfterTest
	public void UserPostsTeardown() {
		RunJsonServer.quitServer();
	}

}
