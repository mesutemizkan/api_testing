package get_method;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest02 extends HerokuAppBaseUrl {

    /*
        When
			I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/1001
	    Then
		    HTTP Status code should be 404
		And
		    Status Line should be HTTP/1.1 404 Not Found
		And
		    Response body contains "Not Found"
		And
		    Response body does not contain "TechProEd"]
		And
		    Server is "Cowboy"
    */
    @Test
    public void get02() {
        // 1. Step: Set the url
//        String url = "https://restful-booker.herokuapp.com/booking/1001";        -->     This is not recommended
        spec.pathParams("first", "booking",
                  "second", 1001);

        // 2. Step: Set the expected data

        // 3. Step: Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Step: Assert the output
        response.
                then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        // assertTrue(true) --> Pass | assertTrue(false) --> Fail
        assertTrue(response.asString().contains("Not Found"));

        // assertTrue(false) --> Pass | assertTrue(true) --> Fail
        assertFalse(response.asString().contains("TechProEd"));

        // assertEquals(firstParameter, secondParameter); If second parameter matches with the second parameter --> Pass
        assertEquals(response.getHeader("Server"), "Cowboy");

        // How to see status code on the console
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Status line: " + response.getStatusLine());

    }

}
