package com.projeto.sistemabiblioteca.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sistemabiblioteca.entities.Livro;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {
    List<Livro> findByEmprestadoFalse();
}
