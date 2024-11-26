package com.projeto.sistema;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;

@Configuration
public class ConfiguracaoBancoDeDados {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); //declara o driver do banco de dados
        dataSource.setUrl("jdbc:postgresql://localhost:5432/loja"); // declara a url do banco de dados
        dataSource.setUsername("postgres"); // declara o usuário do banco de dados
        dataSource.setPassword("postgres"); //e a senha
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL); // Corrigido para Database.POSTGRESQL
        adapter.setShowSql(true); // isso é para mostrar o sql no console
        adapter.setGenerateDdl(true); //e isso daq é para criar as tabelas automaticamente
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect"); // isso é para definir o dialeto do banco de dados
        return adapter;
    }
}
