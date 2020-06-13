package com.bib.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bib.BibliothekApplicationTests;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringJUnit4ClassRunner.class)
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

        for (String author : (Set<String>) resultActions.getModelAndView().getModelMap().get("booksOfAuthor")) {
            assertTrue("Martin, Friedrisch, Simon".contains(author));
        }
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
        authorAndOneBook.add("Heidegger");
        authorAndOneBook.add("Was heisst Denken?");

        mockMvc.perform(get("/author/books/author_and_books?name=Martin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authorAndBookNames", CoreMatchers.hasItem(authorAndOneBook)));
    }

    @Test
    public void findAllAuthors() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/author/books/authors")
                .requestAttr("selectedListToDisplay", "allAuthors"))
                .andExpect(status().isOk())
                .andReturn();
        Set<List> modelMap = (Set<List>) resultActions.getModelAndView().getModelMap().get("allAuthors");
        System.out.println(modelMap);

        for (List author : modelMap) {
            assertTrue("Martin, Friedrich, Simon, Jacob".contains((String) author.get(0)));
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