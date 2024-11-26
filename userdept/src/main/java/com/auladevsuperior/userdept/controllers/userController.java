package com.auladevsuperior.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auladevsuperior.userdept.entities.User;
import com.auladevsuperior.userdept.repositories.userRepository;



@RestController
@RequestMapping(value = "/users")
public class userController {
    @Autowired
    private userRepository repository;

    @GetMapping
    public List<User> findAll() {
        List<User> result= repository.findAll();
                return result;
    }

    @GetMapping (value = "/{id}")
    public User findById(@PathVariable Long id) {
        User result= repository.findById(id).get();
                return result;
    }
    
    @PostMapping 
    public User insert(@RequestBody User user) {
        User result= repository.save(user);
                return result;
    }
}


//2003
//Controllers: Lidam com as solicitações dos clientes e fornecem respostas 
//mvc (model-view-controller) é um padrão de arquitetura de software que separa a aplicação em três componentes principais: modelo, visão e controlador, o controller é responsável por receber as requisições do cliente, processar e devolver uma resposta, o entities é responsável por representar os objetos principais e mapear para o banco de dados, o repositories é responsável por fornecer uma implementação completa para operações básicas de banco de dados, como salvar, buscar por ID, buscar todos os registros, deletar e atualizar