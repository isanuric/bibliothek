package com.bib.controller;

import com.bib.BibliothekApplicationTests;
import org.junit.Ignore;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest extends BibliothekApplicationTests {

    @Test
    public void getBooks() {
        webTestClient.get().uri("/book/display-all-books")
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(v -> {
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
            // TODO: 20/11/2019 test model
//            assertTrue(v.getResponseBody().toString().contains("Martin Heidegger"));
        });
    }


}