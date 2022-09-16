package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping
    public String getUserById(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user/userById";
    }
}
