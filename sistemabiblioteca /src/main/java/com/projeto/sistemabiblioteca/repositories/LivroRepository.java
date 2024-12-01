package com.projeto.sistemabiblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sistemabiblioteca.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByEmprestadoFalse();
}
