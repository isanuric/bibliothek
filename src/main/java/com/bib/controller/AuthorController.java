package com.bib.controller;


import com.bib.configuration.SecurityConfig;
import com.bib.dao.book.Autor;
import com.bib.dao.book.AutorsRepository;
import com.bib.dao.book.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthorController {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AutorsRepository autorsRepository;

    @GetMapping("/author/books")
    public String findAllBooksBySurname(Model model, @RequestParam String name) {
        logger.info("test: [{}]", autorsRepository.findAll(Sort.by("name")));
        model.addAttribute(
                "booksOfAutor",
                getBookTitles(autorsRepository.getAuthorAndBooks(name)));
        return "/search";
    }

    @GetMapping("/author/books/only_book_names")
    public String findBooksNameByAuthorSurname(Model model, @RequestParam String surname) {
        logger.info("test: [{}]", autorsRepository.findAll(Sort.by("surname")));
        Set<Object> onlyBooks = autorsRepository.getBooksBySurname(surname);
        model.addAttribute("onlyBookNames", onlyBooks);
        return "/search";
    }

    @GetMapping("/author/books/author_and_books")
    public String findAuthorAndHisBooks(Model model, @RequestParam String name) {
        logger.info("test: [{}]", autorsRepository.findAll(Sort.by("name")));
        Set<Object> authorAndBooks = autorsRepository.getAuthorAndBooksDirect(name);
        model.addAttribute("authorAndBookNames", authorAndBooks);
        return "/search";
    }

    @GetMapping("/author/books/authors")
    public String findAllAuthors(Model model) {
        Set<Object> allAuthors = autorsRepository.getAllAuthors();
        model.addAttribute("allAuthors", allAuthors);
        return "/search";
    }


//    @GetMapping(value = "/author/books")
//    public String getAutorByFirstname(Model model, @RequestParam String name) {
//        logger.info("getAutorByFirstname {}", name);
//        Autor firstFind = autorsRepository.findAutorByFirstname(name).iterator().next();
////        String name = firstFind.getName();
////        String surname = firstFind.getSurname();
////        logger.info("firstname: [{}], lastname: [{}], {}", name, surname, firstFind.getBook().get(0).getName());
//        model.addAttribute("NameAndSurname",  autorsRepository.findAutorByFirstname(name));
//        return "/search";
//    }

    private List getBookTitles(Set<Autor> autorSet) {
        Autor autor = autorSet.iterator().next();
        List title = new ArrayList();
        String author = String.format("%s %s", autor.getName(), autor.getSurname());
        for (Book book : autor.getBook()) {
            title.add(String.format("%s, %s", book.getName(), author));
        }
        return title;
    }
}
