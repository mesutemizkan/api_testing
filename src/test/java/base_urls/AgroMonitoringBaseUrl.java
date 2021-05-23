package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringBaseUrl {

    // Create RequestSpecification object
    protected RequestSpecification spec;

    // If you use @Before before a method, it means method will executed before all tests
    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();

    }

}
