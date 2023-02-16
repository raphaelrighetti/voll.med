# Voll.med

## Spring Boot 3: desenvolva uma API Rest em Java

Estudos de Spring Boot do curso Spring Boot 3: desenvolva uma API Rest em Java da Alura.

Neste curso irei desenvolver uma API REST para um consultório médico fictício chamado Voll.med.
Como o curso é focado somente na construção de uma API REST utilizando Java e Spring, não terá nenhum tipo de interface do usuário, mas fica como desafio para mim fazer uma aplicação client-side que integra com essa API.

### Aula 1

#### Criando o projeto

Nessa aula vi como gerar um projeto Spring base com algumas dependências a partir do [Spring Initializr](https://start.spring.io/), importar ele no IntelliJ e rodar a aplicação com um "Hello, World!". Também aprendi a estrutura de um projeto Spring e um pouco da história do framework.

### Aula 2

#### Primeira requisição POST

Nessa aula vi como fazer o controller responder às requsições POST, pegando o corpo da requisição e imprimindo ele no console a partir de um objeto DTO (Data Transfer Object) intanciado a partir de um Record (recurso do Java que não havia utilizado até agora por ter focado apenas no Java 8 no começo)

### Aula 3

#### Persistência dos dados

Nessa aula vi como persistir os dados recebidos na requisição utilizando repositories, interfaces que estendem JpaRepository, que vem com uma série de métodos para fazer a integração com o banco de dados. Aprendi também a utilizar o Flyway, ferramenta de Migrations que o Spring dá suporte, e boas práticas para trabalhar com ele.
Outro tópico da aula foi fazer a validação dos dados recebidos na requisição utilizando o módulo Validation do Spring, que possui várias annotations que facilitam muito na hora de fazer essas validações.

Além do conteúdo da aula, como no curso estamos utilizando MySQL como banco de dados, resolvi aprender um pouco sobre docker e utilizar o MySQL a partir um container para manter a minha máquina com um bom desempenho.

### Aula 4

#### Requisições GET e Paginação

Nessa aula vi como fazer a API responder à requisições GET, retornando os dados aplicando uma paginação com o uso da interface Pageable e os dados em uma Page em vez de uma lista.

Também vi como mudar o jeito que a paginação irá funcionar com o uso de query strings na própria requisição e como mudar o padrão usado pelo Spring com o uso da annotation @PageableDefault.

### Aula 5

#### Requisições PUT e DELETE

Nessa aula vi como fazer a API responder à requisições PUT e DELETE, para assim fazer a atualização e exclusão de registros no banco de dados.

Também aprendi o conceito de exclusão lógica e como implementar isso utilizando o Spring, criando um novo campos "status" na tabela e mapeando-o na entidade.

Além disso, foi mostrado no curso como fazer um método personalizado de listagem aplicando um filtro na interface repository, utilizando apenas o padrão de nomenclatura para o Spring fazer a filtragem automaticamente.

### Conclusão

Estou muito feliz com o aprendizado que tive durante esse curso e com um entendimento muito maior do que o que eu tinha antes no básico do Spring, tendo em vista que já havia utilizado o framework no desenvolvimento de um projeto na empresa onde trabalho, mas não entendia realmente como as coisas que eu estava fazendo funcionavam por baixo dos panos.

Mal posso esperar para começar o próximo curso!

## Spring Boot 3: aplique boas práticas e proteja uma API Rest

Estudos de Spring Boot do curso Spring Boot 3: aplique boas práticas e proteja uma API Rest da Alura.

Neste curso irei continuar o desenvolvimento da API REST da Voll.med, dessa vez aplicando boas práticas e protejendo-a.

### Aula 1

Nessa aula vi a importância de se retornar os status codes certos nas respostas da nossa API, assim seguindo as boas práticas e deixando mais explícito ao cliente o resultado da requisição.

Para realmente aplicar as boas práticas dos status codes e corpos de respostas, aprendi o funcionamento da classe ResponseEntity, que facilita muito nesse quesito.

Também aprendi um pouco mais de como trabalhar com objetos Optional, coisa que não havia tido oportunidade de usar ainda.

### Aula 2

Nessa aula vi como tratar os erros gerados em requisições feitas de maneira não esperada com o uso de uma classe @RestControllerAdvice, utilizando métodos @ExceptionHandler e retornando ResponseEntities que fazem mais sentido e seguem as boas práticas, informando realmente o que deu errado na requisição.

Também vi como podemos mudar o padrão do Spring em lidar com Exceptions causadas nas requisições por meio do arquivo "application.properties", usando propriedades padrões do Spring que podemos pegar na [documentação](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html).
