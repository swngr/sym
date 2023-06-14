package org.fw.utils;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.fw.managers.MobileDriverManager;

import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ApiCalls {

    private static final String ENV = PropertyUtils.getProperty(Constants.BACKEND_ENV);
    private static String BASE_URL;
    private static final String API_USERNAME = UserDataUtils.getUserData(Constants.API_USERNAME);
    private static final String API_PASSWORD = UserDataUtils.getUserData(Constants.API_PASSWORD);
    private static final String API_CLIENT_ID = UserDataUtils.getUserData(Constants.API_CLIENT_ID);

    private static final JsonObject JSON_BODY_APPOINTMENT = JsonDataUtils.getJsonObject();

    // Return the user: auto_tester access_token_password
    private static String authToken() {
        BASE_URL = "https://auth-" + ENV + ".symplast.com/connect/token";
        return
                given().
                        header("Content-Type", "application/x-www-form-urlencoded").
                        formParam("grant_type", "password").
                        formParam("username", API_USERNAME). // Login as auto_tester
                        formParam("password", API_PASSWORD).
                        formParam("client_id", API_CLIENT_ID).
                        when().
                        post(BASE_URL).
                        then().
                        assertThat().
                        statusCode(200).
                        extract().path("access_token");
    }

    private static String getConversationId(String authToken, String patientId, String conversationName) {
        BASE_URL = "https://practiceapp-" + ENV + ".symplast.com";
        Response response =
                given().
                        relaxedHTTPSValidation().
                        baseUri(BASE_URL).
                        header("Accept", "text/plain").
                        header("Authorization", "Bearer " + authToken).
                        queryParam("includeInactive", "false").
                        queryParam("batchSize", "10"). // Only the first 10 conversations
                        queryParam("skipValue", "0").
                        when().
                        get("/Conversations/MyConversations").
                        then().
                        assertThat().
                        statusCode(200).
                        extract().
                        response();

        return response.jsonPath().get("result.find { it.title == '"+conversationName+"' }.id");
    }

    private static void sendConversationMessage(String authToken, String conversationId, String conversationMessage) {
        BASE_URL = "https://securemessagingapi-" + ENV + ".symplast.com";
        given().
                relaxedHTTPSValidation().
                baseUri(BASE_URL).
                pathParam("conversationId", conversationId).
                header("Content-Type", "application/x-www-form-urlencoded").
                header("Authorization", "Bearer " + authToken).
                formParam("messageText", conversationMessage).
                when().
                post("/api/Conversations/{conversationId}/Message").
                then().
                assertThat().
                statusCode(201);
    }

    private static boolean isAppointmentCreated(){

        if(anythingWrong()){return false;}
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        BASE_URL = "https://practiceapp-" + ENV + ".symplast.com";

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String  startDate = formatter.format(cal.getTime());

        Response response =
                given().
                        baseUri(BASE_URL).
                        header("Content-Type", "application/json; charset=utf-8").
                        header("Authorization", "Bearer " + authToken()).
                        param("dateFrom", startDate).
                        param("pageNumber", 0).
                        param("pageSize", 3).
                        param("locationId", UserDataUtils.getIntUserData(Constants.LOCATION_ID)).
                        param("showCancelled", false).
                        param("showVideoVisitOnly", false).
                        param("includePatientInformation", true).
                        when().
                        get("/Appointments").
                        then().
                        assertThat().
                        statusCode(200).
                        extract().response();

        return response.jsonPath().getList("result").size() > 0;
    }

    public static void createAppointment() {

        if(anythingWrong()){return;}
        if(isAppointmentCreated()){return;}
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        BASE_URL = "https://practiceapp-" + ENV + ".symplast.com";

        // Get today date

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        cal.add(Calendar.HOUR_OF_DAY, +1);
        String endDate = formatter.format(cal.getTime());

        cal.add(Calendar.HOUR_OF_DAY, -1);
        String  startDate = formatter.format(cal.getTime());

        // Get the parametrized data
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("endDate", endDate);
        jsonMap.put("locationId", UserDataUtils.getIntUserData(Constants.LOCATION_ID));
        jsonMap.put("startDate", startDate);
        jsonMap.put("statusId", 1);
        jsonMap.put("patientId", UserDataUtils.getUserData(Constants.PATIENT_ID_JOHN_CONNOR));

        // Insert the parametrized data into the json body

        JsonObject jsonObject = JSON_BODY_APPOINTMENT.getAsJsonObject();

        for(Map.Entry<String,Object> entry : jsonMap.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue().toString());
        }

        given().
                baseUri(BASE_URL).
                header("Content-Type", "application/json; charset=utf-8").
                header("Authorization", "Bearer " + authToken()).
                body(jsonObject.toString()).
                when().
                post("/Appointments").
                then().
                assertThat().
                statusCode(200);
    }


    private static boolean isApptCreatedWithStatus(String apptStatus){

        String apptStatusToUpper = apptStatus.toUpperCase();

        if(anythingWrong()){return false;}
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        BASE_URL = "https://practiceapp-" + ENV + ".symplast.com";

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String  startDate = formatter.format(cal.getTime());


        int appStatusId = 0;
        switch (apptStatusToUpper) {
            case "PENDING" :
                appStatusId = 1;
                break;
            case "CHECKED IN":
                appStatusId = 2;
                break;
            default:
                appStatusId = 0;
                break;
        }

        Response res =
                RestAssured.
                        given().
                                baseUri(BASE_URL).
                        header("Content-Type", "application/json; charset=utf-8").
                        header("Authorization", "Bearer " + authToken()).
                        param("dateFrom", startDate).
                        param("pageNumber", 0).
                        param("pageSize", 3).
                        param("statusId",appStatusId).
                        param("locationId", UserDataUtils.getIntUserData(Constants.LOCATION_ID)).
                        param("showCancelled", false).
                        param("showVideoVisitOnly", false).
                        param("includePatientInformation", true).
                        when().
                        get("/Appointments/");
        JsonPath js = res.jsonPath();
        List<Object> values = js.getList("result.findAll { it.appointmentStatus == \"" + appStatusId + "\" } providers.name");
        return values.size()>0;

    }

    public static void createAppointmentWithStatusAndLocation(String apptStatus, String apptLocation) {

        String apptStatusToUpper = apptStatus.toUpperCase();
        String apptLocationToUpper = apptLocation.toUpperCase();

        if(anythingWrong()){return;}
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        BASE_URL = "https://practiceapp-" + ENV + ".symplast.com";

        // Get today date

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        cal.add(Calendar.HOUR_OF_DAY, -5);
        String endDate = formatter.format(cal.getTime());

        cal.add(Calendar.HOUR_OF_DAY, -2);
        String  startDate = formatter.format(cal.getTime());

        // Get the parametrized data

        int apptStatusId = 0;
        switch (apptStatusToUpper) {
            case "PENDING" :
                apptStatusId = 1;
                break;
            case "CHECKED IN":
                apptStatusId = 2;
                break;
            default:
                apptStatusId = 0;
                break;
        }

        int locationId = 0;
        switch (apptLocationToUpper) {
            case "AUTO TEST ROOM" :
                locationId = 125;
                break;
            case "AUTO TEST SPA":
                locationId = 127;
                break;
            default:
                locationId = 0;
                break;
        }

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("endDate", endDate);
        jsonMap.put("locationId", locationId);
        jsonMap.put("startDate", startDate);
        jsonMap.put("patientId", UserDataUtils.getUserData(Constants.PATIENT_ID_TONY_STARK));
        jsonMap.put("statusId", apptStatusId);

        // Insert the parametrized data into the json body

        JsonObject jsonObject = JSON_BODY_APPOINTMENT.getAsJsonObject();

        for(Map.Entry<String,Object> entry : jsonMap.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue().toString());
        }

        given().
                baseUri(BASE_URL).
                header("Content-Type", "application/json; charset=utf-8").
                header("Authorization", "Bearer " + authToken()).
                body(jsonObject.toString()).
                when().
                post("/Appointments").
                then().
                assertThat().
                statusCode(200);
    }

    public static void sendMessage(String patientId, String conversationName, String conversationMessage) {
        if(anythingWrong()){return;}
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        String authToken = authToken();
        String conversationId = getConversationId(authToken, patientId, conversationName);
        sendConversationMessage(authToken, conversationId, conversationMessage);
    }

    private static boolean anythingWrong(){
        return (MobileDriverManager.getMobileDriver() == null);
    }

}
