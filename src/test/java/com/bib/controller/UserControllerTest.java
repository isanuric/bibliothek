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

    private static final String USER = "1001";
    private static final String ADMIN = "1000";

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
    public void loginSuccess() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "admineins");
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
    public void redirectToLogin() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", ADMIN);
        map.add("password", "pass");
        webTestClient.post().uri("/admin")
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
            assertTrue(v.getResponseCookies().get("JSESSIONID").get(0).getValue().length() == 32);
            assertTrue(v.getResponseHeaders().getFirst("Location").contains("/login"));
        });
    }

    @Test
    @WithMockUser(value = ADMIN, username = ADMIN, password = "pass", roles = "ADMIN")
    public void accessToAdmin() {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("username", ADMIN);
        map.add("password", "pass");
        webTestClient.post().uri("/admin")
//                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
            assertTrue(v.getResponseCookies().get("JSESSIONID").get(0).getValue().length() == 32);
            assertTrue(v.getResponseHeaders().getFirst("Location").contains("/login"));
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
        map.add("password", "Testpass1212");

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