package testClasses;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Carts extends BaseClass {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static String jsonRequestBody;
    public static String key="id";
    public static String getPath="5";
    public static String putPath="7";
    public static String deletePath="5";
    public static ExcelHandler excel = new ExcelHandler("./resources/excelFiles/dataFile1.xlsx");


    public static String putUpdateUserPayload(String jsonDataFilePath){
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.userId", excel.getCellData("cartSheet","userId",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.date", excel.getCellData("cartSheet","date",3), jsonRequestBody);
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
            jsonRequestBody = JsonHandler.editJsonValue("$.userId", excel.getCellData("cartSheet","userId",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.date", excel.getCellData("cartSheet","date",2), jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Test(priority = 1)
    public static void getSingleCart() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing get call for single cart");
            commonApiActions.log("Started executing get call for single cart");
            response = CommonApiActions.getCall(Endpoints.Cart_Id_API,key,getPath,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_CART_SCHEMA"));
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

    @Test(priority = 2)
    public static void getUserCart() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing get call for user cart");
            commonApiActions.log("Started executing get call for user cart");
            response = CommonApiActions.getCall(Endpoints.Cart_UserId_API,key,getPath,null,null,null);
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

    @Test(priority = 3)
    public static void postAddNewCart() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing post call for adding a new cart");
            commonApiActions.log("Started executing post call for adding a new cart");
//            String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
            response = CommonApiActions.postCall(Endpoints.Cart_API, postSingleUserPayload(prop.getProperty("POSTCARTJSONDATA")),null,null,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_CART_SCHEMA"));
            validatePostSingleUserResponse(response);
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "GET Call Executed ");
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
    @Test(priority = 4)
    public static void putUpdateCart() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing put call for updating a cart");
            commonApiActions.log("Started executing put call for updating a cart");
            response = CommonApiActions.putCall(Endpoints.Cart_Id_API, putUpdateUserPayload(prop.getProperty("POSTCARTJSONDATA")),key,putPath,null,null,null);
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
    @Test(priority = 5)
    public static void deleteCart() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing delete call for deleting a user");
            commonApiActions.log("Started executing delete call for deleting a user");
            response = CommonApiActions.deleteCall(Endpoints.Product_Id_API,key,deletePath,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "GET Call Executed ");
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
        Assert.assertEquals(JsonHandler.getJsonValue(res,"userId"), excel.getCellData("cartSheet","userId",2));
        Assert.assertEquals(JsonHandler.getJsonValue(res,"date"), excel.getCellData("cartSheet","date",2));
    }

    public static void validatePutUpdateUserResponse(Response res){
        Assert.assertEquals(JsonHandler.getJsonValue(res,"userId"), excel.getCellData("cartSheet","userId",3));
        Assert.assertEquals(JsonHandler.getJsonValue(res,"date"), excel.getCellData("cartSheet","date",3));
    }
}


