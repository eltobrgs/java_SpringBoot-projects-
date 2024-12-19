package com.projeto.sistemabiblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SistemabibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemabibliotecaApplication.class, args);
    }
}