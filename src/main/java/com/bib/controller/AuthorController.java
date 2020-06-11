package com.bib.controller;


import com.bib.configuration.SecurityConfig;
import com.bib.dao.book.Autor;
import com.bib.dao.book.AutorsRepository;
import com.bib.dao.book.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.util.Assert;
import reactor.util.ObjectUtils;
import reactor.util.StringUtils;


@Controller
public class AuthorController {

    private static final String SEARCH_PAGE = "/search";
    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AutorsRepository autorsRepository;

    @GetMapping("/author/books")
//    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
    public String findAllBooksBySurname(Model model, @RequestParam String surname) {
        if (StringUtils.isEmpty(surname)) {
            return SEARCH_PAGE;
        }

        Optional<List> bookTitles = getBookTitles(autorsRepository.getAuthorAndBooks(surname));

        if (bookTitles.isEmpty()) {
            model.addAttribute("booksOfAutor", "uid not found");
            return SEARCH_PAGE;
        }

        logger.info("test: [{}]", autorsRepository.findAll(Sort.by("surname")));
        model.addAttribute("booksOfAutor", bookTitles.get());
        return SEARCH_PAGE;
    }

    @GetMapping("/author/books/only_book_names")
    public String findBooksNameByAuthorSurname(Model model, @RequestParam String surname) {
        logger.info("test: [{}]", autorsRepository.findAll(Sort.by("surname")));
        Set<Object> onlyBooks = autorsRepository.getBooksBySurname(surname);
        model.addAttribute("onlyBookNames", onlyBooks);
        return SEARCH_PAGE;
    }

    @GetMapping("/author/books/author_and_books")
    public String findAuthorAndHisBooks(Model model, @RequestParam String name) {
        logger.info("test: [{}]", autorsRepository.findAll(Sort.by("name")));
        Set<Object> authorAndBooks = autorsRepository.getAuthorAndBooksDirect(name);
        model.addAttribute("authorAndBookNames", authorAndBooks);
        return SEARCH_PAGE;
    }

    @GetMapping("/author/books/authors")
    public String findAllAuthors(Model model) {
        Set<Object> allAuthors = autorsRepository.getAllAuthors();
        model.addAttribute("allAuthors", allAuthors);
        return SEARCH_PAGE;
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

    private Optional<List> getBookTitles(Set<Autor> autorSet) {

        if (autorSet.isEmpty()) {
            Optional.empty();
        }

        Autor autor = autorSet.iterator().next();
        List title = new ArrayList();
        String author = String.format("%s %s", autor.getName(), autor.getSurname());
        for (Book book : autor.getBook()) {
            title.add(String.format("%s, %s", book.getName(), author));
        }
        return Optional.of(title);
    }
}
