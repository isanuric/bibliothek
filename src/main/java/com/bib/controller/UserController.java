package com.bib.controller;


import com.bib.dao.user.User;
import com.bib.dao.user.UserRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/home"})
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("allUsers", userRepository.findAllExistUsers());

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

    @PostMapping("/login")
    public String loginPost() {
        return "/user";
    }

    @RequestMapping("/user")
    public String user() {
        return "/user";
    }

    @PostMapping("/user/add")
    public void add(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Long email) {
        userRepository.save(new User(username, password, email));
    }

    @GetMapping("/user/all")
    public void getAll(Model model) {
        Collection<User> allExistUsers = userRepository.findAllExistUsers();
        System.out.println(allExistUsers);
        model.addAttribute("allUsers", allExistUsers);
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

//
//    @GetMapping("/change-password")
//    public String changePassword() {
//        return "/user";
//    }
//
//    @PostMapping("/change-password")
//    public String changePasswordPost(
//            @RequestParam("password-old") String passworOld,
//            @RequestParam("password-new") String passwordNew) {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return "/user";
//    }
}
