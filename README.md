# Projetos Java Spring Boot

Este repositório contém múltiplos projetos Java Spring Boot organizados em diferentes diretórios. Abaixo está uma visão geral de cada projeto e suas funcionalidades.

## Estrutura do Repositório

- **backup's/**: Contém backups de projetos.

- **cardapio/**: Projeto de gerenciamento de cardápios. (Não finalizado)
- **firstpage/**: Projeto de exemplo para uma página inicial.
- **sistema/**: Projeto de exemplo para um sistema genérico.
- **sistemabiblioteca/**: Sistema de gerenciamento de biblioteca.
- **userdept/**: Projeto de exemplo para gerenciamento de usuários e departamentos.

## Projetos

### Cardápio

- **Descrição**: Projeto de exemplo para gerenciamento de cardápios.
- **Localização**: [cardapio/](cardapio/)
- **Ponto de Entrada**: [CardapioApplication.java](cardapio/src/main/java/com/example/cardapio/CardapioApplication.java)
- **Dependências**: Definidas em [pom.xml](cardapio/pom.xml)

### First Page

- **Descrição**: Projeto de exemplo para uma página inicial.
- **Localização**: [firstpage/](firstpage/)
- **Ponto de Entrada**: [FirstpageApplication.java](firstpage/src/main/java/com/example/firstpage/FirstpageApplication.java)
- **Dependências**: Definidas em [pom.xml](firstpage/pom.xml)
- **Templates**: [home.html](firstpage/src/main/resources/templates/home.html), [info.html](firstpage/src/main/resources/templates/info.html), [tutorial.html](firstpage/src/main/resources/templates/tutorial.html)

### Sistema

- **Descrição**: Projeto de exemplo para um sistema genérico.
- **Localização**: [sistema/](sistema/)
- **Ponto de Entrada**: [ConfiguracaoBancoDeDados.java](sistema/src/main/java/com/projeto/sistema/ConfiguracaoBancoDeDados.java)
- **Dependências**: Definidas em [pom.xml](sistema/pom.xml)

### Sistema Biblioteca

- **Descrição**: Sistema de gerenciamento de biblioteca.
- **Localização**: [sistemabiblioteca/](sistemabiblioteca/)
- **Ponto de Entrada**: [EmprestimoController.java](sistemabiblioteca/src/main/java/com/projeto/sistemabiblioteca/controllers/EmprestimoController.java)
- **Dependências**: Definidas em [pom.xml](sistemabiblioteca/pom.xml)
- **Templates**: [home.html](sistemabiblioteca/src/main/resources/templates/biblioteca/home.html), [listaLivros.html](sistemabiblioteca/src/main/resources/templates/livro/listaLivros.html), [emprestarLivro.html](sistemabiblioteca/src/main/resources/templates/emprestimo/emprestarLivro.html)

### User Department

- **Descrição**: Projeto de exemplo para gerenciamento de usuários e departamentos.
- **Localização**: [userdept/](userdept/)
- **Ponto de Entrada**: [UserdeptApplication.java](userdept/src/main/java/com/auladevsuperior/userdept/UserdeptApplication.java)
- **Controladores**: [userController.java](userdept/src/main/java/com/auladevsuperior/userdept/controllers/userController.java)
- **Dependências**: Definidas em [pom.xml](userdept/pom.xml)

## Como Executar

Cada projeto pode ser executado individualmente. Navegue até o diretório do projeto desejado e execute o seguinte comando:

```sh
./mvnw spring-boot:run

Faça um fork deste repositório.
Crie uma branch para sua feature (git checkout -b feature/nova-feature).
Commit suas mudanças (git commit -am 'Adiciona nova feature').
Faça um push para a branch (git push origin feature/nova-feature).
Crie um novo Pull Reques