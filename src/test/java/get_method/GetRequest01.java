package get_method;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class GetRequest01 {

    /*
        In API test cases and automation scripts we use "Given", "When", "Then", and "And"

        "Given" : It declares prerequisites
        "When"  : It declares the action which user will perform
        "Then"  : It declares outputs
        "And"   : It can be used after "Given", "When" and "Then" for multiple actions
    */

    /*
        When
			I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/3
		Then
			HTTP Status code should be 200
		And
		    Content Type should be JSON
		And
			Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void get01() {

        // 1. Step: Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/3";

        // 2. Step: Set the expected data(We will learn later)

        // 3. Step: Type automation script to send GET Request
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4. Step: Assert the output
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");

        // How to print content-type, status code, status line, etc. on the console...
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Content-Type: " + response.getContentType());
        System.out.println("Status line: " + response.getContentType());
        System.out.println("All headers:\n" + response.getHeaders());
        System.out.println("Server: " + response.getHeader("Server"));
        System.out.println("Time: " + response.getTime());
    }

}

























