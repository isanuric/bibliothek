package com.bib.controller;


import com.bib.book.Book;
import com.bib.book.BookRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoreController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }


    @GetMapping("/all")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getByid(@PathVariable Integer id) {
        Assert.notNull(id, "id could not be null.");
        return bookRepository.findById(id);
    }

    @GetMapping("/add")
    public String addNewBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {
        this.addBook(name, autor, iban);
        return "Done";

    }

    @PostMapping("/add")
    public String addNewBookPost(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {
        this.addBook(name, autor, iban);
        return "Done";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Assert.notNull(id, "id could not be null.");
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // ~
    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping("/books/current")
    public String getCurrentBooks(Model model) {
        model.addAttribute("books", bookRepository.findAllExistBooks());
        return "/books";
    }


    // ~ Internal Methods
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Add new entity to Repository.
     * @param name
     * @param autor
     * @param iban
     */
    private void addBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Integer iban) {
        Assert.notNull(name, "Name could not be null.");
        Assert.notNull(autor, "Autor could not be null.");
        Assert.notNull(iban, "IBAN could not be null.");

        // create new book
        Book book = new Book();
        book.setName(name);
        book.setAutor(autor);
        book.setIban(iban);
        book.setStatus(1);

        // add to repository
        bookRepository.save(book);
    }

}
