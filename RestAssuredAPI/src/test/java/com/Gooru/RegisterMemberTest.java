package com.Gooru;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RegisterMemberTest {

    // Utility method to load JSON from the resources folder
    private String loadRequestBody(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
    }

    @Test(priority=1)
    public void testRegisterMemberwithvaliddata() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseUrl");

        // Load request body from a specific file
        String requestBody = loadRequestBody("requestBody.json");

        // Sending POST request and validating the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/RegisterMember")
                .then()
                .statusCode(200) // Expected status code
                .extract()
                .response();
        System.out.println("Response: " + response.asString());
        //Apply assertion
        Assert.assertEquals(response.statusCode(), 200, "Status code is not as expected!" );

        // Print response
       
       
    }

    @Test(priority=2,enabled=false)
    public void testRegisterMemberWithInvalidEmail() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseUrl");

        // Load request body from a specific file
        String requestBody = loadRequestBody("requestbodyexistingemail.json");

        // Sending POST request and validating the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/RegisterMember")
                .then()
                .statusCode(500) // Expected status code for invalid email
                .extract()
                .response();

        // Print response
        System.out.println("Response for existing Email: " + response.asString());
    }

    @Test(priority=3,enabled=false)
    public void testRegisterMemberWithMissingField() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseUrl");

        // Load request body from a specific file
        String requestBody = loadRequestBody("requestbodymissingfield.json");

        // Sending POST request and validating the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/RegisterMember")
                .then()
                .statusCode(400) // Expected status code for missing field
                .extract()
                .response();

        // Print response
        System.out.println("Response for Missing Field: " + response.asString());
    }

    @Test(priority=4,enabled=false)
    public void testRegisterMemberWithEmptyBody() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseUrl");

        // Sending POST request with an empty body
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{}") // Empty JSON object
                .when()
                .post("/RegisterMember")
                .then()
                .statusCode(400) // Expected status code for empty body
                .extract()
                .response();

        // Print response
        System.out.println("Response for Empty Body: " + response.asString());
    }
}
