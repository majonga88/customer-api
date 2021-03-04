package org.innso.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerWorkflow_Should {

    @Test
    @Order(1)
    void create_message() {

        given()
                .body("{\n" +
                        "  \"date\": \"2021-03-03T14:51:05.789Z\",\n" +
                        "  \"authorName\": \"Jérémie Durand\",\n" +
                        "  \"content\": \"Bonjour, j’ai un problème avec mon nouveau téléphone\",\n" +
                        "  \"channel\": \"MAIL\",\n" +
                        "  \"clientFolderReference\": \"ref01\"\n" +
                        "}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when().post("/messages")
                .then()
                .statusCode(200)
                .body( "authorName", containsString("Jérémie Durand"),
                        "content", containsString("Bonjour, j’ai un problème avec mon nouveau téléphone"));
    }

    @Test
    @Order(2)
    void create_client_folder() {

        given()
                .body("{\n" +
                        "  \"clientName\": \"Jérémie Durand\",\n" +
                        "  \"openedDate\": \"2021-03-03T14:50:47.933Z\",\n" +
                        "  \"reference\": \"ref01\"\n" +
                        "}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when().post("/clientFolders")
                .then()
                .statusCode(200)
                .body( "clientName", containsString("Jérémie Durand"),
                        "reference", containsString("ref01"));
    }

    @Test
    @Order(3)
    void create_replying_message_to_existing_case() {

        given()
                .body("{\n" +
                        "  \"date\": \"2021-03-03T14:51:05.789Z\",\n" +
                        "  \"authorName\": \"Sonia Valentin\",\n" +
                        "  \"content\": \"Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?\",\n" +
                        "  \"channel\": \"MAIL\",\n" +
                        "  \"clientFolderReference\": \"ref01\"\n" +
                        "}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when().post("/messages/ref01")
                .then()
                .statusCode(200)
                .body( "authorName", containsString("Sonia Valentin"),
                        "content", containsString("Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?"));
    }

    @Test
    @Order(4)
    public void update_client_folder() {

        given()
                .when().put("/clientFolders/id/1/reference/KA-18B6")
                .then()
                .statusCode(200)
                .body( "clientName", containsString("Jérémie Durand"),
                        "reference", containsString("KA-18B6"));
    }

    @Test
    @Order(5)
    public void get_all_client_folders() {

        given()
                .when().get("/clientFolders")
                .then()
                .statusCode(200)
                .body("$.size()", is(1),
                        "clientName", contains("Jérémie Durand"),
                        "reference", contains("KA-18B6"));
    }
}