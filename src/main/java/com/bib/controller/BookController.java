package com.bib.controller;


import com.bib.configuration.SecurityConfig;
import com.bib.dao.book.AutorsRepository;
import com.bib.dao.book.Book;
import com.bib.dao.book.BookRepository;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
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
    private AutorsRepository autorsRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/all")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/search")
    public String search(Model model) {

        Set<Object> allAuthors = autorsRepository.getAllAuthors();
        model.addAttribute("allAuthors", allAuthors);
        return "/search";
    }

    @GetMapping("/id")
    public String getByid(Model model, @RequestParam Integer id) {
        Assert.notNull(id, "id could not be null.");
        model.addAttribute("bookById", bookRepository.findById(id));
        return "/search";
    }

//    @GetMapping("/add")
//    public String addNewBook(
//            @RequestParam String name,
//            @RequestParam String autor,
//            @RequestParam Long isbn) {
//        Assert.notNull(name, "Name could not be null.");
//        Assert.notNull(autor, "Autor could not be null.");
//        Assert.notNull(isbn, "isbn could not be null.");
//
//        bookRepository.save(new Book(name, autor, isbn));
//        return "/admin";
//    }

    @PostMapping("/add")
    public String addNewBookPost(
            @RequestParam String name,
            @RequestParam int id,
            @RequestParam Long isbn) {
        Assert.notNull(name, "Name could not be null.");
        Assert.notNull(isbn, "isbn could not be null.");

        bookRepository.save(new Book(id, name));
        return "/admin";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        Assert.notNull(id, "id could not be null.");
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // ~
    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping("/current")
    public String getAlltBooks(Model model) {
        model.addAttribute("books", bookRepository.findAllExistBooks());
        return "/books";
    }
}
