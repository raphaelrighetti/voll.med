# Voll.med

Estudos de Spring Boot do curso Spring Boot 3: desenvolva uma API Rest em Java da Alura.

Neste curso irei desenvolver uma API REST para um consultório médico fictício chamado Voll.med.
Como o curso é focado somente na construção de uma API REST utilizando Java e Spring, não terá nenhum tipo de interface do usuário, mas fica como desafio para mim fazer uma aplicação client-side que integra com essa API.

## Aula 1

### Criando o projeto

Nessa aula vi como gerar um projeto Spring base com algumas dependências a partir do [Spring Initializr](https://start.spring.io/), importar ele no IntelliJ e rodar a aplicação com um "Hello, World!". Também aprendi a estrutura de um projeto Spring e um pouco da história do framework.

## Aula 2

### Primeira requisição POST

Nessa aula vi como fazer o controller responder às requsições POST, pegando o corpo da requisição e imprimindo ele no console a partir de um objeto DTO (Data Transfer Object) intanciado a partir de um Record (recurso do Java que não havia utilizado até agora por ter focado apenas no Java 8 no começo)

## Aula 3

### Persistência dos dados

Nessa aula vi como persistir os dados recebidos na requisição utilizando repositories, interfaces que estendem JpaRepository, que vem com uma série de métodos para fazer a integração com o banco de dados. Aprendi também a utilizar o Flyway, ferramenta de Migrations que o Spring dá suporte, e boas práticas para trabalhar com ele.
Outro tópico da aula foi fazer a validação dos dados recebidos na requisição utilizando o módulo Validation do Spring, que possui várias annotations que facilitam muito na hora de fazer essas validações.

Além do conteúdo da aula, como no curso estamos utilizando MySQL como banco de dados, resolvi aprender um pouco sobre docker e utilizar o MySQL a partir um container para manter a minha máquina com um bom desempenho.

## Aula 4

### Requisições GET e Paginação

Nessa aula vi como fazer a API responder à requisições GET, retornando os dados aplicando uma paginação com o uso da interface Pageable e os dados em uma Page em vez de uma lista.

Também vi como mudar o jeito que a paginação irá funcionar com o uso de query strings na própria requisição e como mudar o padrão usado pelo Spring com o uso da annotation @PageableDefault.