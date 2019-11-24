package com.bib.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bib.BaseTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class AuthorControllerTest extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void login_getOnlyBookNames() throws Exception {
        mockMvc.perform(get("/author/books/only_book_names?name=Martin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("onlyBookNames", CoreMatchers.hasItem("Sein und Zeit")));
    }

}