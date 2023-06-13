package api;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import endpoints.UserComment;
import io.restassured.http.ContentType;
import utils.Configuration;
import utils.Keys;
import utils.RunJsonServer;

public class TestUserComment {

	int todel = 30;

	UserComment uc = new UserComment();
	Configuration config = new Configuration();

	@BeforeTest
	public void UserCommentSetup() {
		RunJsonServer.runServer();
	}

	@Test(priority = 3)
	public void testUserComment_GET() {
		uc.logRequest();
		uc.request_GET(config.getProps(Keys.comment));
		uc.logResponse();
	}

	@Test(priority = 1)
	public void testUserComments_POST() {
		uc.logRequest();
		uc.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("id", todel);
		payload.put("body", "some comment body  for 21");
		payload.put("postId", 21);
		uc.addRequestBody(payload);
		uc.request_POST(config.getProps(Keys.comment));
		uc.logResponse();
	}

	@Test(priority = 2)
	public void testUserComment_PUT() {
		uc.logRequest();
		uc.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("id", todel);
		LocalTime now = LocalTime.now();
		String body = "some comment body for change @ " + now.toString();
		payload.put("body", body);
		payload.put("postId", 19);
		uc.addRequestBody(payload);
		uc.request_PUT(config.getProps(Keys.comment) + "/" + payload.get("id"));
		uc.logResponse();
		uc.responseCodeValidation(200);
	}

	@Test
	public void testUserPosts_DELETE() {
		uc.logRequest();
		uc.request_DELETE(config.getProps(Keys.comment) + "/" + todel);
		uc.logResponse();
		uc.responseCodeValidation(200);
	}

	@AfterTest
	public void UserCommentTeardown() {
		RunJsonServer.quitServer();
	}

}
