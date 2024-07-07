package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    // Verify if the total record is 20
    @Test
    public void test001() {
        response.body("", hasSize(20));
    }

    // Verify if the name of id = 7015067 is equal to ”Adheesh Kaul”
    @Test
    public void test002() {
        response.body("find{it.id == 7015067}.name", equalTo("Adheesh Kaul"));
    }

    // Check the single ‘Name’ in the Array list (Adheesh Kaul)
    @Test
    public void test003() {
        response.body("name", hasItem("Adheesh Kaul"));
    }

    // Check the multiple ‘Names’ in the ArrayList (Adheesh Kaul, Tanushri Kaur, Anuja Mehrotra )
    @Test
    public void test004() {
        response.body("name", hasItems("Adheesh Kaul", "Tanushri Kaur", "Anuja Mehrotra"));
    }

    // Verify the email of userid = 7015061 is equal “girindra_lld_verma@wiegand.example”
    @Test
    public void test005() {
        response.body("find{it.id == 7015061}.email", equalTo("girindra_lld_verma@wiegand.example"));
    }

    // Verify the status is “Active” of user name is “Adheesh Kaul”
    @Test
    public void test006() {
        response.body("find{it.name == 'Adheesh Kaul'}.status", equalTo("active"));
    }


    // Verify the Gender = male of user name is “Kamalesh Dhawan”
    @Test
    public void test007() {
        response.body("find{it.name == 'Kamalesh Dhawan'}.gender", equalTo("male"));
    }


}
