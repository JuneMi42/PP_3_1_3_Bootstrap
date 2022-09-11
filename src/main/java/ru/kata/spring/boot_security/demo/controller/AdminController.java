package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getUser(Model model, @AuthenticationPrincipal User userHEAD) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("userHEAD", userHEAD);
        return "admin/getUser";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User userHEAD) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userHEAD", userHEAD);
        return "admin/userById";
    }

    @GetMapping("/new")
    public String newUser(Model model, @AuthenticationPrincipal User userHEAD) {
        model.addAttribute("user", new User());
        model.addAttribute("userHEAD", userHEAD);
        model.addAttribute("roles", userService.getRoles());
        return "admin/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal User userHEAD) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userHEAD", userHEAD);
        model.addAttribute("roles", userService.getRoles());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
