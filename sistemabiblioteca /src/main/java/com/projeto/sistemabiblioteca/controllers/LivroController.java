package com.projeto.sistemabiblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.sistemabiblioteca.entities.Livro;
import com.projeto.sistemabiblioteca.repositories.LivroRepository;

@Controller
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    /**
     * Endpoint para listar todos os livros disponíveis.
     */
    @GetMapping("/lista")
    public String listaLivros(Model model) {
        model.addAttribute("livros", livroRepository.findByEmprestadoFalse());
        return "livro/listaLivros";
    }

    /**
     * Endpoint para exibir o formulário de cadastro de livro.
     */
    @GetMapping("/cadastro")
    public String cadastroLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livro/cadastroLivro";
    }

    /**
     * Endpoint para salvar um novo livro ou atualizar um existente.
     */
    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livro/lista";
    }

    /**
     * Endpoint para carregar o formulário de edição de livro.
     */
    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable String id, Model model) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        model.addAttribute("livro", livro);
        return "livro/editarLivro";
    }

    /**
     * Endpoint para atualizar um livro.
     */
    @PostMapping("/atualizar")
    public String atualizarLivro(@ModelAttribute Livro livro) {
        // Verifique se o livro existe
        Livro livroExistente = livroRepository.findById(livro.getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        // Atualize os campos
        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setCategoria(livro.getCategoria());
        livroExistente.setDescricao(livro.getDescricao());
        livroExistente.setEditora(livro.getEditora());
        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setAnoPublicacao(livro.getAnoPublicacao());

        // Salve o livro atualizado
        livroRepository.save(livroExistente);
        return "redirect:/livro/lista";
    }

    /**
     * Endpoint para deletar um livro.
     */
    @GetMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable String id) {
        livroRepository.deleteById(id);
        return "redirect:/livro/lista";
    }

    // Exibe a página de livro indisponível
    @GetMapping("/indisponivel")
    public String livroIndisponivel() {
        return "livro/indisponivel";  // Template que informa que o livro está indisponível
    }
}
