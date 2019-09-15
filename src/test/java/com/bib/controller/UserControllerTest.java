package com.bib.controller;

import static org.junit.Assert.assertTrue;

import com.bib.BaseTest;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;


public class UserControllerTest extends BaseTest {

    @Test
    public void admin() {
    }

    @Test
    public void loginPost() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "user");
        map.add("password", "pass");
        webTestClient.post().uri("/login")
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
            assertTrue(v.getResponseCookies().get("JSESSIONID").get(0).getValue().length() == 32);
            assertTrue(v.getResponseHeaders().getFirst("Location").contains("/user"));
        });
    }

    @Test
    public void addUserPost() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "user02");
        map.add("password", "pass");
        webTestClient.post().uri("/user/add")
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectBody().consumeWith(v -> {
//            System.out.println(v);
//            assertTrue(v.getResponseCookies().get("JSESSIONID").get(0).getValue().length() == 32);
//            assertTrue(v.getResponseHeaders().getFirst("Location").contains("/user"));
        });

        webTestClient.get().uri("/user/all")
                .exchange()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
        });
    }
}