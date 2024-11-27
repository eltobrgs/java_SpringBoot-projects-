package com.projeto.sistema.controllers; // Pacote onde a classe está localizada

import org.springframework.stereotype.Controller; // Importa a anotação Controller
import org.springframework.web.bind.annotation.GetMapping; // Importa a anotação GetMapping para tratar requisições GET

// A classe é um controlador do Spring, responsável por lidar com as rotas de acesso.
@Controller
public class PrincipalController {

    // A anotação @GetMapping mapeia a URL "/administrativo" para o método abaixo
    @GetMapping("/administrativo")
    public String acessarAdministrativo() {
        // Retorna o nome da view (arquivo HTML) que será renderizado para o usuário
        return "administrativo/home"; // A view home.html dentro da pasta "administrativo"
    }
}
