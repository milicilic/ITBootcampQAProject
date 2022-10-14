package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    @Test
    public void getProductList() {
        RestAssured.baseURI = "https://automationexercise.com/api/";

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("productsList")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "status code should be 200");
        System.out.println(response.getBody().print());
    }
    @Test
    public void getUserAccountDetailByEmail(){
        RestAssured.baseURI = "https://automationexercise.com/api/getUserDetailByEmail";

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("email", "newUser@user.com")
                .when()
                .get()
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "status code should be 200");
        System.out.println(response.getBody().print());
    }



}
