package com.bib.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping({"/", "/home"})
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

    @RequestMapping("/user")
    public String user() {
        return "/user";
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
