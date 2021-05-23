package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuappTestData {

    Map<String, Object> bookingDatesMap = new HashMap<>();
    Map<String, Object> bookingDetailsMap = new HashMap<>();

    public Map<String, Object> setUpData() {

        bookingDatesMap.put("checkin", "2016-05-10" );
        bookingDatesMap.put("checkout", "2019-06-03" );
        bookingDetailsMap.put("firstname", "Susan");
        bookingDetailsMap.put("lastname", "Wilson");
        bookingDetailsMap.put("totalprice", 457);
        bookingDetailsMap.put("depositpaid", true);
        bookingDetailsMap.put("bookingdates", bookingDatesMap);
        bookingDetailsMap.put("additionalneeds", "Breakfast");

        return bookingDetailsMap;
    }


}
