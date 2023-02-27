package testClasses;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.joda.time.Seconds;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Products extends BaseClass {
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
            jsonRequestBody = JsonHandler.editJsonValue("$.title", excel.getCellData("productSheet","title",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.price", excel.getCellData("productSheet","price",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.description", excel.getCellData("productSheet","description",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.image", excel.getCellData("productSheet","image",3), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.category", excel.getCellData("productSheet","category",3), jsonRequestBody);
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
            jsonRequestBody = JsonHandler.editJsonValue("$.title", excel.getCellData("productSheet","title",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.price", excel.getCellData("productSheet","price",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.description", excel.getCellData("productSheet","description",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.image", excel.getCellData("productSheet","image",2), jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.category", excel.getCellData("productSheet","category",2), jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public static void getSingleProduct() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing get call for single product");
            commonApiActions.log("Started executing get call for single product");
            response = CommonApiActions.getCall(Endpoints.Product_Id_API,key,getPath,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_PRODUCT_SCHEMA"));
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
    public static void getLimitedProducts() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing get call for list of products");
            commonApiActions.log("Started executing get call for list of products");
            HashMap<String,String> m= new HashMap<>();
            m.put("limit","3");
            response = CommonApiActions.getCall(Endpoints.Product_API,null,null,null,m,null);
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
    public static void postAddProduct() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing post call for adding a new product");
            commonApiActions.log("Started executing post call for adding a new product");
//            String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
            response = CommonApiActions.postCall(Endpoints.Product_API, postSingleUserPayload(prop.getProperty("POSTPRODUCTJSONDATA")),null,null,null,null,null);
            CommonApiActions.validateStatusCode(response, 200);
            CommonApiActions.validateResponseTime(response);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_PRODUCT_SCHEMA"));
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
    public static void putUpdateProduct() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing put call for updating a user");
            commonApiActions.log("Started executing put call for updating a user");
            response = CommonApiActions.putCall(Endpoints.Product_Id_API, putUpdateUserPayload(prop.getProperty("POSTPRODUCTJSONDATA")),key,putPath,null,null,null);
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
    public static void deleteProduct() throws IOException {
        CommonApiActions commonApiActions = new CommonApiActions();
        try {
            ExtentFactory.getInstance().getExtent().log(Status.INFO, "Started executing delete call for deleting a product");
            commonApiActions.log("Started executing delete call for deleting a product");
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
        Assert.assertEquals(JsonHandler.getJsonValue(res,"title"),excel.getCellData("productSheet","title",2));
        Assert.assertEquals(JsonHandler.getJsonValue(res,"category"),excel.getCellData("productSheet","category",2));
    }

    public static void validatePutUpdateUserResponse(Response res){
        Assert.assertEquals(JsonHandler.getJsonValue(res,"title"),excel.getCellData("productSheet","title",3));
        Assert.assertEquals(JsonHandler.getJsonValue(res,"category"),excel.getCellData("productSheet","category",3));
    }
}


