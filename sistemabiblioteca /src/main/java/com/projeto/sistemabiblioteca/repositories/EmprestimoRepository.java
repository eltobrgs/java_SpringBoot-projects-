package com.projeto.sistemabiblioteca.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sistemabiblioteca.entities.Emprestimo;

@Repository
public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
}
