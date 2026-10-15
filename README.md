# 🎭 Memelândia — Plataforma de Gerenciamento de Memes

![Java](https://img.shields.io/badge/Java-Backend-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Microservices-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven)
![Arquitetura](https://img.shields.io/badge/Arquitetura-Microsserviços-blue)

## 📌 Sobre o projeto

A **Memelândia** é uma aplicação desenvolvida como projeto final de curso, com o objetivo de aplicar na prática conceitos de desenvolvimento backend utilizando Java e Spring Boot.

O sistema permite gerenciar usuários, categorias e memes, utilizando uma arquitetura baseada em microsserviços para separar responsabilidades e facilitar a manutenção e a evolução da aplicação.

A comunicação entre os serviços é realizada por meio de APIs REST, com utilização do Spring Cloud OpenFeign para simplificar as chamadas HTTP entre aplicações.

O projeto também contempla boas práticas de desenvolvimento, como validação de dados, tratamento de exceções, persistência com JPA e Hibernate e testes automatizados.

## 🎯 Objetivos

- Desenvolver APIs REST utilizando Java e Spring Boot.
- Aplicar conceitos de arquitetura de microsserviços.
- Implementar operações CRUD para usuários, categorias e memes.
- Integrar microsserviços utilizando OpenFeign.
- Aplicar validações nas requisições e regras de negócio.
- Tratar exceções e retornar respostas HTTP apropriadas.
- Implementar testes automatizados.
- Facilitar a execução do projeto em diferentes ambientes.

## 🏗️ Arquitetura do sistema

A Memelândia utiliza os seguintes componentes:

| Componente | Responsabilidade |
|---|---|
| **User Service** | Gerenciamento de usuários e validação da existência de usuários. |
| **Meme Service** | Gerenciamento de memes e categorias. |
| **Aplicação principal Memelândia** | Integração com os microsserviços e disponibilização das funcionalidades da aplicação existente. |

As categorias são gerenciadas pelo `meme-service`, não existindo um microsserviço independente para essa responsabilidade.

### 🔄 Comunicação entre os serviços

Os microsserviços se comunicam por meio de APIs REST. O Spring Cloud OpenFeign é utilizado para realizar chamadas HTTP entre eles.

Exemplo do fluxo de cadastro de um meme:

1. A aplicação recebe os dados do meme e o identificador da categoria.
2. O `meme-service` verifica se a categoria existe.
3. O serviço consulta o `user-service` para verificar se o usuário informado existe.
4. Os dados são convertidos para a entidade de persistência.
5. O meme é salvo no banco de dados.
6. A aplicação retorna a resposta da operação.

Esse fluxo permite validar informações relacionadas a diferentes serviços antes de concluir o cadastro.

## 🛠️ Tecnologias utilizadas

- **Java** — linguagem de programação.
- **Spring Boot** — desenvolvimento das aplicações backend.
- **Spring Web** — criação de APIs REST.
- **Spring Data JPA** — persistência e acesso aos dados.
- **Hibernate** — mapeamento objeto-relacional.
- **Spring Cloud OpenFeign** — comunicação HTTP entre microsserviços.
- **Bean Validation** — validação de dados, quando configurada.
- **Maven** — gerenciamento de dependências e build.
- **JUnit** — testes automatizados.
- **Mockito** — simulação de dependências durante os testes.
- **H2 Database** — banco de dados em memória, quando utilizado na configuração dos serviços.
- **Docker e Docker Compose** — conteinerização e execução integrada, conforme a configuração do ambiente.

## ✨ Funcionalidades

### 👤 Gerenciamento de usuários

- Cadastro de usuários.
- Consulta de usuários.
- Atualização de dados.
- Exclusão de usuários.
- Validação de informações obrigatórias.
- Tratamento de conflitos de e-mail.
- Verificação da existência de usuários para operações relacionadas a memes.

### 🗂️ Gerenciamento de categorias

- Cadastro de categorias.
- Listagem das categorias cadastradas.
- Consulta por identificador.
- Atualização de categorias.
- Exclusão de categorias, respeitando as regras de integridade dos dados.
- Associação entre categorias e memes.

O gerenciamento de categorias é responsabilidade do `meme-service`.

### 😂 Gerenciamento de memes

- Cadastro de memes.
- Consulta e listagem de memes.
- Atualização de informações.
- Exclusão de memes.
- Associação dos memes às categorias.
- Associação dos memes aos usuários responsáveis pela publicação.
- Validação da existência da categoria antes do cadastro.
- Validação da existência do usuário por meio da comunicação entre serviços.

## 🛡️ Validação e tratamento de exceções

A aplicação utiliza validações e tratamento de exceções para garantir maior consistência nas operações e facilitar a identificação de erros.

Entre os cenários tratados estão:

- Dados obrigatórios ausentes ou inválidos.
- Usuário não encontrado.
- Categoria não encontrada.
- Meme não encontrado.
- E-mail já cadastrado.
- Falhas de comunicação entre microsserviços.
- Erros inesperados durante o processamento das requisições.

O objetivo é retornar códigos HTTP adequados e mensagens que ajudem o consumidor da API a compreender o resultado da operação.

Quando configurado, o tratamento global de exceções pode padronizar as respostas JSON, incluindo o status HTTP, o código do erro e a mensagem correspondente.

## 🧪 Testes automatizados

Os testes automatizados permitem verificar as regras de negócio e reduzir o risco de regressões durante a manutenção da aplicação.

Com JUnit e Mockito, é possível testar os serviços isoladamente e simular o comportamento de repositórios e dependências externas.

Exemplos de cenários de teste:

- Cadastro de usuário com sucesso.
- Tentativa de cadastrar e-mail já existente.
- Consulta de usuário inexistente.
- Cadastro de meme com categoria inexistente.
- Cadastro de meme com usuário inexistente.
- Atualização e exclusão de registros.
- Tratamento de exceções de negócio.
- Falhas nas chamadas entre microsserviços.

Para executar os testes de um módulo, acesse o diretório que contém seu `pom.xml` e execute:

```bash
mvn test
```

Para compilar e empacotar o projeto:

```bash
mvn clean package
```

## 🐳 Docker e Docker Compose

O projeto pode utilizar Docker e Docker Compose para padronizar a execução dos microsserviços e suas dependências.

Após configurar os Dockerfiles e o arquivo `compose.yaml` na raiz do projeto, o ambiente poderá ser iniciado com:

```bash
docker compose up --build
```

Para executar os serviços em segundo plano:

```bash
docker compose up -d --build
```

Para acompanhar os logs:

```bash
docker compose logs -f
```

Para interromper os serviços:

```bash
docker compose down
```

**Observação:** os comandos de inicialização integrada dependem da configuração efetiva dos Dockerfiles, das portas, das variáveis de ambiente e das dependências de cada serviço.

## ⚙️ Executando localmente

### Pré-requisitos

- JDK compatível com a versão Java definida nos módulos.
- Maven ou Maven Wrapper.
- Git.
- Configurações de banco de dados e comunicação entre serviços.

### Clonando o repositório

```bash
git clone https://github.com/dowglasSantos/memelandia.git
cd memelandia
```

### Executando um serviço

Entre no diretório do serviço desejado e execute:

```bash
mvn spring-boot:run
```

Se o módulo possuir Maven Wrapper, no Linux ou macOS utilize:

```bash
./mvnw spring-boot:run
```

Cada serviço deve ser configurado com suas respectivas propriedades, incluindo a porta da aplicação, a conexão com o banco de dados e os endereços dos serviços chamados pelo OpenFeign.

## 📚 Documentação da API

Caso o Springdoc OpenAPI esteja habilitado, a documentação interativa poderá ser acessada pelo Swagger UI.

O endereço normalmente segue este formato:

```text
http://localhost:PORTA/swagger-ui/index.html
```

Substitua `PORTA` pela porta configurada no serviço desejado.

O Swagger UI permite visualizar os endpoints disponíveis, consultar os parâmetros das requisições e testar as operações da API.

## 📁 Estrutura do projeto

A estrutura abaixo representa a organização conceitual dos componentes, podendo variar conforme os diretórios reais do repositório.

```text
memelandia/
├── user-service/
│   ├── src/
│   └── pom.xml
├── meme-service/
│   ├── src/
│   └── pom.xml
├── memelandia/              # Aplicação principal, se separada
│   ├── src/
│   └── pom.xml
├── compose.yaml             # Docker Compose, quando configurado
└── README.md
```

O `meme-service` é responsável tanto pelos memes quanto pelas categorias. O `user-service` é responsável pelo gerenciamento de usuários.

## 🚀 Possíveis melhorias

Entre as melhorias que podem evoluir o projeto estão:

- Implementação de autenticação e autorização.
- Proteção dos endpoints de publicação de memes.
- Padronização das respostas de erro.
- Ampliação da cobertura de testes unitários e de integração.
- Configuração completa do Docker Compose.
- Centralização das configurações dos serviços.
- Monitoramento e observabilidade.
- Comunicação assíncrona por mensageria.
- Integração contínua para execução de testes e validação do build.

Esses itens devem ser considerados melhorias futuras caso ainda não estejam implementados.

## 👨‍💻 Autor

**Douglas Santos**

Projeto desenvolvido como parte da formação em desenvolvimento backend com Java.

- **GitHub:** [dowglasSantos](https://github.com/dowglasSantos)
- **Repositório:** [Memelândia](https://github.com/dowglasSantos/memelandia)

---

Desenvolvido com Java e Spring Boot.

