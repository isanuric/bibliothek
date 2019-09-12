package com.bib.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CoreControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void admin() {
    }

    @Test
    public void loginPost() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "1001");
        map.add("password", "pass");
        webTestClient.post().uri("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().is3xxRedirection()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().consumeWith(v ->
                System.out.println(v.getResponseBody()));
    }
}