package com.bib.controller;

import static org.junit.Assert.assertTrue;

import com.bib.BaseTest;
import java.util.Map;
import org.junit.Test;
import org.springframework.http.ResponseCookie;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;


public class UserControllerTest extends BaseTest {

    @Test
    public void admin() {
    }

    @Test
    public void about() {
        webTestClient.get().uri("/about")
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
        });
    }

    @Test
    public void loginPost() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "1000");
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
    public void addUser() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
//        map.add("usernameSubmit", "test01");
//        map.add("passwordSubmit", "test01");
        map.add("username", "test01");
        map.add("password", "test01");
        map.add("email", "test01@gmail.com");
        webTestClient.post().uri("/user/add")
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
        });
    }

    @Test
//    @WithMockUser(username = "user", password = "pass", authorities = "user")
//    @WithMockUser
    @WithMockUser(username = "admin", password = "pass", roles = {"USER", "ADMIN"})
    public void addUserPost() {

        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "user");
        map.add("password", "pass");

        webTestClient.post().uri("/login")
                .exchange().
                expectStatus().isFound()
                .expectBody(Void.class).consumeWith(v -> {
            Map<String, ResponseCookie> cookies = v.getResponseCookies().toSingleValueMap();
            ResponseCookie smsession = cookies.get("JSESSIONID");

            webTestClient.post().uri("/user/all")
                    .header("Set-Cookie", "JSESSIONID=", smsession.getValue())
                    .exchange()
                    .expectBody().consumeWith(z -> {
                System.out.println("******************>>>");
                System.out.println(z);
                System.out.println("******************<<<");
            });
        });
    }
}