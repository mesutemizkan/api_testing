package post_method;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData02;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostRequest01 extends HerokuAppBaseUrl {

    /*
        When
	 		I send POST Request to the Url https://restful-booker.herokuapp.com/booking
	 		with the request body {
								    "firstname": "Selim",
								    "lastname": "Ak",
								    "totalprice": 11111,
								    "depositpaid": true,
								    "bookingdates": {
								        "checkin": "2020-09-09",
								        "checkout": "2020-09-21"
								     }
								  }
	 	Then
	 		Status code is 200
	 		And response body should be like {
											    "bookingid": 11,
											    "booking": {
											        "firstname": "Selim",
											        "lastname": "Ak",
											        "totalprice": 11111,
											        "depositpaid": true,
											        "bookingdates": {
											            "checkin": "2020-09-09",
											            "checkout": "2020-09-21"
											        }
											    }
											 }
    */
    @Test
    public void get01() {

        // 1) Set the URL
        spec.pathParam("first", "booking");

        // 2) Set the expected data
        HerokuAppTestData02 expectedData = new HerokuAppTestData02();

        // 3) Send POST request
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData.expectedDataSetUp()).when().post("/{first}");
        response.prettyPrint();


        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        // 4) Assert the output
        // 1. Way
        assertEquals(expectedData.expectedDataSetUp().get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.expectedDataSetUp().get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.expectedDataSetUp().get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.expectedDataSetUp().get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedData.bookingDatesSetUp().get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedData.bookingDatesSetUp().get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

        // 2. Way
        JsonPath json = response.jsonPath();

        assertEquals(expectedData.expectedDataSetUp().get("firstname"), json.getString("booking.firstname"));
        assertEquals(expectedData.expectedDataSetUp().get("lastname"), json.getString("booking.lastname "));
        assertEquals(expectedData.expectedDataSetUp().get("totalprice"), json.getInt("booking.totalprice "));
        assertEquals(expectedData.expectedDataSetUp().get("depositpaid"), json.getBoolean("booking.depositpaid "));
        assertEquals(expectedData.bookingDatesSetUp().get("checkin"), json.getString("booking.bookingdates.checkin "));
        assertEquals(expectedData.bookingDatesSetUp().get("checkout"), json.getString("booking.bookingdates.checkout "));

    }




}






























