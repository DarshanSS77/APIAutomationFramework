package com.myproject.base;
//common to all class
//Base test father -> test cases -  Son - inheritance

import com.myproject.asserts.AssertAction;
import com.myproject.endpoints.APIConstants;
import com.myproject.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;


public class BaseTest {
    public RequestSpecification requestSpecification;
    public ValidatableResponse validatableResponse;
    public Response response;
    public AssertAction assertAction;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;

    @BeforeTest
    public void setup() {
        payloadManager = new PayloadManager();
        assertAction = new AssertAction();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BSAE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

//        requestSpecification = RestAssured.given()
//                .baseUri(APIConstants.BSAE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();

    }

    public String getToken() {
        requestSpecification = RestAssured.given().baseUri(APIConstants.BSAE_URL)
                .basePath(APIConstants.AUTH_URL);
//        Setting the payload
        response = requestSpecification.contentType(ContentType.JSON).body(payloadManager.setAuthPayload()).post();

//    String extraction
        String token = payloadManager.getTokenFromJson(response.asString());
        return token;
    }
}
