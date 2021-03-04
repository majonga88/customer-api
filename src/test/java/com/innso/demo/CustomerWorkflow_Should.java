package com.innso.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerWorkflow_Should {

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    void create_message() throws ExecutionException, InterruptedException, URISyntaxException, FileNotFoundException {

        //create_message
        HttpRequest requestOne = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/messages"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(ClassLoader.getSystemResource("jeremiemessage.json").toURI())))
                .build();
        String responseOne = HttpClient.newHttpClient().sendAsync(requestOne, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).get();

        Assertions.assertEquals("{\"id\":1,\"date\":\"2021-03-03\",\"authorName\":\"Jérémie Durand\",\"content\":\"Bonjour, j’ai un problème avec mon nouveau téléphone\",\"channel\":\"MAIL\"}", responseOne);


    }

    @Test
    @Order(2)
    void create_client_folder() throws ExecutionException, InterruptedException, URISyntaxException, FileNotFoundException {

        //create_message
        HttpRequest requestTwo = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/clientFolders"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(ClassLoader.getSystemResource("clientfolder.json").toURI())))
                .build();
        String responseTwo = HttpClient.newHttpClient().sendAsync(requestTwo, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).get();

        Assertions.assertEquals("{\"id\":1,\"clientName\":\"Jérémie Durand\",\"openedDate\":\"2021-03-03\",\"reference\":\"ref01\",\"messages\":[{\"id\":1,\"date\":\"2021-03-03\",\"authorName\":\"Jérémie Durand\",\"content\":\"Bonjour, j’ai un problème avec mon nouveau téléphone\",\"channel\":\"MAIL\"}]}", responseTwo);
    }

    @Test
    @Order(3)
    void create_replying_message_to_existing_case() throws ExecutionException, InterruptedException, URISyntaxException, FileNotFoundException {

        HttpRequest requestThree = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/messages/ref01"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(ClassLoader.getSystemResource("soniamessage.json").toURI())))
                .build();
        String responseThree = HttpClient.newHttpClient().sendAsync(requestThree, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).get();

        Assertions.assertEquals("{\"id\":2,\"date\":\"2021-03-03\",\"authorName\":\"Sonia Valentin\",\"content\":\"Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?\",\"channel\":\"MAIL\"}", responseThree);
    }

    @Test
    @Order(4)
    public void update_client_folder() throws ExecutionException, InterruptedException, URISyntaxException, FileNotFoundException {

        HttpRequest requestFour = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/clientFolders/id/1/reference/KA-18B6"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get(ClassLoader.getSystemResource("clientfolderupdated.json").toURI())))
                .build();
        String responseFour = HttpClient.newHttpClient().sendAsync(requestFour, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).get();

        Assertions.assertEquals("{\"id\":1,\"clientName\":\"Jérémie Durand\",\"openedDate\":\"2021-03-03\",\"reference\":\"KA-18B6\",\"messages\":[{\"id\":1,\"date\":\"2021-03-03\",\"authorName\":\"Jérémie Durand\",\"content\":\"Bonjour, j’ai un problème avec mon nouveau téléphone\",\"channel\":\"MAIL\"},{\"id\":2,\"date\":\"2021-03-03\",\"authorName\":\"Sonia Valentin\",\"content\":\"Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?\",\"channel\":\"MAIL\"}]}", responseFour);
    }

    @Test
    @Order(5)
    public void get_all_client_folders() throws ExecutionException, InterruptedException {

        HttpRequest requestFive = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/clientFolders"))
                .header("Content-Type", "application/json")
                .GET().build();
        String responseFive = HttpClient.newHttpClient().sendAsync(requestFive, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).get();

        Assertions.assertEquals("[{\"id\":1,\"clientName\":\"Jérémie Durand\",\"openedDate\":\"2021-03-03\",\"reference\":\"KA-18B6\",\"messages\":[{\"id\":1,\"date\":\"2021-03-03\",\"authorName\":\"Jérémie Durand\",\"content\":\"Bonjour, j’ai un problème avec mon nouveau téléphone\",\"channel\":\"MAIL\"},{\"id\":2,\"date\":\"2021-03-03\",\"authorName\":\"Sonia Valentin\",\"content\":\"Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?\",\"channel\":\"MAIL\"}]}]", responseFive);
    }
}
