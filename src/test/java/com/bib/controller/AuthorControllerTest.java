package com.bib.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bib.BaseTest;
import java.util.HashSet;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
public class AuthorControllerTest extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findBooksNameByAuthorSurname() throws Exception {
        mockMvc.perform(get("/author/books/only_book_names?surname=Heidegger"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("onlyBookNames", CoreMatchers.hasItem("Sein und Zeit")));
    }

    @Test
    public void findAuthorAndHisBooks() throws Exception {
        Set<Object> hashSet = new HashSet<>();
        hashSet.add(new String[]{"Heidegger", "Sein und Zeit"});

        mockMvc.perform(get("/author/books/author_and_books?name=Martin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authorAndBookNames", Matchers.hasSize(2)))
                .andExpect(model().attribute("authorAndBookNames", Matchers.hasItem(hashSet.iterator().next())));
    }

    @Test
    public void findAllAuthors() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/author/books/authors"))
                .andExpect(status().isOk())
                .andReturn();
        for (String author : (Set<String>) resultActions.getModelAndView().getModelMap().get("allAuthors")) {
            assertTrue("Martin, Friedrisch, Simon".contains(author));
        }
    }

//    @Test
//    public void findAllAuthors() throws Exception {
//
//        Autor autor = Mockito.mock(Autor.class);
//        HashSet<Object> authors = new HashSet<>();
//        authors.add(autor);
//
//        MvcResult resultActions = mockMvc.perform(get("/author/books/authors"))
//                .andExpect(status().isOk())
//                .andReturn();
////                .andExpect(model().attribute("allAuthors", Matchers.hasSize(3)));
////                .andExpect(model().attribute("allAuthors", Matchers.hasItem(authors.iterator().next())));
//        Set<Autor> allAuthors = (Set<Autor>) resultActions.getModelAndView().getModelMap().get("allAuthors");
////        System.out.println("---> " + allAuthors.iterator().next().getBook().iterator().next().getName());
//        System.out.println("---> " + allAuthors.iterator().next().getBook().get(0).getName());
////        assertThat()
//    }
}