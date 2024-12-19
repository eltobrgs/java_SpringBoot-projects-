
// Alteração no EmprestimoController.java
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

    @GetMapping("/emprestar")
    public String mostrarEmprestarLivro(Model model) {
        model.addAttribute("livros", livroRepository.findByEmprestadoFalse());
        model.addAttribute("emprestimo", new Emprestimo());
        return "emprestimo/emprestarLivro";
    }

    @PostMapping("/salvar")
    public String emprestarLivro(@ModelAttribute("emprestimo") Emprestimo emprestimo) {
        Livro livro = livroRepository.findById(emprestimo.getLivroId())
                                     .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getEmprestado()) {
            return "redirect:/livro/indisponivel";
        }

        livro.setEmprestado(true);
        livro.setDataEmprestimo(LocalDate.now());
        livro.setDataDevolucao(emprestimo.getDataDevolucao());
        livroRepository.save(livro);

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimoRepository.save(emprestimo);

        return "redirect:/emprestimo/lista";
    }

    @GetMapping("/lista")
    public String listaEmprestados(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimo/listaEmprestados";
    }

    @GetMapping("/devolver/{id}")
    public String devolverEmprestimo(@PathVariable String id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        Livro livro = livroRepository.findById(emprestimo.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setEmprestado(false);
        livro.setDataEmprestimo(null);
        livro.setDataDevolucao(null);
        livroRepository.save(livro);

        emprestimoRepository.delete(emprestimo);

        return "redirect:/livro/lista";
    }
}
