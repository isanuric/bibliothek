package com.bib.controller;

import com.bib.BibliothekApplicationTests;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest extends BibliothekApplicationTests {

    @Test
    public void getBooks() {
        webTestClient.get().uri("/book/display-all-books")
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
//            assertTrue(v.getResponseBody().toString().contains("Nietzsche contra Wagner"));
        });
    }

    @Ignore
    @Test
    public void getAutorByFiresName() {
        webTestClient.get().uri("author/books?name=Martin")
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(v -> {
            System.out.println(v);
            // TODO: 20/11/2019 test model
//            assertTrue(v.getResponseBody().toString().contains("Martin Heidegger"));
        });
    }

    @Test
    public void getByid() {
    }

    @Test
    public void getCurrentBooks() {
    }
}