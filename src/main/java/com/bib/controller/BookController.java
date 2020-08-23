package com.bib.controller;


import static org.springframework.util.Assert.notNull;

import com.bib.configuration.SecurityConfig;
import com.bib.dao.author.AuthorRepository;
import com.bib.dao.book.Book;
import com.bib.dao.book.BookRepository;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/book")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/all")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/search")
    public String search(Model model) {

        Set<Object> allAuthors = authorRepository.getAllAuthors();
        model.addAttribute("allAuthors", allAuthors);
        return "/search";
    }

    @GetMapping("/id")
    public String getByid(Model model, @RequestParam Integer id) {
        notNull(id, "id could not be null.");
        model.addAttribute("bookById", bookRepository.findById(id));
        return "/search";
    }

    @PostMapping("/add")
    public String addNewBookPost(
            @RequestParam int authorId,
            @RequestParam String bookName) {
        notNull(bookName, "Name could not be null.");

        bookRepository.save(new Book( 1, "bookName4"));
        return "/admin";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/display-all-books")
    public String getAlltBooks(Model model) {
        model.addAttribute("books", bookRepository.findAllBooksSortById());
        return "/books-all";
    }

    @GetMapping("/choose")
    public String choose(Model model) {
        model.addAttribute("books", bookRepository.findAllBooksSortById());
        return "/books";
    }
}
