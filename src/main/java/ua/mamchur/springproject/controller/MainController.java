package ua.mamchur.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.mamchur.springproject.repository.RepairRequestRepository;

@Controller
public class MainController {

    @Autowired
    RepairRequestRepository repairRequestRepository;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String mainPage(Model model) {
        return "index";
    }

}
