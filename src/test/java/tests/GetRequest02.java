package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest02 {

    /*
        When I send a GET request to REST API URL
        "https://api-techproed-test.herokuapp.com/courses/608bb976c9e4a800151ab367"
        And content-type is “application/JSON”
        Then
        HTTP Status Code should be 200
        And Response format should be "application/JSON"
           for the course whose id is "608bb976c9e4a800151ab367"
        And "code" should be "WP100"
        And "image" should be "wordpress.jpg"
        And English "title" should be "Wordpress"
        And Turkish "shortDescription"  should be "Wordpress in nasıl kullanılacağını öğreneceğiz"
     */

    @Test
    public void get01() {

        // 1) Set the URL
        String url = "https://api-techproed-test.herokuapp.com/courses/608bb976c9e4a800151ab367";

        // 2) Set the expected data (we will learn later)


        // 3) Send the request
        Response response = given().
                                accept("application/json").
                            when().
                                get(url);
        response.prettyPrint();

        // 4) Assertion
        response.
                then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("code", equalTo("WP100")).
                    body("image", equalTo("wordpress.jpg")).
                    body("title.en", equalTo("Wordpress")).
                    body("shortDescription.tr",equalTo("Wordpress in nasıl kullanılacağını öğreneceğiz"));

        /*
            If you use body methods more than one, it will work like hard assertion(not execute after first fail)
         */
    }

    // 1. Method: Use body method
    @Test
    public void get02() {
        // 1) Set the URL
        String url = "https://api-techproed-test.herokuapp.com/courses/608bb976c9e4a800151ab367";

        // 2) Set the expected data (we will learn later)


        // 3) Send the request
        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        // Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("code", equalTo("WP100"),
                        "image", equalTo("wordpress.jpg"),
                        "title.en", equalTo("Wordpress"),
                        "shortDescription.tr", equalTo("Wordpress in nasıl kullanılacağını öğreneceğiz"));
        /*
            If you use body methods once, it will work like soft assertion. Because it cannot stop execution in the
            middle of the method.
         */
    }

    /*
        2. Way: Use assert methods
           Hard Assertion: We have three methods
                a) assertEquals(expected, actual)
                b) assertTrue(boolean)  -> pass if condition is true
                c) assertFalse(boolean) -> pass if condition is false
     */
    @Test
    public void get03() {

        // 1. Step: Set the url
        String url = "https://api-techproed-test.herokuapp.com/courses/608bb976c9e4a800151ab367";

        // 2. Step: Set the expected data

        // 3. Step: Send the request
        Response response = given().accept(ContentType.JSON).when().get(url);
        response.prettyPrint();

        // 4. Step: Assertion
        assertEquals(200, response.getStatusCode());
        assertEquals("application/JSON; charset=utf-8", response.getContentType());
        assertTrue(response.asString().contains("WP100"));
        assertTrue(response.asString().contains("wordpress.jpg"));
        assertFalse(response.asString().contains("xxx")); // Test Passes
        assertTrue(response.asString().contains("Wordpress in nasıl kullanılacağını öğreneceğiz"));

        /*
            For this test case, using second way is not recommended. Because you are just checking
            if response body contains a String. You don't check equality like code:"WP100"...etc
        */
    }

    /*
       3. Way: Use Soft Assertion:
          There are three steps in soft assertion
                1. Create SoftAssert class to create object (SoftAssert softAssert = new SoftAssert()):
                2. Type assertions: assertEquals(actual, expected), assertTrue(boolean), assertFalse(boolean)
                3. Use assertAll(): If you don't use assertALl() your test will pass but it will be not be meaningful to test
    */
    @Test
    public void get04() {
        // 1. Step: Set the url
        String url = "https://api-techproed-test.herokuapp.com/courses/608bb976c9e4a800151ab367";

        // 2. Step: Set the expected data

        // 3. Step: Send the request
        Response response = given().accept(ContentType.JSON).when().get(url);
        response.prettyPrint();

        // 4. Step: Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "status code is wrong");// you can add messages for failure
        softAssert.assertEquals(response.getContentType(), "application/JSON; charset=utf-8");
        softAssert.assertTrue(response.asString().contains("WP100"));
        softAssert.assertTrue(response.asString().contains("wordpress.jpg"));
        softAssert.assertTrue(response.asString().contains("Wordpress in nasıl kullanılacağını öğreneceğiz"));

        softAssert.assertAll();
    }



}















