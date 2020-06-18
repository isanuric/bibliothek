package com.bib.controller;


import com.bib.configuration.SecurityConfig;
import com.bib.dao.author.Author;
import com.bib.dao.author.AuthorRepository;
import com.bib.dao.book.Book;
import com.bib.dao.book.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.SetUtils;
import reactor.util.StringUtils;


@Controller
public class AuthorController {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private static final String SEARCH_PAGE = "/search";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/author/books")
    public String findAllBooksBySurname(Model model, @RequestParam String surname) {
        if (StringUtils.isEmpty(surname)) {
            return SEARCH_PAGE;
        }

        Optional<List> bookTitles = getBookTitles(authorRepository.getAuthorAndBooks(surname));

        if (bookTitles.isEmpty()) {
            String attributeValue = "Author [" + surname + "] not found.";
            logger.info("{}", attributeValue);
            model.addAttribute("booksOfAuthor", attributeValue);
            return SEARCH_PAGE;
        }

        logger.info("test: [{}]", authorRepository.findAll(Sort.by("surname")));
        model.addAttribute("booksOfAuthor", bookTitles.get());
        return SEARCH_PAGE;
    }

    @GetMapping("/author/books/author-and-books")
    public String findBooksNameByAuthorSurname(Model model, @RequestParam String surname) {
        logger.info("getBooksBySurname: [{}]", authorRepository.getBooksBySurname(surname));
        model.addAttribute("onlyBookNames", authorRepository.getBooksBySurname(surname));
        return SEARCH_PAGE;
    }

    @GetMapping("/author/books/author_and_books")
    public String findAuthorAndHisBooks(Model model, @RequestParam String name) {
        Set<List> authorAndBooks = authorRepository.getAuthorAndBooksDirect(name);
        model.addAttribute("authorAndBookNames", authorAndBooks);
        return SEARCH_PAGE;
    }

    @GetMapping("/author/books/authors")
    public String findAllAuthors(Model model, HttpServletRequest request) {
        String listToDisplay = request.getParameter("selectedListToDisplay");

        Set<List> allValuesToDisplay;
        if ("allBooks".equals(listToDisplay)) {
            allValuesToDisplay = bookRepository.getAllBooks();
        } else {
            allValuesToDisplay = authorRepository.getAllAuthorsFullName();
        }
        model.addAttribute("allAuthors", allValuesToDisplay);
        return "/books";
    }

    private Optional<List> getBookTitles(Set<Author> authorSet) {

        if (SetUtils.isEmpty(authorSet)) {
            logger.info("Autor set is empty.");
            return Optional.empty();
        }

        Author autor = authorSet.iterator().next();

        List title = new ArrayList();
        String author = String.format("%s %s", autor.getName(), autor.getSurname());
        for (Book book : autor.getBook()) {
            title.add(String.format("%s, %s", book.getName(), author));
        }
        return Optional.of(title);
    }
}
