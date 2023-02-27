package testClasses;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import utilities.PropertiesFileHandler;

import java.io.IOException;

//This is base class
public class BaseClass {
    public static RequestSpecification requestSpecification;
    public static Response response = null;

    @BeforeClass
    public static RequestSpecification requestSpecification() throws IOException {
        PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
        String BaseUrl = prop.getProperty("baseUrl");
//        String BaseUrl = prop.getProperty("baseUrl1");
        if (requestSpecification == null) {
            requestSpecification = new RequestSpecBuilder().setBaseUri(BaseUrl)
//            requestSpecification = new RequestSpecBuilder().setBaseUri(BaseUrl1)
                    .setContentType(ContentType.JSON)
                    .build();
            return RestAssured.given().spec(requestSpecification);
        }
        return RestAssured.given().spec(requestSpecification);
    }

}