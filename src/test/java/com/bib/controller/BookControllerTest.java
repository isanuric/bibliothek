package com.bib.controller;

import static org.junit.Assert.*;

import com.bib.BaseTest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;
import org.springframework.web.reactive.function.BodyInserters;

public class BookControllerTest extends BaseTest {

    @Test
    public void getBooks() {
        webTestClient.get().uri("/book/current")
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
            assertTrue(v.getResponseBody().toString().contains("Nietzsche contra Wagner"));
        });
    }

    @Test
    public void getByid() {
    }

    @Test
    public void getCurrentBooks() {
    }
}