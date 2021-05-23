package get_method;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest08dt extends DummyBaseUrl {

    /*
     When
         I send GET Request to URL http://dummy.restapiexample.com/api/v1/employees
     Then
         Status code is 200
         1)Print all ids greater than 10 on the console
           Assert that there are 14 ids greater than 10
         2)Print all ages less than 30 on the console
           Assert that maximum age is 23
         3)Print all employee names whose salaries are greater than 350,000
           Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
    */
    @Test
    public void get01() {

        spec.pathParam("first", "employees");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        JsonPath json = response.jsonPath();

        // To filter the output we need to use "Groovy Language"
        List<Integer> idList = json.getList("data.findAll{(it.id)>10}.id");

        // 1)Print all ids greater than 10 on the console
        System.out.println(idList);

        // Assert that there are 14 ids greater than 10
        assertEquals(14, idList.size());

        // 2)Print all ages less than 30 on the console
        List<String> ageList = json.getList("data.findAll{(it.employee_age)<30}.employee_age");
        System.out.println(ageList);
        // Assert that maximum age is 23
        // First sort and take the last element

        // If ages are String when I sorted, it will not give me the correct order(natural order)
        // Create an Integer list and by using for each loop take all the Strings and convert them to integer
        List<Integer> ageListInt = new ArrayList<>();
        for(String w:ageList) {
            ageListInt.add(Integer .valueOf(w));
        }
        Collections.sort(ageListInt);


        Collections.sort(ageList);
        // convert 23 to Integer
        assertEquals(Integer.valueOf(23), ageList.get(ageList.size()-1));

        // 3)Print all employee names whose salaries are greater than 350,000
        List<String> employeeList = json.getList("data.findAll{(it.employee_salary)>350.000}.employee_name");
        System.out.println(employeeList);
        // Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
        assertTrue("The name you search for is not in the list", employeeList.contains("Charde Marshall"));


    }



}




























