package com.bib.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PasswordService {

    public boolean isPasswordScopeCorrect(Model model, String password) {
        if (password.length() < 6) {
            model.addAttribute("pwTooShort", "Password too short. Password must have at least 6 characters");
            return false;
        }

        if (!hasUppercase(password)) {
            model.addAttribute("needUppercase", "Password must have at least one upper case");
            return false;
        }

        if (!hasLowercase(password)) {
            model.addAttribute("needLowercase", "Password must have at least one lower case");
            return false;
        }
        return true;
    }

    public boolean hasLowercase(String string) {
        return !string.equals(string.toUpperCase());
    }

    public boolean hasUppercase(String string) {
        return !string.equals(string.toLowerCase());
    }

}
