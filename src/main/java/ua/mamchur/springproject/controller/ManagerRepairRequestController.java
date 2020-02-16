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
import ua.mamchur.springproject.model.RepairRequest;
import ua.mamchur.springproject.service.RepairRequestService;
import ua.mamchur.springproject.service.RepairRequestStatusService;

@Controller
public class ManagerRepairRequestController {

    private static final Logger LOGGER = Logger.getLogger(ManagerRepairRequestController.class);
    @Autowired
    RepairRequestService repairRequestService;

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @RequestMapping(value = {"/managerlist"}, method = RequestMethod.GET)
    public String currentManagerRepairRequestList(Model model) {
        model.addAttribute("repairRequests", repairRequestService.getManagerRepairRequestList());
        return "managerlist";
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PostMapping(value = "/managerlist/{id}")
    public String acceptCurrentRequestWithPrice(
            @PathVariable("id") Long id,
            RepairRequest repairRequest,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("The request with id " + id + " was accepted");
        repairRequestService.changeRepairRequestStatus(id, RepairRequestStatusService.ACCEPTED_REQUEST);
        repairRequestService.edit(id, repairRequest);
        redirectAttributes.addFlashAttribute("message", "The request is successfully accepted.");
        redirectAttributes.addFlashAttribute("type", "success fade show");
        return "redirect:/managerlist";
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PostMapping(value = "/decliningmanagerlist/{id}")
    public String declineRequest(@PathVariable("id") Long id) {
        repairRequestService.changeRepairRequestStatus(id, RepairRequestStatusService.DECLINED_REQUEST);
        LOGGER.info("The request with id " + id + " was declined");
        return "redirect:/managerlist";
    }
}