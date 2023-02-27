package utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import testClasses.BaseClass;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class CommonApiActions extends BaseClass {
    private static BufferedReader read_product=null;
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");

    public static RequestSpecification reqSpec;
    public static Boolean flag;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
    Date date = new Date();
    String actualDate = format.format(date);

    public static void validateStatusCode(Response response,int expected) {
        response.then().assertThat().statusCode(expected);
    }

    public static void validateResponseTime(Response response) {
        if(response.getTimeIn(TimeUnit.SECONDS) < 3)
            flag=true;
        Assert.assertTrue(flag);
    }



    public static Response getCall(String endPoint,String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.get(endPoint);
    }

    public static Response postCall(String endPoint, String jsonObject,String pathKey,String pathValue, HashMap<String,String> headers, HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.body(jsonObject).post(endPoint);
    }

    public static Response putCall(String endPoint,String jsonObject,String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.body(jsonObject).put(endPoint);
    }

    public static Response deleteCall(String endPoint,String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.delete(endPoint);
    }

    public static RequestSpecification createRequest(String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws IOException {
        if (authToken == null) {
            if(headers == null && queryParams == null && pathKey==null)
                return requestSpecification().when();
            else if (headers == null && queryParams == null)
                return requestSpecification().when().pathParam(pathKey, pathValue);
            else if (queryParams == null)
                return requestSpecification().when().headers(headers).pathParam(pathKey, pathValue);
            else if (headers == null && pathKey == null)
                return requestSpecification().when().queryParams(queryParams);
            else if (pathKey == null)
                return requestSpecification().when().queryParams(queryParams).headers(headers);
            else if (headers == null)
                return requestSpecification().when().queryParams(queryParams).pathParam(pathKey, pathValue);
            else
                return requestSpecification().when().headers(headers).queryParams(queryParams).pathParam(pathKey, pathValue);
        }
        else {
            if(headers == null && queryParams == null && pathKey==null)
                return requestSpecification().when();
            else if (headers == null && queryParams == null)
                return requestSpecification().when().auth().oauth2(authToken).pathParam(pathKey, pathValue);
            else if (queryParams == null)
                return requestSpecification().when().auth().oauth2(authToken).headers(headers).pathParam(pathKey, pathValue);
            else if (headers == null && pathKey == null)
                return requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams);
            else if (pathKey == null)
                return requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams).headers(headers);
            else if (headers == null)
                return requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams).pathParam(pathKey, pathValue);
            else
                return requestSpecification().when().auth().oauth2(authToken).headers(headers).queryParams(queryParams).pathParam(pathKey, pathValue);
        }
    }

    public static String getAccessTokenForOAuth2(String endpoint, String Username, String Password,String oauthUsername, String oauthPassword, String grantType, String scope, String accessToken){
        try {
            if(grantType.equalsIgnoreCase("password")){
                response = requestSpecification().auth().preemptive().basic(Username,Password)
                        .formParam("grant_type", grantType)
                        .formParam("username", oauthUsername)
                        .formParam("password", oauthPassword)
                        .when()
                        .post(endpoint);
            }else {
                response = requestSpecification().auth().preemptive().basic(Username, Password)
                        .formParam("grant_type", grantType)
                        .formParam("client_id", oauthUsername)
                        .formParam("client_secret", oauthPassword)
                        .formParam("scope", scope)
                        .when()
                        .post(endpoint);
            }
            System.out.println("OAuth Response - " + response.getBody().asString());
            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            return jsonObject.get(accessToken).toString();
        }catch (Exception e){
//            log.error("Failed to fetch Oauth token:" + e);
        }
        return null;
    }
    public void log(String Message) throws IOException {
        File file = new File(System.getProperty("user.dir")+"//logs//logFile.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("Log ->>> "+actualDate+" [ INFO ] "+ Message);
        writer.newLine();
        System.out.println("Log ->>> "+actualDate+" [ INFO ] "+ Message);
        writer.close();
    }

}
