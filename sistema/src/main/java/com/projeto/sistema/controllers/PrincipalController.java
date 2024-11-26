package com.projeto.sistema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
    @GetMapping("/administrativo") // http://localhost:8080/administrativo, serve para acessar a página administrativo, mapeando a rota /administrativo
    public String acessarAdministrativo() { // Método que retorna a página home.html
        return "administrativo/home";
    }

    
}
