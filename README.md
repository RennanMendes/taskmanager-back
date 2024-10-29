<div align="center">    
    <h1>Taskmanager</h1>

![GitHub License](https://img.shields.io/github/license/RennanMendes/taskmanager-backend)
![Static Badge](https://img.shields.io/badge/tested_with-JUnit-red)
![Static Badge](https://img.shields.io/badge/status-in_progress-brightgreen)
</div>

## Sobre

Este projeto foi desenvolvido com o objetivo de explorar conceitos essenciais em uma aplicação real, incluindo GraphQL
para comunicação com APIs, Redis como mecanismo de cache, e a Arquitetura Limpa para organizar as camadas de negócio e
infraestrutura.

### Funcionalidades Principais

As principais funcionalidades oferecidas pelo projeto incluem:

- Gerenciamento de Projetos: funcionalidades como criação, atualização, exclusão e busca de projetos.
- Gerenciamento de Tarefas: criação, atualização, exclusão e busca de tarefas associadas a projetos.
- Filtros: filtro de tarefas com base em atributos como ID do projeto e status.

## Tecnologias utilizadas

| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain.svg" alt="Java Icon" width="40" height="40" /> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" alt="Spring Icon" width="48" height="48" /> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/graphql/graphql-plain.svg" alt="GraphQL Icon" width="40" height="40" /> |  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-plain.svg" alt="`PostgreSQL` Icon" width="40" height="40" />  | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" alt="Intellij Icon" width="40" height="40" /> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-plain-wordmark.svg" alt="Docker Icon" width="40" height="40" /> |
|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------:|--------------------------------------------------------------------------------------------------------------------------------------------------:|
|                                                            Java                                                             |                                                                    Spring                                                                     |                                                                   GraphQL                                                                   |                                                                PostgreSQL                                                                |                                                                  IntelliJ                                                                  |                                                                                                                                          IntelliJ |

## Instalação

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/products/docker-desktop/)

Siga estas etapas para instalar e executar o projeto:

1. Clone o repositório: `git@github.com:RennanMendes/taskmanager-backend.git`

2. Compile o projeto usando o Maven: `mvn clean install`

3. Certifique-se de estar com os contêineres docker abaixo ativo

4. Execute o projeto: `java -jar target/taskmanager-0.0.1-SNAPSHOT.jar`

```
docker run --name postgres_container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=db_taskmanager -p 5431:5432 -d postgres:latest
docker run --name my-redis -p 6379:6379 -d redis redis-server --requirepass "1234"
```

Após configurar e iniciar o projeto, a aplicação pode ser acessada localmente pelo GraphiQL em: http://localhost:8080/graphiql?path=/graphql

## Exemplo de Consultas GraphQL

Abaixo estão alguns exemplos de queries e mutations para facilitar o uso da API.

### Consultas

Buscar projeto por id
```
query {
  buscarProjetoPorId(id: "1d8cb938-ec6b-11ed-affa-0242ac120002") {
    id
    nome
    descricao
    dataInicio
    dataFim
    status
    prioridade
  }
}
```

Buscar projeto por nome
```
query {
  buscarProjetoPorNome(nome: "Projeto A") {
    id
    nome
    descricao
  }
}
```

Buscar todos os projetos
```
query {
  buscarTodosOsProjetos {
    id
    nome
    descricao
  }
}
```

Buscar todos os projetos
```
query {
  buscarTodosOsProjetos {
    id
    nome
    descricao
  }
}
```

### Mutations

Criar projeto
```
mutation {
  criarProjeto(projeto: {
    nome: "Novo Projeto",
    descricao: "Descrição do projeto",
    dataInicio: "2024-10-28",
    dataFim: "2024-12-28",
    status: EXECUCAO,
    prioridade: ALTA
  }) {
    id
    nome
  }
}
```

Atualizar projeto
```
mutation {
  atualizarProjeto(id: "2b9cc294-ec6b-11ed-affa-0242ac120002", projeto: {
    nome: "Projeto Atualizado",
    descricao: "Nova descrição",
    status: IMPEDIMENTO,
    prioridade: MEDIA
  }) {
    id
    nome
    descricao
    dataInicio
    dataFim
    status
    prioridade
  }
}
```

Excluir projeto
```
mutation {
  excluirProjeto(id: "3dacb2de-ec6b-11ed-affa-0242ac120002")
}
```