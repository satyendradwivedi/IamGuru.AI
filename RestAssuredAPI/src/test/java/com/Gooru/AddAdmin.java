package com.Gooru;

import static io.restassured.RestAssured.given;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddAdmin {
	// Utility method to load JSON from the resources folder
    private String loadRequestBody(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
    }

    @Test(priority = 1)
    public void testaddvaliduser() throws Exception {
        // Load Base URI for login API from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseUrllogin");

        // Load login request body from a specific file
        String requestBody = loadRequestBody("requestBodylogin.json");

        // Sending POST request to login and validating the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/Login")
                .then()
                .statusCode(200) // Expected status code
                .extract()
                .response();

        // Log the response
        System.out.println("Login Response: " + response.asString());

        // Extract the access token from the login response
        String accessToken = response.jsonPath().getString("result.accessToken");
        System.out.println("Access Token: " + accessToken);

        // Set Base URI for the add admin API
        RestAssured.baseURI = properties.getProperty("baseurladdadmin");

        // Load add admin request body from a specific file
        String rb = loadRequestBody("AddUserByAdmin.json");

        // Sending POST request to add admin with the access token in the header
        Response rs = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken) // Add Authorization header
                .body(rb)
                .when()
                .post("/AdminAddUser")
                .then()
                .statusCode(200) // Expected status code
                .extract()
                .response();

        // Log the response
        System.out.println("Add Admin Response: " + rs.asString());

        // Apply assertion to validate the status code
        Assert.assertEquals(rs.statusCode(), 200, "Status code is not as expected!");
    }

   

	@Test(priority=2)
    public void testMemberloginwithInvaliddata() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseurladdadmin");

        // Load request body from a specific file
        String requestBody = loadRequestBody("requestBodyloginInvalid.json");

        // Sending POST request and validating the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/AdminAddUser")
                .then()
                .statusCode(400) // Expected status code for invalid email
                .extract()
                .response();
        Assert.assertEquals(response.statusCode(),400, "Invalid Status Code");

        // Print response
        System.out.println("Response for invalid: " + response.asString());
    }

    @Test(priority=3)
    public void testLoginMemberWithMissingField() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseurladdadmin");

        // Load request body from a specific file
        String requestBody = loadRequestBody("requestBodyloginMissingfield.json");

        // Sending POST request and validating the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/AdminAddUser")
                .then()
                .statusCode(400) // Expected status code for missing field
                .extract()
                .response();
                Assert.assertEquals(response.asString(), "Enter valid credentials.");
        // Print response
        System.out.println("Response for Missing Field: " + response.asString());
    }

    @Test(priority=4,enabled=true)
    public void testLoginMemberWithEmptyBody() throws Exception {
        // Load Base URI from external properties file
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/config.properties")));
        RestAssured.baseURI = properties.getProperty("baseurladdadmin");

        // Sending POST request with an empty body
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{}") // Empty JSON object
                .when()
                .post("/AdminAddUser")
                .then()
                .statusCode(400) // Expected status code for empty body
                .extract()
                .response();
       Assert.assertEquals(response.asString(), "Enter valid credentials.");
        // Print response
        System.out.println("Response for Empty Body: " + response.asString());
    }
}


