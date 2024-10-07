# SafeColeta

SafeColeta é um serviço RESTful para gerenciamento de caminhões de coleta de lixo inteligente. Este projeto utiliza Spring Boot para criar a aplicação e PostgreSQL como banco de dados.

## Requisitos

- Docker
- Docker Compose
- Java 11
- Maven

## Estrutura do Projeto

- `src/main/java`: Código-fonte Java
- `src/main/resources`: Recursos da aplicação (arquivos de configuração)
- `target`: Diretório onde o JAR compilado será gerado
- `pom.xml`: Arquivo de configuração do Maven
- `Dockerfile`: Arquivo de configuração do Docker para a aplicação
- `docker-compose.yml`: Arquivo de configuração do Docker Compose para gerenciar contêineres

## Configuração do Ambiente

### Compilando a Aplicação

1. Faça login no Docker Hub:
   ```sh
   docker login
   ```

2. Puxe a imagem do Docker Hub:
   ```sh
   docker pull amandasacchi22k736/app:welcome-21-alpine
   ```

3. Crie a rede Docker (se ainda não existir):
   ```sh
   docker network create safecoleta-network
   ```

4. Inicie o contêiner Oracle XE na rede safecoleta-network:
   ```sh
   docker run --name oracle-xe --network safecoleta-network -d -p 1521:1521 -p 5500:5500 gvenzl/oracle-xe
   ```

5. Inicie a aplicação na rede safecoleta-network:
   ```sh
   docker run --name safecoleta-app --network safecoleta-network -d -p 8080:8080 \
   -e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle-xe:1521/xepdb1 \
   -e SPRING_DATASOURCE_USERNAME=system \
   -e SPRING_DATASOURCE_PASSWORD=senha_segura \
   -e SPRING_DATASOURCE_DRIVER-CLASS-NAME=oracle.jdbc.OracleDriver \
   amandasacchi22k736/app:welcome-21-alpine
   ```

## Configurações do Banco de Dados

As configurações do banco de dados são definidas no arquivo `docker-compose.yml`:

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/mydb
  SPRING_DATASOURCE_USERNAME: user
  SPRING_DATASOURCE_PASSWORD: password
```

Certifique-se de que as credenciais do banco de dados correspondam às configurações da sua aplicação Spring Boot.

## Endpoints da API

Abaixo estão alguns dos principais endpoints disponíveis na aplicação:

- `GET /api/caminhoes`: Lista todos os caminhões de coleta
- `POST /api/caminhoes`: Adiciona um novo caminhão de coleta
- `GET /api/coletas`: Lista todas as coletas agendadas
- `POST /api/coletas`: Agenda uma nova coleta

## Segurança

O projeto inclui uma configuração de segurança básica utilizando Spring Security. As configurações de segurança podem ser encontradas no pacote `br.com.fiap.safecoleta.config.security`.

## Testando a Aplicação

Você pode testar a aplicação usando ferramentas como Postman ou qualquer cliente HTTP de sua preferência. Para facilitar, um arquivo de coleção do Postman (`SafeColeta.postman_collection.json`) está incluído no projeto.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.
