package com.projeto.sistemabiblioteca.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.sistemabiblioteca.entities.Emprestimo;
import com.projeto.sistemabiblioteca.entities.Livro;
import com.projeto.sistemabiblioteca.repositories.EmprestimoRepository;
import com.projeto.sistemabiblioteca.repositories.LivroRepository;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // Exibe a página de emprestar livro com a lista de livros disponíveis
    @GetMapping("/emprestar")
    public String mostrarEmprestarLivro(Model model) {
        // Obtenha todos os livros disponíveis para popular o dropdown
        model.addAttribute("livros", livroRepository.findByEmprestadoFalse()); // Somente livros não emprestados
        model.addAttribute("emprestimo", new Emprestimo());
        return "emprestimo/emprestarLivro";  // Nome do template a ser exibido
    }

    // Método POST para realizar o empréstimo
    @PostMapping("/salvar")
    public String emprestarLivro(@ModelAttribute("emprestimo") Emprestimo emprestimo) {
        Livro livro = livroRepository.findById(emprestimo.getLivro().getId())
                                     .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        
        // Verificar se o livro já está emprestado
        if (livro.getEmprestado()) {
            return "redirect:/livro/indisponivel";  // Redireciona para uma página de erro, caso o livro já tenha sido emprestado
        }
        
        // Atualiza o livro como emprestado
        livro.setEmprestado(true);
        livro.setDataEmprestimo(LocalDate.now());
        livro.setDataDevolucao(emprestimo.getDataDevolucao());  // Usa a data de devolução do formulário
        livroRepository.save(livro);
        
        // Salva o empréstimo no banco
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimoRepository.save(emprestimo);

        // Redireciona para a lista de livros emprestados
        return "redirect:/emprestimo/lista";
    }

    // Exibe a lista de livros emprestados
    @GetMapping("/lista")
    public String listaEmprestados(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimo/listaEmprestados";  // Exibe a lista de livros emprestados
    }

    // Exibe a página para devolução de livro
    @GetMapping("/devolver/{id}")
    public String devolverEmprestimo(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        
        Livro livro = emprestimo.getLivro();
        
        // Marca o livro como devolvido
        livro.setEmprestado(false);
        livro.setDataEmprestimo(null);
        livro.setDataDevolucao(null);
        livroRepository.save(livro);
        
        // Exclui o registro do empréstimo
        emprestimoRepository.delete(emprestimo);

        // Redireciona para a lista de empréstimos
        return "redirect:/livro/lista";
    }
}
