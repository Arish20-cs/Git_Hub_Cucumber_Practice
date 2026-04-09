package crudOperations;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class Request_Chain_Git_CRUD {
	static String token="Your_Token";
	String user="Arish20-cs";
	String title="practiceGit87";
	@Test(priority=1)
	public void createRepo() {
		HashMap<String,String> h=new HashMap<>();
		h.put("name", title);
		h.put("description", "It is created");
		given()
		.header("Authorization","Bearer "+token)
		.contentType(ContentType.JSON)
		.body(h)
		.when()
		.post("https://api.github.com/user/repos")
		.then()
		.statusCode(201)
		.assertThat()
		.statusLine(Matchers.containsString("Created"))
		.log().all();
	}
	@Test(priority=2)
	public void getRepo() {
		given()
		.header("Authorization","Bearer "+token)
		.pathParam("user", user)
		.pathParam("title", title)
		.when()
		.get("https://api.github.com/repos/{user}/{title}")
		.then()
	    .statusCode(200)
	    .assertThat()
		.statusLine(Matchers.containsString("OK"))
		.log().all();
	}
	@Test(priority=3)
	public void updateRepo() {
		HashMap<String,String> h=new HashMap<>();
		
		h.put("description", "It is updated");
		given()
		.header("Authorization","Bearer "+token)
		.contentType(ContentType.JSON)
		.pathParam("user", user)
		.pathParam("title", title)
		.body(h)
		.when()
		.patch("https://api.github.com/repos/{user}/{title}")
		.then()
		.statusCode(200)
	    .assertThat()
		.statusLine(Matchers.containsString("OK"))
		.log().all();
	}
	@Test(priority=4)
	public void deleteRepo() {
		given()
		.header("Authorization","Bearer "+token)
		.pathParam("user", user)
		.pathParam("title", title)
		.when()
		.delete("https://api.github.com/repos/{user}/{title}")
		.then()
		.statusCode(204)
	    .assertThat()
		.statusLine(Matchers.containsString("No Content"))
		.log().all();
	}
}
