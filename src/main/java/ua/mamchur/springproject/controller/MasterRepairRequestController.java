package ua.mamchur.springproject.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.mamchur.springproject.service.RepairRequestService;
import ua.mamchur.springproject.service.RepairRequestStatusService;

@Controller
public class MasterRepairRequestController {

    private static final Logger LOGGER = Logger.getLogger(MasterRepairRequestController.class);
    @Autowired
    RepairRequestService repairRequestService;

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @RequestMapping(value = {"/masterlist"}, method = RequestMethod.GET)
    public String currentMasterRepairRequestList(Model model) {
        model.addAttribute("repairRequests", repairRequestService.getMasterRepairRequestList());
        return "masterlist";
    }

    @PreAuthorize("hasAuthority('ROLE_MASTER')")
    @PostMapping(value = "/masterlist/{id}")
    public String closeAcceptedRequest(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        repairRequestService.changeRepairRequestStatus(id, RepairRequestStatusService.CLOSED_REQUEST);
        LOGGER.info("The request with id " + id + " was closed successfully");
        redirectAttributes.addFlashAttribute("message", "The request is successfully closed");
        redirectAttributes.addFlashAttribute("type", "success fade show");
        return "redirect:/masterlist";
    }
}
