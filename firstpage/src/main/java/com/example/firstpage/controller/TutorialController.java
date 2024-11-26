package com.example.firstpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialController {
    @GetMapping("/tutorial")
    public String tutorial() {
        return "tutorial";  
    }
}


