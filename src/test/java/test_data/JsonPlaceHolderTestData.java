package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    Map<String, Object> expectedDataMap = new HashMap<>();

    public Map<String, Object> setUpData() {

        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("completed", false);
        expectedDataMap.put("userId", 1);
        expectedDataMap.put("title", "laboriosam mollitia et enim quasi adipisci quia provident illum");
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        return expectedDataMap;
    }

}
