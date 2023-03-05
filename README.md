# PGTEL API

---

Esse projeto trata-se de uma API para gerenciamento de estoque da empresa PGTEL. É utilizado a linguagem *Kotlin*, *Maven* como gerenciador de dependências e *Postgresql* como SGBD.

## Requisitos

- Java 17+
- Docker(Rodar o banco de dados)

## Como rodar

- Clone o repositório
- Execute o comando `docker-compose up -d` para subir o banco de dados
- Execute o comando `mvn clean install` para instalar as dependências
- Execute o comando `mvn spring-boot:run` para rodar a aplicação
- Acesse a documentação da API em `http://localhost:8080/swagger-ui.html`

## Como rodar os testes

- Execute o comando `mvn test`
- Para gerar o relatório de cobertura de testes, execute o comando `mvn jacoco:report`
- O relatório de cobertura de testes estará disponível em `target/site/jacoco/index.html`

## Dependências

- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Springfox Swagger
- H2 Database
- jjwt
- jaxb-api
- postgresql


## Arquitetura

A arquitetura utilizada foi a arquitetura hexagonal, que é uma arquitetura baseada em portas e adaptadores. Essa arquitetura é utilizada para separar a lógica de negócio da lógica de infraestrutura, facilitando a manutenção e a testabilidade do código. Está separado também por modulos para facilitar a separação de contextos. 

## Ambientes

Por se tratar de um projeto ainda em desenvolvimento não temos ambiente disponivel.