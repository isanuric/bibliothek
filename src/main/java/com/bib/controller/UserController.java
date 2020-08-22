package com.bib.controller;


import com.bib.dao.members.Members;
import com.bib.dao.members.MembersRepository;
import com.bib.service.PasswordService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/user")
public class UserController {

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private PasswordService passwordService;


    @RequestMapping()
    public String user(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "user";
    }

    @PostMapping("/add")
    public String add(
            Model model,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            model.addAttribute("anyIsEmpty", "username, password or email could not be empty ");
            return "login";
        }

        if (!passwordService.isPasswordScopeCorrect(model, password)) {
            return "login";
        }

        membersRepository.save(new Members(username, password, email));
        return "user";
    }

    @GetMapping("/all")
    public void getAll(Model model) {
        Collection<Members> allExistUsers = membersRepository.findAllExistUsers();
        model.addAttribute("allUsers", allExistUsers);
    }

    @PostMapping("/change-password")
    public String changePasswordPost(
            @RequestParam("password-old") String passworOld,
            @RequestParam("password-new") String passwordNew) {
        System.out.println(passworOld + "-> " + passwordNew);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/user";
    }

    private String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
