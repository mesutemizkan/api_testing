package post_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JsonPlaceHolderTestData02;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends JsonPlaceHolderBaseUrl {

    /*
        When
	  		I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
	  		with the request body {
								    "userId": 55,
								    "title": "Tidy your room",
								    "completed": false
								   }
		Then
			Status code is 201
			And response body is like {
									    "userId": 55,
									    "title": "Tidy your room",
									    "completed": false,
									    "id": 201
									  }
    */

    @Test
    public void post01() {

        // 1) Set the URL
        spec.pathParam("first", "todos");

        // 2) Set the expected data
        JsonPlaceHolderTestData02 expectedData = new JsonPlaceHolderTestData02();

        // 3) Send POST Request
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData.expectedDataSetUp()).when().post("/{first}");
        response.prettyPrint();

        // 4) Assert output

        // 1. Way: By using GSON
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.expectedDataSetUp().get("completed"), actualData.get("completed"));
        assertEquals(expectedData.expectedDataSetUp().get("title"), actualData.get("title"));
        assertEquals(expectedData.expectedDataSetUp().get("userId"), actualData.get("userId"));

        // 2. Way: JsonPath with Soft Assertion (Verification) -> 1) Create SoftAssert object - 2) Use assert methods - 3) assertAll()
        // 1) Create SoftAssert object
        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        // 2) User assert methods
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertEquals(json.getBoolean("completed"), expectedData.expectedDataSetUp().get("completed"));
        softAssert.assertEquals(json.getString("title"), expectedData.expectedDataSetUp().get("title"));
        softAssert.assertEquals(json.getInt("userId"), expectedData.expectedDataSetUp().get("userId"));

        // 3) assertAll()
        softAssert.assertAll();

        // 3. Way: By using body method
        response.
                then().
                assertThat().
                statusCode(201).
                body("completed", equalTo(expectedData.expectedDataSetUp().get("completed")),
                        "title", equalTo(expectedData.expectedDataSetUp().get("title")),
                        "userId", equalTo(expectedData.expectedDataSetUp().get("userId")));

    }




}
























