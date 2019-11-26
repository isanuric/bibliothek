package com.bib.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bib.BaseTest;
import com.bib.dao.book.Book;
import java.util.HashSet;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class AuthorControllerTest extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getOnlyBookNames() throws Exception {
        mockMvc.perform(get("/author/books/only_book_names?name=Martin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("onlyBookNames", CoreMatchers.hasItem("Sein und Zeit")));
    }

    @Test
    public void getOnlyBookNamesDirect() throws Exception {

        Set<Object> hashSet = new HashSet<>();
        hashSet.add(new String[]{"Heidegger", "Sein und Zeit"});

        mockMvc.perform(get("/author/books/author_and_book_names?name=Martin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authorAndBookNames", Matchers.hasSize(2)))
                .andExpect(model().attribute("authorAndBookNames", Matchers.hasItem(hashSet.iterator().next())));
    }

    @Test
    public void findAllAuthors() throws Exception {

        HashSet<Object> authors = new HashSet<>();
        authors.add(new Object[]{"1", "Martin", "Heidegger", new Book()});
        mockMvc.perform(get("/author/books/authors"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("allAuthors", Matchers.hasSize(3)));
//                .andExpect(model().attribute("allAuthors", Matchers.contains(authors.iterator().next())));
    }
}