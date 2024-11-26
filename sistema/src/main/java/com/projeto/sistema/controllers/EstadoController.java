package com.projeto.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import com.projeto.sistema.entities.Estado;
import com.projeto.sistema.repositories.EstadoRepository;



@Controller
public class EstadoController {
     
    @Autowired
    private EstadoRepository estadoRepository;

    //A anotação @Autowired faz com que o Spring injete uma instância de EstadoRepository aqui

    @GetMapping("/cadastrarEstado")
    public ModelAndView cadastrar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(estado);
        }
        
        estadoRepository.saveAndFlush(estado);
        return cadastrar(new Estado());
    }
    
    
}
