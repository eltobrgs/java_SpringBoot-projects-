package com.projeto.sistemabiblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    @GetMapping("/biblioteca")
    public String acessarBiblioteca() {
        return "biblioteca/home"; // Página inicial do sistema de biblioteca
    }

    @GetMapping("/")
    public String redirecionametoBiblioteca() {
        return "biblioteca/home"; // Página inicial do sistema de biblioteca
    }
}
