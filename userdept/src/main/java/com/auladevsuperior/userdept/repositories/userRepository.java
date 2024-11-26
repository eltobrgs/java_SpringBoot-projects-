package com.auladevsuperior.userdept.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auladevsuperior.userdept.entities.User;

public interface userRepository extends JpaRepository<User, Long> {


}


// //JpaRepository fornece uma implementação completa para operações básicas de banco de dados, como:
// Salvar (save)
// Buscar por ID (findById)
// Buscar todos os registros (findAll)
// Deletar (delete)
// Atualizar (update)