# SOFTPLAN - PESSOAS

API desenvolvida seguindo o modelo de maturidade Richardson para o desafio softplan.
Contendo paginação, validação, versionamento de API, testes, /source, autenticação basic.

A principio utilizei MySQL durante o desenvolvimento, também para garantir a correnta modelagem do banco de dados.
Para deploy no docker, a API utiliza um H2 temporário para não deixar o container tão pesado.

| URL | NOME |
| ------ | ------ |
| localhost:8080/swagger | swagger |

dockerhub: https://hub.docker.com/r/pedrobipede/softplan-pessoas
frontend: https://github.com/dev-vasconcelos/softplan-pessoas-front
