package api;

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

	UserComment uc = new UserComment();
	Configuration config = new Configuration();

	@BeforeTest
	public void UserCommentSetup() {
		RunJsonServer.runServer();
	}

	@Test
	public void testUserComment_GET() {
		uc.logRequest();
		uc.request_GET(config.getProps(Keys.comment));
		uc.logResponse();
	}

	@Test
	public void testUserComments_POST() {
		uc.logRequest();
		uc.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("id", 21);
		payload.put("body", "some comment body  for 21");
		payload.put("postId", 21);
		uc.addRequestBody(payload);
		uc.request_POST(config.getProps(Keys.comment));
		uc.logResponse();
	}

	@Test
	public void testUserComment_PUT() {
		uc.logRequest();
		uc.setContentType(ContentType.JSON);
		// here we are using LinkedHashMap to ensure the insertion order to match the db
		// data format
		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("id", 19);
		payload.put("body", "some comment body changed here");
		payload.put("postId", 19);
		uc.addRequestBody(payload);
		uc.request_PUT(config.getProps(Keys.comment) + "/" + payload.get("id"));
		uc.logResponse();
		uc.responseCodeValidation(200);
	}

	@Test
	public void testUserPosts_DELETE() {
		uc.logRequest();
		String idToDel = "20";
		uc.request_DELETE(config.getProps(Keys.comment) + "/" + idToDel);
		uc.logResponse();
		uc.responseCodeValidation(200);
	}

	@AfterTest
	public void UserCommentTeardown() {
		RunJsonServer.quitServer();
	}

}
