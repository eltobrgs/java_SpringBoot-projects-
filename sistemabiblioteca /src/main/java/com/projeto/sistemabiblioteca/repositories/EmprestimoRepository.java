package com.projeto.sistemabiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sistemabiblioteca.entities.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
