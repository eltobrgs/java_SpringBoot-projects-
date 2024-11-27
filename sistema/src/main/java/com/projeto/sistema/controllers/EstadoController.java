package com.projeto.sistema.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema.entities.Estado;
import com.projeto.sistema.repositories.EstadoRepository;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    // Método para exibir o formulário de cadastro de estado
    @GetMapping("/administrativo/estado/cadastrar")
    public ModelAndView cadastrar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    // Método para listar todos os estados cadastrados
    @GetMapping("/administrativo/estado/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/estados/lista");
        mv.addObject("listaEstados", estadoRepository.findAll());
        return mv;
    }
    
    // Método para remover um estado baseado no ID
    @GetMapping("/administrativo/estado/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        estadoRepository.deleteById(id);
        return new ModelAndView("redirect:/administrativo/estado/listar");
    }
    
    // Método para editar um estado existente, baseado no ID
    @GetMapping("/administrativo/estado/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()) {
            return cadastrar(estado.get());
        } else {
            return new ModelAndView("redirect:/administrativo/estado/listar");
        }
    }

    // Método para salvar um novo estado ou atualizar um existente
    @PostMapping("/administrativo/estado/salvar")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(estado);
        }
        estadoRepository.saveAndFlush(estado);
        return new ModelAndView("redirect:/administrativo/estado/listar");
    }

}
