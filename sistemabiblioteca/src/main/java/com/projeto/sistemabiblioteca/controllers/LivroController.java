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

@Controller
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    // Método para exibir o formulário de cadastro de livro
    @GetMapping("/biblioteca/livro/cadastrar")
    public ModelAndView cadastrar(Livro livro) {
        ModelAndView mv = new ModelAndView("biblioteca/livros/cadastro"); // Caminho correto para a página de cadastro de livro
        mv.addObject("livro", livro);
        return mv;
    }

    // Método para listar todos os livros cadastrados
    @GetMapping("/biblioteca/livro/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("biblioteca/livros/lista"); // Caminho correto para a lista de livros
        mv.addObject("listaLivros", livroRepository.findAll());
        return mv;
    }

    // Método para remover um livro baseado no ID
    @GetMapping("/biblioteca/livro/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        livroRepository.deleteById(id);
        // Redirecionando para a página de lista de livros após a remoção
        return new ModelAndView("redirect:/biblioteca/livro/listar"); // Corrigido para redirecionar para a rota correta
    }

    // Método para editar um livro existente, baseado no ID
    @GetMapping("/biblioteca/livro/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            // Usa o método de cadastro para editar (mesmo formulário de cadastro serve para edição)
            return cadastrar(livro.get()); 
        } else {
            // Se não encontrar o livro, redireciona para lista
            return new ModelAndView("redirect:/biblioteca/livro/listar");
        }
    }

    // Método para salvar um novo livro ou atualizar um existente
    @PostMapping("/biblioteca/livro/salvar")
    public ModelAndView salvar(Livro livro, BindingResult result) {
        if (result.hasErrors()) {
            // Retorna ao formulário em caso de erro de validação
            return cadastrar(livro); 
        }
        livroRepository.saveAndFlush(livro); // Salva ou atualiza o livro no banco de dados
        // Redireciona para a lista de livros após salvar ou atualizar
        return new ModelAndView("redirect:/biblioteca/livro/listar"); // Corrigido para redirecionar para a lista
    }
}
