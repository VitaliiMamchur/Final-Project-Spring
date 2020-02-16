package ua.mamchur.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mamchur.springproject.model.User;
import ua.mamchur.springproject.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        User createdUser = userService.create(user);
        if (createdUser == null) {
            redirectAttributes.addFlashAttribute("message", "User already exist. Try another username!");
            redirectAttributes.addFlashAttribute("type", "danger fade show");
            return "registration";
        }
        return "redirect:/login";
    }
}