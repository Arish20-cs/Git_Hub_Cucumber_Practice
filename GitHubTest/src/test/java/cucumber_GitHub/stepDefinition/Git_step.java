package cucumber_GitHub.stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import cucumber_GitHub.pojo_classes.Repo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Git_step {
	static String token="your_token";
	String user="Arish20-cs";
	Response res;	
	@Given("set the base url")
	public void setBaseUri() {
		RestAssured.baseURI="https://api.github.com";
	}
	@When("I send post request to {string}")
	public void createRepo(String endpoint,DataTable table) {
		Map<String,String> data=table.asMap();
		Repo repo=new Repo();
		repo.setName(data.get("title"));
		repo.setDescription(data.get("description1"));
		res=RestAssured.
				given()
				.auth().oauth2(token)
		        .contentType(ContentType.JSON)
		        .body(repo)
		        .post(endpoint);
		res.then().log().all();
	}
	@When("I send get request with {string} to {string}")
	public void getRepo(String name, String endpoint) {
		res=RestAssured.
				given()
				.auth().oauth2(token)
				.pathParam("user", user)
				.pathParam("name", name)
				.get(endpoint+"/{user}/{name}");
	}
	@When("I send patch request with {string} to {string}")
	public void updateRepo(String name, String endpoint) {
		HashMap<String,Object> repo=new HashMap<>();
		repo.put("private",true);
		res=RestAssured.
				given()
				.auth().oauth2(token)
				.body(repo)
				.pathParam("user", user)
				.pathParam("name", name)
				.patch(endpoint+"/{user}/{name}");
	}
	@When("I send delete request with {string} to {string}")
	public void deleteRepo(String name, String endpoint) throws InterruptedException {
		Thread t = new Thread();
		t.sleep(5000);
		res=RestAssured.
				given()
				.auth().oauth2(token)
				.pathParam("user", user)
				.pathParam("name", name)
				.delete(endpoint+"/{user}/{name}");
	}
	@Then("response should contain status as {int}")
	public void validateStatus(int status) {
		res.then().statusCode(status);
	}
	@Then("check visibility as {string}")
	public void checkVisibility(String value) {
		boolean val=Boolean.parseBoolean(value);
		boolean org=res.jsonPath().getBoolean("private");
		Assert.assertEquals(val, org);
	}
}
