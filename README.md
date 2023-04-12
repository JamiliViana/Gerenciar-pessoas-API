# API para gerenciar pessoas

Este projeto é uma API RESTful desenvolvida em Java para gerenciar pessoas, ela permite:
-	Criar uma pessoa
-	Editar uma pessoa
-	Consultar uma pessoa
-	Listar pessoas
-	Criar endereço para pessoa
-	Listar endereços da pessoa
-	Poder informar qual endereço é o principal da pessoa  

### Tecnologias utilizadas:

- Java 11
- Spring Boot 2.5.2
- Banco de dados H2 (em memória)
- Maven

### Endpoints disponíveis:

- GET http://localhost:8080/pessoa/listaPessoas retorna uma lista de todas as pessoas cadastradas.
- GET http://localhost:8080/pessoa/{nomePessoa} retorna uma pessoa com o nome especificado.
- POST http://localhost:8080/pessoa cria uma nova pessoa (nome, e data de nascimento) com as informações fornecidas pelo usuário.
- POST http://localhost:8080/endereco/{nomePessoa} cria umm novo endereço (logradouro, cep, número, e cidade) para uma pessoa de acordo com o nome da pessoa especificado.
- PUT http://localhost:8080/pessoa/{nomePessoa} atualiza as informações de uma pessoa com o nome especificado, e com as informações para atualizar fornecidas pelo usuário.
- GET http://localhost:8080/endereco/{nomePessoa} retorna uma lista de todos endereços cadastrados para a pessoa com o nome especificado.
- GET http://localhost:8080/pessoa/enderecoPrincipal/{nomePessoa}/{idEndereco} retorna o endereço que foi configurado atrvés do id para ser o principal da pessoa esfecificada pelo nome.

Observações:

- Os dados da pessoa incluem nome, data de nascimento e uma lista de endereços.
- Os dados dos endereços incluem logradouro, cep, número, cidade, e uma pessoa associada à ele.
- O banco de dados utilizado é o H2, que é um banco de dados em memória. Isso significa que todas as informações são armazenadas apenas enquanto o projeto estiver sendo executado. Quando o projeto é encerrado, todas as informações são perdidas.
