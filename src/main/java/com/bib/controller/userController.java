//package com.bib.controller;
//
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/user")
//public class userController {
//
//    public String user() {
//        return "/user";
//    }
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
//
//
//    @GetMapping("/403")
//    public String error403() {
//        return "/error/403";
//    }
//
//
//}
