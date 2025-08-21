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
* **Docker** : Para conteinerização padronização de ambientes de teste e produção.

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

## 🏛️ Arquitetura: Domain-Driven Design (DDD) e Contextos Delimitados

Este projeto adota os princípios do **Domain-Driven Design (DDD)** para gerenciar a complexidade do negócio. Em vez de uma única camada de domínio monolítica, a aplicação é dividida em **Contextos Delimitados (Bounded Contexts)**. Cada contexto representa uma área de negócio coesa e possui seu próprio modelo, com limites bem definidos.

Esta abordagem resulta um **Monólito Modular**, onde cada contexto pode evoluir de forma independente e, se necessário, ser extraído para um microserviço no futuro com muito mais facilidade.

A regra principal é que as dependências sempre apontam para o "coração" do negócio, o `domain` de cada contexto.

### Estrutura de Pastas

```
br/ufal/orion/rpg/
│
├── users/         // Contexto de Identidade e Acesso
│   ├── domain/
│   ├── application/
│   └── infrastructure/
│
├── characters/             // Contexto de Personagens e Inventário
│   ├── domain/
│   ├── application/
│   └── infrastructure/
│
└── rulesengine/            // Contexto de Regras e Mecânicas do Jogo
    ├── domain/
    ├── application/
    └── infrastructure/
```

### Detalhamento dos Contextos Delimitados

#### **1. Contexto de Identidade e Acesso (`users`)**
* **Responsabilidade:** Gerenciar a autenticação e os dados do jogador como um usuário da plataforma. É o "portal de entrada" do sistema.
* **Entidades Principais:** `UserModel`.
* **Lógica:** Cadastro, login, gerenciamento de perfil e senhas.

#### **2. Contexto de Personagens (`characters`)**
* **Responsabilidade:** Gerenciar a ficha, o estado, o inventário e a progressão dos personagens. Este é o núcleo da experiência do jogador.
* **Entidades Principais:** `Character`, `InventoryItem` (ligando `Character` a `Weapon` ou `Armor`), `CharacterSkill` (a associação de uma habilidade aprendida).
* **Lógica:** Subir de nível, calcular atributos derivados, equipar e desequipar itens, gerenciar os "tanques" de energia (mana, aura, etc.).

#### **3. Contexto de Regras e Mecânicas (`rulesengine`)**
* **Responsabilidade:** Servir como o "livro de regras" do universo do jogo. É um catálogo de todas as possibilidades e mecânicas. Este contexto é, em grande parte, de "leitura" para os outros contextos.
* **Entidades Principais:** `Skill` (com suas evoluções), `Weapon`, `Item`, `Armor`, `EnergyType`, `SkillEnergyModifier`.
* **Lógica:** Definir o dano base de uma arma, o efeito de uma habilidade, o custo de energia e como os modificadores de energia alteram o comportamento das habilidades.

### 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---