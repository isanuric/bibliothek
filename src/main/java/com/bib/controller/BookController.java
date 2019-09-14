package com.bib.controller;


import com.bib.dao.book.Book;
import com.bib.dao.book.BookRepository;
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

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/all")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/search")
    public String search() {
        return "/search";
    }

    @GetMapping("/id")
    public String getByid(Model model, @RequestParam Integer id) {
        Assert.notNull(id, "id could not be null.");
        model.addAttribute("bookById", bookRepository.findById(id));
        return "/search";
    }

    @GetMapping("/add")
    public String addNewBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Long isbn) {
        this.addBook(name, autor, isbn);
        return "/admin";

    }

    @PostMapping("/add")
    public String addNewBookPost(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Long isbn) {
        this.addBook(name, autor, isbn);
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
    public String getCurrentBooks(Model model) {
        model.addAttribute("books", bookRepository.findAllExistBooks());
        return "/books";
    }

    // ~ Internal Methods
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Add new entity to Repository.
     */
    private void addBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Long isbn) {
        Assert.notNull(name, "Name could not be null.");
        Assert.notNull(autor, "Autor could not be null.");
        Assert.notNull(isbn, "isbn could not be null.");

        bookRepository.save(new Book(name, autor, isbn));
    }

}
