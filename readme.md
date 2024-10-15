# SafeColeta

SAFE-COLETA é uma API RESTful para o gerenciamento de coleta de resíduos, incluindo o cadastro e consulta de caminhões, moradores, notificações e agendamentos de coleta. A aplicação utiliza Spring Boot e MySQL para armazenamento de dados.

## Índice

- Visão Geral do Deploy
- Tecnologias Utilizadas
- Instalação
- Configuração
- Testes
- [Postman] Endpoints da API no servidor Azure & Local
- Testes dos endpoints

# Visão Geral do Deploy

Atualmente, o projeto está em deploy no Azure com um banco de dados MySQL dedicado, portanto, ele não depende do container MySQL criado no Docker Compose (descrito abaixo), que serve apenas como uma alternativa para executar o projeto localmente.

A URL base da API em produção é:
   ```sh
   https://fiap-safecoleta-app-dev-argfcjc5gqbzfbdm.eastus2-01.azurewebsites.net
   ```
Para acessar os endpoints, basta adicionar o caminho do endpoint ao final desta URL.

# Tecnologias Utilizadas

- Java 17: Linguagem de programação principal.
- Spring Boot: Framework para criação de aplicações REST.
- Maven.
- MySQL: Banco de dados relacional.
- Docker & Docker Compose: Gerenciamento de containers.
- JUnit 5 & Mockito: Testes unitários.
- Swagger (Springdoc OpenAPI): Documentação interativa da API.
- Azure App Service.
- Azure Database for MySQL.
- GitHub Actions.

# Instalação

1. Clone o repositório:
```sh

git clone https://github.com/viniciuslopes98/SAFE-COLETA.git cd
SAFE-COLETA

```

2. Certifique-se de ter o Docker e Docker Compose instalados.


# Configuração
Configuração de Variáveis de Ambiente
Para executar o projeto localmente, configure as variáveis de ambiente necessárias no arquivo `application-dev.properties` e `docker-compose.yml`.


Edite o arquivo `application-dev.properties` localizado em `src/main/resources/application-dev.properties` para incluir as variáveis de ambiente de conexão com o banco de dados:

```
server.port=8080
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USER}
spring.datasource.password=${DATABASE_PWD}
spring.jpa.show-sql=true
spring.jpa.open-in-view=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always

```

Configure o banco de dados MySQL no `docker-compose.yml` (apenas para execução local):
```
services:
  db:
    image: mysql:8.0
    container_name: safecoleta_mysql
    environment:
      - MYSQL_ROOT_PASSWORD=root_pass
      - MYSQL_DATABASE=safecoleta
    ports:
      - "3306:3306"

  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - PROFILE=dev
      - DATABASE_URL=jdbc:mysql://db:3306/safecoleta?createDatabaseIfNotExist=true
      - DATABASE_USER=root
      - DATABASE_PWD=root_pass

```


## Executando o Projeto Local

### Usando Docker Compose
1. No diretório raiz do projeto, inicie os containers:
```sh

docker-compose up --build

```
2. Acesse a aplicação em http://localhost:8080.

# Testes

### Para executar os testes unitários:

```sh

mvn test

```

# Endpoints da API no servidor Azure
Base URL da API: 
```sh

https://fiap-safecoleta-app-dev-argfcjc5gqbzfbdm.eastus2-01.azurewebsites.net

```

## Testes de API com Postman

Utilize o Postman para testar os endpoints da API. Um arquivo de coleção do Postman `SafeColetaDevops_collection.json` está incluído no projeto em `\src\main\java\br\com\fiap\safecoleta\collection\SafeColetaDevops_collection`.

![image](https://github.com/user-attachments/assets/0fd26e58-8e22-4e09-ba27-42f22ccbbb3b)


### ATENÇÃO: ``  Para testar local basta substituir a URL base por http://localhost:8080   ``


## Caminhão
- POST Cadastrar um novo caminhão `/api/caminhao/cadastro`:
   
   - Body:

```sh
    {
     "placa": "DUR4534",
     "motorista": "Lucas Marques",
     "status": "ativo",
     "ultimaAtualizacao": "2024-05-23T12:32:00"
   }
```
- GET Consultar caminhão por ID `/api/caminhao/id/{id}`


## Morador
- POST Cadastrar um novo morador `/api/morador/cadastro`:
   
   - Body:

```sh
{
  "nome": "Ana Maria",
  "email": "ana.maria@gmail.com"
}

```
- GET Consultar morador por ID `/api/morador/id/{id}`

## Notificação
IMPORTANTE: Para cadastrar uma notificação, é necessário que um morador já exista.
- POST Cadastrar uma nova notificação `/api/notificacao/cadastro`:
 
   - Body:

```sh
{
  "moradorId": 1,
  "mensagem": "Boa tarde, prezado(a) morador(a). Agradecemos por sua paciência, sua solicitação foi confirmada. A coleta será feita no dia 24/05/2024 às 14:00 horas. Qualquer dúvida entre em contato com nossa central. Obrigado por contar conosco!",
  "dataNotificacao": "2024-05-24T14:30:00",
  "lida": true
}


```
- GET Consultar notificação por ID `/api/notificacao/id/{id}`

## Agendamento de Coleta
IMPORTANTE: Para cadastrar um agendamento, é necessário que um caminhão já exista.
- POST Cadastrar um novo agendamento de coleta `/api/agendamento/cadastro`:
 
   - Body:

```sh
{
  "caminhaoId": 1,
  "tipoResiduos": "Comum",
  "dataAgendamento": "2024-06-22",
  "horario": "14:30",
  "endereco": "Av. Costa Eduardo, 267 SAO PAULO-SP",
  "confirmado": true
}

```
- GET Consultar agendamento por ID `/api/agendamento/id/{id}`
- GET Listar todos os agendamentos `/api/agendamento/agendamentos`
- DELETE Excluir agendamento por ID `/api/agendamento/id/{id}`
- PUT Atualizar agendamento por ID `/api/agendamento/id/{id}`

