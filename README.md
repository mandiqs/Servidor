# Book Management API
***

### Dupla
- Amanda Queiroz Sobral
- Carlos Eduardo Domingues Hobmeier

### Link para o vídeo
https://youtu.be/ojkRh89FRfk

***

Esta é uma API de gerenciamento de livros e de autores construída com Spring Boot e configurada com autenticação JWT. A aplicação permite aos usuários visualizar, adicionar, atualizar e excluir livros e autores em um banco de dados. A API inclui segurança baseada em roles, permitindo que apenas administradores façam atualizações e exclusões de registros.

<img src="https://raw.githubusercontent.com/carloshobmeier/Assets/refs/heads/main/book_management_api/book_management.webp" width="350px">

## Funcionalidades

- Autenticação JWT: Proteção de endpoints sensíveis usando tokens JWT.
- Permissões Baseadas em Role: Apenas usuários com a role ADMIN podem fazer atualizações e exclusões de livros e de autores.
- Gestão de Livros e autores: Endpoints para listar, adicionar, atualizar e excluir livros e autores.
- Documentação da API com Swagger: Interface gráfica para explorar e testar os endpoints.
- Frontend em react para o CRUD completo de livros.


***

## Developed with:

<div style="display: inline_block"><br/>
    <img style="margin-top:4px;" align="center" alt="spring" height="30px" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
    <img style="margin-top:4px;" align="center" alt="kotlin" height="30px" src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
    <img style="margin-top:4px;" align="center" alt="react" height="30px" src="https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB" />
    <img style="margin-top:4px;" align="center" alt="jwt"src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens" />
    <img style="margin-top:4px;" align="center" alt="swagger" height="30px" src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white"/>
</div>

***

## Construir e Executar o Projeto:
A API estará disponível em http://localhost:8080/api

## Acessar o Swagger UI
Disponível em http://localhost:8080/api/swagger-ui/index.html#/

***
## FrontEnd
- npm install
- npm axios install
- npm run dev

***
## Endpoints
##### Autenticação
- POST /users/login => Autentica um usuário e retorna um token JWT.
- POST /users: Cria um novo usuário.
##### Gerenciamento de Livros
- GET /books: Lista todos os livros (acesso público).
- GET /books/{id}: Busca um livro pelo ID (acesso público).
- POST /books: Adiciona um novo livro (necessita autenticação).
- PUT /books/{id}: Atualiza um livro (somente para ADMIN).
- DELETE /books/{id}: Exclui um livro (somente para ADMIN).

##### Gerenciamento de Autores
- POST /authors: Adiciona um novo autor (necessita autenticação).
- PUT /authors/{authorId}/books/{bookId}: Associa um autor a um livro (necessita autenticação).
