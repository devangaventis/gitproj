package api;

import io.restassured.RestAssured;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class RestAssuredApiTest {

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        RestAssured.baseURI = mockWebServer.url("/").toString();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void getSinglePostShouldReturnExpectedFields() {
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
            .setBody("""
                {
                  "userId": 1,
                  "id": 1,
                  "title": "sample title",
                  "body": "sample body"
                }
                """));

        given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
    }

    @Test
    void listPostsShouldReturnThreeRecords() {
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
            .setBody("""
                [
                  {"id":1,"userId":1,"title":"a","body":"x"},
                  {"id":2,"userId":1,"title":"b","body":"y"},
                  {"id":3,"userId":2,"title":"c","body":"z"}
                ]
                """));

        given()
            .when()
                .get("/posts")
            .then()
                .statusCode(200)
                .body("", hasSize(3));
    }

    @Test
    void createPostShouldReturn201AndPayload() {
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(201)
            .addHeader("Content-Type", "application/json")
            .setBody("""
                {
                  "title": "rest assured demo",
                  "body": "automation example",
                  "userId": 1,
                  "id": 101
                }
                """));

        String requestBody = """
            {
              "title": "rest assured demo",
              "body": "automation example",
              "userId": 1
            }
            """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .body("title", equalTo("rest assured demo"))
                .body("body", equalTo("automation example"))
                .body("userId", equalTo(1));
    }
}
