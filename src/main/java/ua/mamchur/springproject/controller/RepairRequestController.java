package ua.mamchur.springproject.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mamchur.springproject.model.RepairRequest;
import ua.mamchur.springproject.model.User;
import ua.mamchur.springproject.service.RepairRequestService;

@Controller
public class RepairRequestController {

    private static final Logger LOGGER = Logger.getLogger(RepairRequestController.class);
    @Autowired
    RepairRequestService repairRequestService;

    @GetMapping("/request")
    public String view() {
        return "request";
    }

    @PostMapping(value = "/request")
    public String create(RepairRequest repairRequest, @AuthenticationPrincipal User currentUser, RedirectAttributes redirectAttributes) {
        repairRequestService.create(currentUser, repairRequest);
        LOGGER.info("The request was added");
        redirectAttributes.addFlashAttribute("message", "Your repair request was successfully added. Please wait till manager accept it.");
        redirectAttributes.addFlashAttribute("type", "success fade show");
        return "redirect:/request";
    }

}