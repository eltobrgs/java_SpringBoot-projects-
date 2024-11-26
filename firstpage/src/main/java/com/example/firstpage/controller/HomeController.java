package com.example.firstpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";  // Retorna o template "home.html"
    }
    
    @GetMapping("/")  // Mapeia a rota raiz
    public String redirectToHome() {
        return "redirect:/home";  // Redireciona para a página "home"
    }
}
