# Heroes API
API para gerenciamento de heróis construída com Spring Webflux

## Ambiente

* Java 11
* Maven 3.6.3 (ou superior)
* Docker & Docker Compose
* DynamoDB

## Executando o projeto

No terminal, entre na pasta raiz do projeto e execute os seguintes comandos:

* Suba a instância do *DynamoDB* disponibilizada através de um container docker:

```bash
docker-compose up
```

* Faça o build do projeto
```bash
./mvnw clean install
```

* Crie a table e popule o banco através das classes executáveis *HeroesData* e *HeroesTable*

* Suba a aplicação:
```bash
./mvnw spring-boot:run
``` 

Os serviços da `api` ficarão disponíveis em `http://localhost:8080/heroes`.
Consulte o `swagger` para verificar o catálogo de serviços disponíveis em `http://localhost:8080/swagger-ui-reactive-api.html`.