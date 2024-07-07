package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

    // Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("", hasSize(25));
    }

    // Verify the if the title of id = 139909 is equal to ”Cubicularis denego acies ad expedita assumenda concedo eveniet.”
    @Test
    public void test002() {
        response.body("find{it.id == 139909}.title", equalTo("Cubicularis denego acies ad expedita assumenda concedo eveniet."));
    }

    // Check the single user_id in the Array list (7015118)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7015118));
    }

    // Check the multiple ids in the ArrayList (139907, 139906, 139905)
    @Test
    public void test004() {
        response.body("user_id", hasItems(7015123, 7015117, 7015118));
    }

    // Verify the body of userid = 7015118 is equal "Aspicio civis vigor. Calco patior curis. Bardus curo corroboro. Amplitudo abduco audacia. Corrigo non magni. Quo tabesco deprecator. Tamisium vapulus crur. Voluptas odit ducimus. Aureus adicio strues. Aiunt caries conor. Cruciamentum adeo tamdiu. Apostolus despecto templum. Cupiditate vox antiquus. Causa argumentum dignissimos. Vulticulus arbitro capio. Curia caute crebro. Vinco bibo umerus. Voluptatem aut tracto. Vergo taedium validus."
    @Test
    public void test005() {
        response.body("find{it.user_id == 7015118}.body", equalTo("Aspicio civis vigor. Calco patior curis. Bardus curo corroboro. Amplitudo abduco audacia. Corrigo non magni. Quo tabesco deprecator. Tamisium vapulus crur. Voluptas odit ducimus. Aureus adicio strues. Aiunt caries conor. Cruciamentum adeo tamdiu. Apostolus despecto templum. Cupiditate vox antiquus. Causa argumentum dignissimos. Vulticulus arbitro capio. Curia caute crebro. Vinco bibo umerus. Voluptatem aut tracto. Vergo taedium validus."));
    }


}
