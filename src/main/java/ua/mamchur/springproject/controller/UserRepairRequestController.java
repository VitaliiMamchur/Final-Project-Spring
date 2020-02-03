package ua.mamchur.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mamchur.springproject.service.RepairRequestService;

@Controller
public class UserRepairRequestController {

    @Autowired
    RepairRequestService repairRequestService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = {"/userlist"}, method = RequestMethod.GET)
    public String userRepairRequestList(Model model, @AuthenticationPrincipal UserDetails currentUser) {

        model.addAttribute("repairRequests", repairRequestService.getAllByUser(currentUser));

        return "userlist";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value = "/userlist/{id}")
    public String addFeedbackToRequest(@PathVariable("id") Long id, @RequestParam String feedback, RedirectAttributes redirectAttributes) {

        if(repairRequestService.addFeedBack(id,feedback)==null)
        {
            redirectAttributes.addFlashAttribute("message", "You can't push feedback, while your request is processing.");
            redirectAttributes.addFlashAttribute("type", "danger fade show");
            return "redirect:/userlist";
        }
        redirectAttributes.addFlashAttribute("message", "Feedback has been successfully published");
        redirectAttributes.addFlashAttribute("type", "success fade show");
        return "redirect:/userlist";
    }
}