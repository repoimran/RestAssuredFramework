package common;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Configuration;
import utils.Keys;
import utils.RunJsonServer;

public abstract class RESTSteps {

	RequestSpecification request;
	Response response;
	Configuration config = new Configuration();
	String baseUrl;

	public void setUrl() {
		baseUrl = config.getProps(Keys.baseUrl);
	}

	public void init_request() {
		setUrl();
		request = RestAssured.given().baseUri(baseUrl);
	}

	public void request_GET(String endpoint) {
		response = request.get(endpoint);
	}

	public void request_POST(String endpoint) {
		response = request.post(endpoint);
	}

	public void request_PUT(String endpoint) {
		response = request.put(endpoint);
	}

	public void request_DELETE(String endpoint) {
		response = request.delete(endpoint);
	}

	public void addHeader(Header header) {
		request.header(header);
	}

	public void setContentType(ContentType contentType) {
		request.contentType(contentType);
	}

	public void addRequestBody(String body) {
		request.body(body);
	}

	public void addRequestBody(File file) {
		request.body(file);
	}

	public void addRequestBody(InputStream inputStream) {
		request.body(inputStream);
	}

	public void addRequestBody(byte[] body) {
		request.body(body);
	}

	public void addRequestBody(Map<?, ?> body) {
		request.body(body);
	}

	public void responseCodeValidation(int statusCode) {
		response.then().statusCode(statusCode);
	}

	public void bodyEqualValidation(String path, Object expected) {
		response.then().body(path, equalTo(expected));
	}

	public void headerEqualValidation(String path, Object expected) {
		response.then().header(path, equalTo(expected));
	}

	public void logRequest() {
		request.log().all();
	}

	public void logResponse() {
		response.then().log().all();
	}

	public Response getResponse() {
		return response;
	}
}
