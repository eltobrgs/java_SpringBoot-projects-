package com.projeto.sistemabiblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemabiblioteca.entities.Livro;
import com.projeto.sistemabiblioteca.repositories.LivroRepository;

@Controller // Define a classe como um controlador do Spring MVC
public class LivroController {

    @Autowired // Injeta a dependência do repositório de livros
    private LivroRepository livroRepository;

    @GetMapping("/biblioteca/livro/cadastrar") // Mapeia a rota para exibir o formulário de cadastro
    public ModelAndView cadastrar(Livro livro) {
        ModelAndView mv = new ModelAndView("biblioteca/livros/cadastro"); // Cria um ModelAndView para a página de cadastro
        mv.addObject("livro", livro); // Adiciona um objeto Livro para ser utilizado no formulário
        return mv; // Retorna o ModelAndView configurado
    }

    @GetMapping("/biblioteca/livro/listar") // Mapeia a rota para listar todos os livros
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("biblioteca/livros/lista"); // Cria um ModelAndView para a página de lista de livros
        mv.addObject("listaLivros", livroRepository.findAll()); // Adiciona a lista de todos os livros ao ModelAndView
        return mv; // Retorna o ModelAndView configurado
    }

    
    @GetMapping("/biblioteca/livro/remover/{id}") // Mapeia a rota para remover um livro pelo ID
    public ModelAndView remover(@PathVariable("id") Long id) {
        livroRepository.deleteById(id); // Remove o livro do banco de dados usando o ID
        return new ModelAndView("redirect:/biblioteca/livro/listar"); // Redireciona para a página de lista de livros
    }

    @GetMapping("/biblioteca/livro/editar/{id}") // Mapeia a rota para editar um livro pelo ID
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Livro> livro = livroRepository.findById(id); // Busca o livro pelo ID no banco de dados
        if (livro.isPresent()) { // Verifica se o livro foi encontrado
            return cadastrar(livro.get()); // Retorna o formulário de cadastro com os dados do livro
        } else { // Caso o livro não seja encontrado
            return new ModelAndView("redirect:/biblioteca/livro/listar"); // Redireciona para a lista de livros
        }
    }

    @PostMapping("/biblioteca/livro/salvar") // Mapeia a rota para salvar ou atualizar um livro
    public ModelAndView salvar(Livro livro, BindingResult result) {
        if (result.hasErrors()) { // Verifica se há erros de validação no formulário
            return cadastrar(livro); // Retorna ao formulário de cadastro com os erros
        }
        livroRepository.saveAndFlush(livro); // Salva ou atualiza o livro no banco de dados
        return new ModelAndView("redirect:/biblioteca/livro/listar"); // Redireciona para a lista de livros após salvar
    }
}

/*
Sumário de Termos:

1. @Controller: Indica que esta classe é um controlador do Spring MVC, responsável por gerenciar requisições HTTP.
2. @Autowired: Injeta automaticamente a dependência necessária, neste caso, o `LivroRepository`.
3. LivroRepository: Interface que estende `JpaRepository` e gerencia operações no banco de dados para a entidade `Livro`.
4. ModelAndView: Classe usada para combinar dados (model) e uma página (view) que será exibida ao usuário.
5. @GetMapping: Anotação para mapear requisições HTTP do tipo GET para um método.
6. @PostMapping: Anotação para mapear requisições HTTP do tipo POST para um método.
7. @PathVariable: Indica que o valor de uma variável na URL será usado como argumento do método.
8. BindingResult: Objeto que contém informações sobre erros de validação em formulários.
9. Optional: Classe que pode ou não conter um valor, usada aqui para verificar a presença de um livro no banco de dados.
10. saveAndFlush: Método do JPA Repository que salva ou atualiza uma entidade no banco de dados e executa a operação imediatamente.
11. redirect: Palavra-chave usada para redirecionar o usuário para outra rota.
12. findAll: Método do JPA Repository que retorna uma lista de todas as entidades armazenadas no banco de dados.
13. deleteById: Método do JPA Repository que remove uma entidade do banco de dados com base no ID fornecido.
*/
