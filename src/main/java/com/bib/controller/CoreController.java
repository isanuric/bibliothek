package com.bib.controller;


import com.bib.dao.Book;
import com.bib.dao.BookRepository;
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

    @GetMapping("/books/search")
    public String search() {
        return "/search";
    }

    @GetMapping("/books")
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
     * @param isbn
     */
    private void addBook(
            @RequestParam String name,
            @RequestParam String autor,
            @RequestParam Long isbn) {
        Assert.notNull(name, "Name could not be null.");
        Assert.notNull(autor, "Autor could not be null.");
        Assert.notNull(isbn, "isbn could not be null.");

        // create new book
        Book book = new Book();
        book.setName(name);
        book.setAutor(autor);
        book.setIsbn(isbn);
        book.setStatus(1);

        // add to repository
        bookRepository.save(book);
    }

}
