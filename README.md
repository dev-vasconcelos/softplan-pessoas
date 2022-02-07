# SOFTPLAN - PESSOAS

API desenvolvida seguindo o modelo de maturidade Richardson para o desafio softplan.
Contendo paginação, validação, versionamento de API, testes, /source, autenticação basic.

A principio utilizei MySQL durante o desenvolvimento, também para garantir a correnta modelagem do banco de dados.
Para deploy no docker, a API utiliza um H2 temporário para não deixar o container tão pesado.

Tecnologia front-end: NextJS  
Tecnologia back-end: Java Spring Boot 

| URL | NOME |
| ------ | ------ |
| localhost:8080/swagger | swagger |
| localhost:3000/ | front-end |
| localhost:8080/source | links dos repositórios |

dockerhub: https://hub.docker.com/r/pedrobipede/softplan-pessoas 

frontend: https://github.com/dev-vasconcelos/softplan-pessoas-front
