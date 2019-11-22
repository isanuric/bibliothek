package com.bib.controller;


import com.bib.configuration.SecurityConfig;
import com.bib.dao.book.Autor;
import com.bib.dao.book.AutorsRepository;
import com.bib.dao.book.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        Autor booksOfAutor = autorsRepository.getBooksOfAutor(name).iterator().next();
        Optional<Autor> byId = autorsRepository.findById(1);
        logger.debug("b: [{}]", byId);
        List title = getTitlesByAuthorName(name, booksOfAutor);
        model.addAttribute("booksOfAutor", title);
        return "/search";
    }

    private List getTitlesByAuthorName(@RequestParam String name, Autor booksOfAutor) {
        List title = new ArrayList();
        String author = String.format("%s %s", name, booksOfAutor.getSurname());
        for (Book book : booksOfAutor.getBook()) {
            title.add(String.format("%s, %s", book.getName(), author));
        }
        return title;
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

}
