package com.bib.controller;


import com.bib.dao.book.AutorsRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toolbar")
public class ToolbarController {

    @Autowired
    private AutorsRepository autorsRepository;

    @GetMapping("/search")
    public String search(Model model) {
    Set<Object> allAuthors = autorsRepository.getAllAuthors();
        model.addAttribute("allAuthors", allAuthors);
        return "/search";
    }


}
