package testClasses;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Users extends BaseClass {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static String jsonRequestBody;
    public static String key="id";
    public static String getPath="1";
    public static String putPath="7";
    public static String deletePath="6";
    public static ExcelHandler excel = new ExcelHandler("./resources/excelFiles/dataFile1.xlsx");


    public static String putUpdateUserPayload(String jsonDataFilePath){
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.email", excel.getCellData("userSheet","email",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.username", excel.getCellData("userSheet","username",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.password", excel.getCellData("userSheet","password",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.name.firstname", excel.getCellData("userSheet","firstname",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.name.lastname", excel.getCellData("userSheet","lastname",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.city", excel.getCellData("userSheet","city",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.street", excel.getCellData("userSheet","street",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.number", excel.getCellData("userSheet","number",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.zipcode", excel.getCellData("userSheet","zipcode",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.phone", excel.getCellData("userSheet","phone",3), jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String postSingleUserPayload(String jsonDataFilePath){
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.email", excel.getCellData("userSheet","email",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.username", excel.getCellData("userSheet","username",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.password", excel.getCellData("userSheet","password",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.name.firstname", excel.getCellData("userSheet","firstname",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.name.lastname", excel.getCellData("userSheet","lastname",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.city", excel.getCellData("userSheet","city",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.street", excel.getCellData("userSheet","street",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.number", excel.getCellData("userSheet","number",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.address.zipcode", excel.getCellData("userSheet","zipcode",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.phone", excel.getCellData("userSheet","phone",2), jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public static void getSingleUser() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        Endpoints ep = new Endpoints();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing get call for single user");
            commonApiActions.log("Started executing get call for single user");
            response = CommonApiActions.getCall(ep.Users_Id_API,key,getPath,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_USER_SCHEMA"));
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "GET Call Executed ");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Status code is "+ response.getStatusCode());
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response Time is " + response.getTimeIn(TimeUnit.SECONDS)+" seconds.");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response ->> "+ response.asString());
            commonApiActions.log("GET Call Executed "+ response.asString());
        }
        catch (Exception e){
            ExtentFactory.getInstance().getExtent().log(Status.INFO, e);
            commonApiActions.log("FATAL: "+e);
        }
    }

    @Test
    public static void getLimitedUsers() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        Endpoints ep = new Endpoints();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing get call for list of users");
            commonApiActions.log("Started executing get call for list of users");
            HashMap<String,String> m= new HashMap<>();
            m.put("limit","5");
            response = CommonApiActions.getCall(ep.Users_API,null,null,null,m,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "GET Call Executed ");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Status code is "+ response.getStatusCode());
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response Time is " + response.getTimeIn(TimeUnit.SECONDS)+" seconds.");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response ->> "+ response.asString());
            commonApiActions.log("GET Call Executed "+ response.asString());
        }
        catch (Exception e){
            ExtentFactory.getInstance().getExtent().log(Status.INFO, e);
            commonApiActions.log("FATAL: "+e);
        }
    }

    @Test
    public static void postAddUser() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        Endpoints ep = new Endpoints();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing post call for adding a new user");
            commonApiActions.log("Started executing post call for adding a new user");
//            String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
            response = CommonApiActions.postCall(ep.Users_API, postSingleUserPayload(prop.getProperty("POSTUSERSJSONDATA")),null,null,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_USER_SCHEMA"));
            validatePostSingleUserResponse(response);
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "POST Call Executed ");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Status code is "+ response.getStatusCode());
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response Time is " + response.getTimeIn(TimeUnit.SECONDS)+" seconds.");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response ->> "+ response.asString());
            commonApiActions.log("POST Call Executed "+ response.asString());
        }
        catch (Exception e){
            ExtentFactory.getInstance().getExtent().log(Status.INFO, e);
            commonApiActions.log("FATAL: "+e);
        }
    }
    @Test
    public static void putUpdateUser() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        Endpoints ep = new Endpoints();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing put call for updating a user");
            commonApiActions.log("Started executing put call for updating a user");
            response = CommonApiActions.putCall(ep.Users_Id_API, putUpdateUserPayload(prop.getProperty("POSTUSERSJSONDATA")),key,putPath,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            validatePutUpdateUserResponse(response);
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "PUT Call Executed ");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Status code is "+ response.getStatusCode());
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response Time is " + response.getTimeIn(TimeUnit.SECONDS)+" seconds.");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response ->> "+ response.asString());
            commonApiActions.log("PUT Call Executed "+ response.asString());
        }
        catch (Exception e){
            ExtentFactory.getInstance().getExtent().log(Status.INFO, e);
            commonApiActions.log("FATAL: "+e);
        }
    }
    @Test
    public static void deleteUser() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        Endpoints ep = new Endpoints();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing delete call for deleting a user");
            commonApiActions.log("Started executing delete call for deleting a user");
            response = CommonApiActions.deleteCall(ep.Users_Id_API,key,deletePath,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "DELETE Call Executed ");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Status code is "+ response.getStatusCode());
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response Time is " + response.getTimeIn(TimeUnit.SECONDS)+" seconds.");
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Response ->> "+ response.asString());
            commonApiActions.log("DELETE Call Executed");
        }
        catch (Exception e)
        {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, e);
            commonApiActions.log("FATAL: "+e);
        }
    }

    public static void validatePostSingleUserResponse(Response res){
        Assert.assertEquals(JsonHandler.getJsonValue(res,"email"), excel.getCellData("userSheet","email",2));
        Assert.assertEquals(JsonHandler.getJsonValue(res,"phone"), excel.getCellData("userSheet","phone",2));
    }

    public static void validatePutUpdateUserResponse(Response res){
        Assert.assertEquals(JsonHandler.getJsonValue(res,"email"), excel.getCellData("userSheet","email",3));
        Assert.assertEquals(JsonHandler.getJsonValue(res,"phone"), excel.getCellData("userSheet","phone",3));
    }
}


