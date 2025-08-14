# RPG References API

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2+-green.svg)
![Maven](https://img.shields.io/badge/Maven-4.0-orange.svg)

### 📖 Descrição

API RESTful desenvolvida para atuar como o backend de um aplicativo organizador de fichas de um RPG customizado. O objetivo é centralizar as regras de negócio, facilitar a criação de novas habilidades, acelerar cálculos de status e agilizar o fluxo do jogo.

### ✨ Principais Funcionalidades

* **Gerenciamento de Fichas:** Lógica completa para criar, ler, atualizar e deletar personagens (`characters`), habilidades (`skills`), armas (`weapons`), etc.
* **Motor de Regras de RPG:** Lógica para calcular o resultado de ações complexas, como a conjuração de habilidades com diferentes tipos de energia.
* **Geração de Strings para Bots:** Formata os resultados de ações (como dano) em strings prontas para serem usadas por bots de dados (ex: em Discord, Roll20), como `/r 2d8 + 5`.
* **Organização Intuitiva:** Estrutura os dados das fichas de forma a facilitar a exibição e o gerenciamento pela interface gráfica.

### 🛠️ Tecnologias Utilizadas

* **Java 21:** Versão mais recente da linguagem, com suporte a Records, Virtual Threads, etc.
* **Spring Boot 3.2+:** Framework principal para a construção da API.
* **Maven:** Gerenciador de dependências e build do projeto.
* **Spring Data JPA / Hibernate:** Para a camada de persistência e mapeamento objeto-relacional.
* **Spring Security:** Para a segurança da API, com autenticação e autorização baseadas em JWT (JSON Web Token).
* **PostgreSQL / Oracle:** Banco de dados relacional para armazenar os dados do jogo.
* **Springdoc OpenAPI:** Para a geração automática da documentação da API com Swagger UI.

### ✅ Pré-requisitos

Antes de começar, garanta que você tenha os seguintes softwares instalados:
* [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Apache Maven 3.8+](https://maven.apache.org/download.cgi)
* Uma instância do **PostgreSQL** (ou **Oracle**) rodando.
* [Git](https://git-scm.com/)

### 🚀 Como Rodar a Aplicação

Siga os passos abaixo para executar o projeto em seu ambiente local.

**1. Clone o Repositório**

**2. Configure o Banco de Dados**
Este projeto usa um arquivo `application.properties` junto com o um arquivo `.env` para configurar a conexão com o banco de dados.

* Crie (ou renomeie a partir de `application.properties.example`, se houver) o arquivo em `src/main/resources/application.properties`.
* Altere as seguintes propriedades com as credenciais do seu banco de dados local:

```properties
# Configuração da Porta do Servidor
server.port=${APP_PORT}

# Configuração do Banco de Dados (Exemplo para PostgreSQL)
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# Configuração do Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**3. Execute a Aplicação**
Use o Maven Wrapper para iniciar a aplicação. Ele cuidará de baixar as dependências e iniciar o servidor Tomcat embutido.

```bash
./mvnw spring-boot:run
```
Após alguns segundos, a API estará rodando e pronta para receber requisições!

### 📄 Documentação da API (Swagger)

A documentação completa dos endpoints é gerada automaticamente e está disponível de forma interativa com o Swagger UI.

* **Acesse a documentação em:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 🏛️ Arquitetura

O projeto segue os princípios da **Arquitetura Limpa (Clean Architecture)**, separando o código em camadas de responsabilidade bem definidas: `domain`, `application` (serviços) e `infrastructure` (controllers, repositórios).

### 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---