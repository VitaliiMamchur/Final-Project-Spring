package ua.mamchur.springproject.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mamchur.springproject.model.User;
import ua.mamchur.springproject.service.UserService;

@Controller
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
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
            LOGGER.warn("Can't do registration. User with such username already exist");
            redirectAttributes.addFlashAttribute("message", "User already exist. Try another username!");
            redirectAttributes.addFlashAttribute("type", "danger fade show");
            return "redirect:/registration";
        }
        LOGGER.info("New user was created. Username: " + user.getUsername());
        return "redirect:/login";
    }
}