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
    public void search() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "value 1");
        map.put(2, "value 2");
        map.put(3, "value 3");
        map.put(4, "value 4");
        map.put(5, "value 5");
        if (!map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String v = entry.getValue().toString();
                map.remove(entry);
            }
        }
    }

    @Test
    public void getByid() {
    }

    @Test
    public void getCurrentBooks() {
    }
}