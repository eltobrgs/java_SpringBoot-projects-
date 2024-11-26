package com.projeto.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.sistema.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> { 
    //o JpaRepository é uma interface que já tem vários métodos prontos para fazer operações no banco de dados
    //o primeiro parâmetro é a entidade que será gerenciada pelo repositório, e o segundo é o tipo do atributo que é a chave primária da entidade
    //como a chave primária da entidade Estado é do tipo long, o segundo parâmetro é Long

}
