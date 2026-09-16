# E-commerce Java SENAI

Backend de um sistema de e-commerce desenvolvido em **Java com Spring Boot**, utilizando **Spring Data JPA** e **MySQL**. O projeto foi desenvolvido para fins de estudo e prática de desenvolvimento de APIs REST, modelagem de entidades, relacionamentos JPA, validação de dados e operações CRUD.

## Funcionalidades

Atualmente, o projeto possui operações REST para:

- Usuários;
- Pedidos;
- Pagamentos;
- Categorias;
- Produtos.

O foco atual da atividade é a implementação dos métodos **POST, GET, PUT e DELETE** para produtos e categorias.

## Tecnologias

- Java 25;
- Spring Boot 4.1.1;
- Spring Web MVC;
- Spring Data JPA;
- Jakarta Bean Validation;
- MySQL;
- Lombok;
- Maven Wrapper;
- JUnit e Spring Boot Test.

## Estrutura do projeto

```text
ecommerceJavaSenai/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .env.example
└── src/
    ├── main/
    │   ├── java/com/biolab/ecommerce/
    │   │   ├── EcommerceApplication.java
    │   │   ├── controllers/
    │   │   │   ├── CategoriaController.java
    │   │   │   ├── PagamentoController.java
    │   │   │   ├── PedidoController.java
    │   │   │   ├── ProdutoController.java
    │   │   │   └── UsuarioController.java
    │   │   ├── DTOs/
    │   │   │   ├── CategoriaDTO.java
    │   │   │   ├── PagamentoDTO.java
    │   │   │   ├── PedidoDTO.java
    │   │   │   ├── ProdutoDTO.java
    │   │   │   └── UsuarioDTO.java
    │   │   ├── entities/
    │   │   │   ├── Categoria.java
    │   │   │   ├── Pagamento.java
    │   │   │   ├── Pedido.java
    │   │   │   ├── Produto.java
    │   │   │   ├── Usuario.java
    │   │   │   └── enums/
    │   │   │       ├── Role.java
    │   │   │       └── StatusPedido.java
    │   │   ├── repositories/
    │   │   │   ├── CategoriaRepository.java
    │   │   │   ├── PagamentoRepository.java
    │   │   │   ├── PedidoRepository.java
    │   │   │   ├── ProdutoRepository.java
    │   │   │   └── UsuarioRepository.java
    │   │   └── services/
    │   │       ├── CategoriaService.java
    │   │       ├── PagamentoService.java
    │   │       ├── PedidoService.java
    │   │       ├── ProdutoService.java
    │   │       └── UsuarioService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/biolab/ecommerce/
            └── EcommerceApplicationTests.java
```

## Requisitos

Antes de executar o projeto, tenha instalado:

- **JDK 25**;
- **MySQL Server**;
- Uma IDE com suporte a Spring Boot, como IntelliJ IDEA, Eclipse ou VS Code.

O projeto possui o **Maven Wrapper**, portanto não é necessário instalar o Maven separadamente.

## Configuração do banco de dados

A aplicação utiliza MySQL na porta `3306`. O banco utilizado atualmente é `ecommerce`.

A URL JDBC pode criar automaticamente o banco caso ele ainda não exista:

```env
DB_URL=jdbc:mysql://localhost:3306/ecommerce?createDatabaseIfNotExist=true
```

As credenciais utilizadas pelo `application.properties` são:

```env
DB_URL=jdbc:mysql://localhost:3306/ecommerce?createDatabaseIfNotExist=true
db_name=root
db_pass=sua_senha
```

O `application.properties` utiliza essas variáveis da seguinte forma:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${db_name}
spring.datasource.password=${db_pass}
```

> **Importante:** não versionar o arquivo `.env` com senhas reais. Utilize o `.env.example` como modelo e configure suas credenciais localmente.

### Criação automática das tabelas

O Hibernate está configurado com:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Dessa forma, durante o desenvolvimento, o Hibernate pode criar e atualizar as tabelas de acordo com as entidades JPA do projeto.

## Como executar

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux ou macOS

```bash
./mvnw spring-boot:run
```

A API será iniciada em:

```text
http://localhost:8080
```

Também é possível executar a classe `EcommerceApplication` diretamente pela IDE.

## Endpoints REST

### Categoria

Base URL:

```text
http://localhost:8080/categoria
```

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/categoria` | Cadastrar categoria |
| `GET` | `/categoria` | Listar todas as categorias |
| `GET` | `/categoria/{id}` | Buscar categoria por ID |
| `PUT` | `/categoria/{id}` | Atualizar categoria |
| `DELETE` | `/categoria/{id}` | Excluir categoria |

#### Exemplo — POST Categoria

```json
{
  "nome": "Eletrônicos"
}
```

#### Exemplo — PUT Categoria

```json
{
  "nome": "Informática"
}
```

---

### Produto

Base URL:

```text
http://localhost:8080/produto
```

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/produto` | Cadastrar produto |
| `GET` | `/produto` | Listar todos os produtos |
| `GET` | `/produto/{id}` | Buscar produto por ID |
| `PUT` | `/produto/{id}` | Atualizar produto |
| `DELETE` | `/produto/{id}` | Excluir produto |

#### Exemplo — POST Produto

```json
{
  "nome": "Notebook Lenovo",
  "descricao": "Notebook para estudos e trabalho",
  "preco": 3500.00,
  "imgUrl": "https://exemplo.com/notebook.jpg",
  "idCategoria": 1
}
```

#### Exemplo — PUT Produto

```json
{
  "nome": "Notebook Lenovo IdeaPad",
  "descricao": "Notebook atualizado para estudos e trabalho",
  "preco": 3200.00,
  "imgUrl": "https://exemplo.com/notebook-atualizado.jpg",
  "idCategoria": 1
}
```

> O `idCategoria` deve corresponder a uma categoria existente no banco de dados.

---

### Usuário

Base URL:

```text
http://localhost:8080/user/
```

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/user/` | Cadastrar usuário |
| `GET` | `/user/` | Listar usuários |

### Pedido

Base URL:

```text
http://localhost:8080/pedido
```

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/pedido` | Cadastrar pedido |
| `DELETE` | `/pedido/{id}` | Excluir pedido |

### Pagamento

Base URL:

```text
http://localhost:8080/pagamento
```

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/pagamento` | Cadastrar pagamento |

## Testando com Postman

Para validar o CRUD de **Categoria** e **Produto**, recomenda-se seguir esta ordem:

### Categoria

```text
1. POST   /categoria
2. GET    /categoria
3. GET    /categoria/{id}
4. PUT    /categoria/{id}
5. GET    /categoria/{id}
6. DELETE /categoria/{id}
```

### Produto

Como o produto possui uma relação com categoria, primeiro crie uma categoria e utilize o ID dela no cadastro do produto.

```text
1. POST   /categoria
2. POST   /produto
3. GET    /produto
4. GET    /produto/{id}
5. PUT    /produto/{id}
6. GET    /produto/{id}
7. DELETE /produto/{id}
```

No Postman, para requisições com JSON, utilize:

```text
Body → raw → JSON
```

E envie o header:

```text
Content-Type: application/json
```

## Build

### Windows

```powershell
.\mvnw.cmd clean package
```

### Linux ou macOS

```bash
./mvnw clean package
```

O arquivo `.jar` será gerado no diretório `target/`.

## Testes

Execute os testes automatizados com:

### Windows

```powershell
.\mvnw.cmd test
```

### Linux ou macOS

```bash
./mvnw test
```

## Status do projeto

O projeto está em desenvolvimento e possui a estrutura de camadas para uma API REST:

```text
Controller → Service → Repository → Database
```

As funcionalidades de CRUD de **Produto** e **Categoria** estão implementadas com operações de:

- `POST` — criação;
- `GET` — consulta;
- `PUT` — atualização;
- `DELETE` — exclusão.

## Segurança

- Não versionar senhas ou credenciais reais;
- utilizar variáveis de ambiente para acesso ao banco;
- armazenar senhas de usuários utilizando hash antes de uma utilização em produção;
- validar os dados recebidos pela API;
- em produção, considerar migrations controladas em vez de depender de `ddl-auto=update`.

## Objetivo

O objetivo do projeto é praticar o desenvolvimento de uma aplicação backend utilizando **Java, Spring Boot, JPA, MySQL e APIs REST**, trabalhando conceitos como:

- Modelagem de entidades;
- Relacionamentos JPA;
- DTOs;
- Services e Repositories;
- Controllers REST;
- Operações CRUD;
- Validação de dados;
- Persistência em banco de dados;
- Testes com Spring Boot.
