# E-commerce Java SENAI

Backend em Java com Spring Boot para estudo de um domínio de e-commerce. O projeto modela usuários, pedidos e os estados do ciclo de um pedido usando Spring Data JPA e MySQL.

## Domínio atual

### Usuário

A entidade `Usuario` representa o cliente ou usuário da loja e possui:

- `id`;
- `nome`;
- `email`;
- `telefone`;
- `senha`;
- `roles`.

O modelo usa validações Jakarta Bean Validation:

- nome, e-mail e senha não podem ficar em branco;
- e-mail deve ter formato válido;
- senha deve ter entre 6 e 20 caracteres.

### Pedido

A entidade `Pedido` possui:

- `id`;
- `momento`, armazenado como `Instant`;
- `status`, baseado no enum `StatusPedido`;
- `cliente`, relacionado a `Usuario` com `@ManyToOne`.

Status disponíveis:

```text
AGUARDANDO_PAGAMENTO
PAGO
ENVIADO
ENTREGUE
CANCELADO
```

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
├── mvnw / mvnw.cmd
├── .env.example
└── src/
    ├── main/
    │   ├── java/com/biolab/ecommerce/
    │   │   ├── EcommerceApplication.java
    │   │   └── entities/
    │   │       ├── Pedido.java
    │   │       ├── StatusPedido.java
    │   │       └── Usuario.java
    │   └── resources/application.properties
    └── test/
        └── java/com/biolab/ecommerce/
            └── EcommerceApplicationTests.java
```

## Requisitos

- JDK 25;
- MySQL Server;
- Maven Wrapper, incluído no projeto;
- IDE com suporte a Spring Boot, como IntelliJ IDEA, Eclipse ou VS Code.

## Configuração do banco

A aplicação está configurada para usar o banco MySQL `loja` na porta `3306`:

```text
URL: jdbc:mysql://localhost:3306/loja
Porta: 8080
```

O Hibernate usa `spring.jpa.hibernate.ddl-auto=update`, podendo criar ou atualizar as tabelas conforme as entidades. Em produção, prefira migrations controladas e não dependa de atualização automática do schema.

### Atenção às variáveis

O arquivo `application.properties` atualmente espera estas variáveis:

```env
DB_URL=jdbc:mysql://localhost:3306/loja
db_name=root
db_pass=sua_senha
```

Já o `.env.example` contém `DB_USERNAME` e `DB_PASSWORD`. Esses nomes não são usados diretamente pelo Spring. Ajuste os nomes no ambiente ou no `application.properties` antes de executar:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Não versione senhas reais ou arquivos `.env`.

## Como executar

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux ou macOS

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

Também é possível abrir o projeto na IDE e executar `EcommerceApplication`.

## Build

Windows:

```powershell
.\mvnw.cmd clean package
```

Linux ou macOS:

```bash
./mvnw clean package
```

O artefato será gerado em `target/`.

## Testes

Execute o teste de carregamento do contexto:

```bash
./mvnw test
```

No Windows, use `./mvnw.cmd test`.

O projeto possui atualmente um teste básico que verifica se o contexto do Spring Boot é carregado.

## Estado do projeto

O repositório contém as entidades e a configuração inicial do domínio. Controllers, repositories e endpoints REST ainda não aparecem na estrutura atual; portanto, o backend ainda não disponibiliza operações HTTP de pedidos ou usuários.

## Segurança

- As senhas devem ser armazenadas com hash, nunca em texto puro.
- Use variáveis de ambiente para credenciais do banco.
- Revise o mapeamento de `roles` antes de persistir em produção, pois o campo é um array Java sem uma estratégia explícita de conversão JPA no estado atual.

## Objetivo

Praticar modelagem de entidades, relacionamentos JPA, enums de domínio, validação de dados e configuração de um projeto Spring Boot conectado ao MySQL.
