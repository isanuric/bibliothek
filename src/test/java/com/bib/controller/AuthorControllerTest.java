package com.bib.controller;

import static java.util.Objects.requireNonNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bib.BibliothekApplicationTests;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
public class AuthorControllerTest extends BibliothekApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Ignore
    @Test
    public void findAllBooksBySurname() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/author/books?surname=Heidegger"))
                .andExpect(status().isOk())
                .andReturn();

        ((Set<String>) requireNonNull(resultActions.getModelAndView()).getModelMap().get("booksOfAuthor")).stream()
                .map("Martin, Friedrich, Simon"::contains)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void findBooksNameByAuthorSurname() throws Exception {
        List<String> authorAndOneBook = new ArrayList<>();
        authorAndOneBook.add("Was heisst Denken?");

        mockMvc.perform(get("/author/books/author-and-books?surname=Heidegger"))
                .andExpect(status().isOk());
//                .andExpect(model().attribute("onlyBookNames", CoreMatchers.hasItem(authorAndOneBook)));
    }

    @Test
    public void findAuthorAndHisBooks() throws Exception {
        List<String> authorAndOneBook = new ArrayList<>();
        authorAndOneBook.add("IMMOY");
        authorAndOneBook.add("Kalte Asche");

        mockMvc.perform(get("/author/books/author_and_books?name=Author-IMMOY"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authorAndBookNames", CoreMatchers.hasItem(authorAndOneBook)));
    }

    @Test
    public void findAllAuthors() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/author/books/authors")
                .requestAttr("selectedListToDisplay", "allAuthors"))
                .andExpect(status().isOk())
                .andReturn();
        var modelMap = (Set<List<String>>) requireNonNull(resultActions.getModelAndView()).getModelMap().get("allAuthors");

        int nameIndex = 0;
        int surnameIndex = 1;
        modelMap.stream()
                .filter(p -> p.get(nameIndex).startsWith("F"))
                .map(author -> "Nietzsche".contains(author.get(surnameIndex)))
                .forEach(Assert::assertTrue);
    }
}
