# Voll.med

## Spring Boot 3: desenvolva uma API Rest em Java

Estudos de Spring Boot do curso "Spring Boot 3: desenvolva uma API Rest em Java" da Alura.

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

Estudos de Spring Boot do curso "Spring Boot 3: aplique boas práticas e proteja uma API Rest" da Alura.

Neste curso irei continuar o desenvolvimento da API REST da Voll.med, dessa vez aplicando boas práticas e protejendo-a.

### Aula 1

#### Boas práticas com ResponseEntity

Nessa aula vi a importância de se retornar os status codes certos nas respostas da nossa API, assim seguindo as boas práticas e deixando mais explícito ao cliente o resultado da requisição.

Para realmente aplicar as boas práticas dos status codes e corpos de respostas, aprendi o funcionamento da classe ResponseEntity, que facilita muito nesse quesito.

Também aprendi um pouco mais de como trabalhar com objetos Optional, coisa que não havia tido oportunidade de usar ainda.

### Aula 2

#### Tratando erros e padronizando respostas

Nessa aula vi como tratar os erros gerados em requisições feitas de maneira não esperada com o uso de uma classe @RestControllerAdvice, utilizando métodos @ExceptionHandler e retornando ResponseEntities que fazem mais sentido e seguem as boas práticas, informando realmente o que deu errado na requisição.

Também vi como podemos mudar o padrão do Spring em lidar com Exceptions causadas nas requisições por meio do arquivo "application.properties", usando propriedades padrões do Spring que podemos pegar na [documentação](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html).

### Aula 3

#### Autenticação com Spring Security

Nessa aula vi como utilizar o Spring Security para fazer a autenticação e autorização dos usuários na nossa API.

Aprendi todo o fluxo de autenticação de um usuário na API com o Spring Security, o conceito de Stateless usando JWT e como ajustar o comportamento do módulo usando uma classe de configuração que disponibiliza objetos @Bean para o Spring usar automaticamente em situações específicas, ou manualmente por meio da injeção de dependência usando a annotation @Autowired.

### Aula 4

#### Geração de tokens JWT

Nessa aula vi como utilizar a biblioteca "java-jwt" da Auth0 para gerar os tokens JWT após a autenticação do usuário e entendi um pouco mais de como os tokens JWT funcionam.

Também aprendi como utilizar variáveis de ambiente em propriedades do arquivo application.properties e como injetar propriedades que não são padrão do Spring utilizando a annotation @Value.

### Aula 5

#### Autorização utilizando os tokens

Nessa aula vi como fazer a autorização de usuários na API validando o token JWT por meio de um Filter que filtra a requisição antes mesmo de ela chegar no controller.

Também aprendi que não basta fazermos a autorização por meio do token enviado no Header da requisição, só com isso o Spring não considera o usuário autenticado e autorizado. Para o Spring realmente fazer a autenticação/autorização, precisamos especificar na nossa classe de configurações de segurança como o Spring Security deve se comportar na autenticação em cada requisição, já que ele está no modo Stateless e não mantém uma sessão do usuário logado.

Também aprendi como forçar o Spring Security a autorizar um usuário após a verificação do token JWT, tendo em vista que se não forçamos ele a fazer isso e não modificamos a ordem dos filtros, ele nunca irá autorizar nenhum usuário, mesmo ele tendo enviado um token válido no Header da requisição.

Foi uma aula com muito conteúdo e nem tudo está fixado na minha cabeça ainda, por isso estou praticando bastante e vou voltar nessa aula posteriormente, pois sei que é um assunto de extrema importância e um tanto complexo quando ainda não estamos acostumados com como funciona.

### Conclusão

Nesse curso aprendi muita coisa interessante e importante, desde como ter respostas personalizadas na API tratando as Exceptions causadas pelas requisições, até como fazer uma autenticação e autorização completa de usuários na API seguindo o modelo Stateless, entre muitos outros detalhes de como tudo funciona.

Sem dúvida foi o curso mais complexo de Spring que finalizei até agora e me sinto muito mais preparado para continuar estudando e melhorando cada vez mais.

## Spring Boot 3: documente, teste e prepare uma API para o deploy

Estudos de Spring Boot do curso "Spring Boot 3: documente, teste e prepare uma API para o deploy" da Alura.

Neste curso irei continuar o desenvolvimento da API REST da Voll.med, dessa vez adicionando novas funcionalidades de agendamento e cancelamento de consultas com validações mais complexas. Também irei aprender a como gerar uma documentação para a API, assim como prepará-la para o deploy.

### Aula 1

#### Começando com novas funcionalidades

Nesta aula vi a importância de isolar regras de negócio e lógicas em classes @Service, assim fazendo com que os controllers tenham apenas a responsabilidade de tratar o fluxo das requisições, chamando a lógica de outro lugar.

Também aprendi como fazer validações de integridade das informações recebidas nas requisições, ou seja, além de fazer as verificações básicas que já havia feito com o Spring Validation, validar se existe uma entidade com o id passado no atributo da requisição, por exemplo.

Além disso, aprendi como criar queries personalizadas no nosso banco de dados para aplicarmos lógicas de filtragem mais complexas e que não seriam possíveis de fazer apenas seguindo o padrão de nomenclatura dos métodos do respository.

### Aula 2

#### Fazendo validações e aplicando SOLID

Nesta aula vi como aplicar validações de regras de negócio seguindo alguns dos princípios SOLID, assim tornando o processo de manutenção dessas validações, ou até mesmo o processo de acrescentar uma nova validação, mais fácil.

Também aprendi como utilizar interfaces nas classes de validação das regras de negócio para conseguir usar o polimorfismo, dessa forma não tendo que instanciar uma por uma no nosso @Service, apenas tendo que injetar uma List com o tipo da nossa interface utilizando o @Autowired, assim podendo utilizar o método "forEach();" para iterar por cada classe de validação executando a validação. Isso torna o processo de adicionar/remover validações muito mais simples.

### Aula 3

#### Documentando a API

Nessa aula vi como utilizar o SpringDoc para gerar a documentação de APIs feitas com Spring, uma ferramenta muito poderosa e simples de usar que automatiza o processo de documentação de APIs gerando um JSON que pode ser lido por outra ferramenta, ou até mesmo utilizado para automatização do processo de criação de aplicações clientes que integram com a nossa API, além de gerar uma interface gráfica no navegador com base no Swagger UI que podemos utilizar como ferramenta de teste da nossa API, assim como um Insomnia ou Postman.

Também vi como aplicar configurações diferenciadas na geração dessa interface gráfica utilizando uma classe @Configuration que retorna um @Bean do tipo OpenAPI, onde podemos aplicar diversas configurações.

Além disso, aprendi um pouco da história do Swagger e do OpenAPI e a importância de padronizarmos a documentação das nossas APIs.

### Aula 4

#### Testando repositories e controllers

Nesta aula vi como utilizar o módulo de testes padrão do Srping (o que já vem como dependência quando iniciamos o projeto a partir do Spring Initializr) para aplicar testes de unidade e de integração nas classes repository e controller do projeto. No curso não foi mostrado como fazer os testes nas validações de regras de negócio, pois fugia do escopo de testes no Spring, onde esses testes não necessitariam de nenhum contexto do Spring em si para serem rodados.

Aprendi também o fluxo given/when/then, algo que não tinha visto antes e que ajuda bastante a entender como funciona o fluxo de um teste.

Vi como configurar os testes nos repositories de modo que os testes sejam feitos em um banco de dados exclusivo para testes criando novos profiles com outros arquivos application.properties, como utilizar mocks para fazer testes em controllers onde a requisição é simulada, como utilizar o JacksonTester para transformar DTOs em strings JSON e muitos outros detalhes sobre como essas coisas funcionam.

Foi uma aula muito completa e com muito conteúdo novo. Sem dúvida irei rever as aulas algumas vezes para fixar o conhecimento, pois são muitas classes e muitos detalhes para conseguir realmente ter tudo funcionando do jeito que queremos.

### Aula 5

#### Buildando o projeto

Nesta aula vi como buildar o projeto em um arquivo .jar utilizando o Maven pelo próprio IntelliJ, criando um novo profile para produção, onde as propriedades de conexão e autenticação ao banco de dados são lidas de variáveis de ambiente, assim tornando a aplicação menos vulnerável e modificável, caso seja necessário mudar essas propriedades, sem ter necessidade de buildar o projeto novamente.

Também aprendi como escolher o profile a ser utilizado ao rodar o .jar, assim como passar as variáveis de ambiente definidas no profile por meio de parâmetros no comando de subir a aplicação.

Além disso, vi que gerar um .jar não é a única escolha disponível para buildar a nossa aplicação, podemos também buildar ela para um arquivo .war ou uma Native Image com o GraalVM, o que faz com que a nossa aplicação se torne um binário executável, sem precisar da JVM para rodar e com uma performance muito mais alta.

### Conclusão

Neste curso aprendi coisas muito importantes no desenvolvimento de APIs REST com o Spring Boot, sendo elas:

- Como fazer queries personalizadas no banco de dados;
- Como aplicar princípios SOLID ao criar validações de regras de negócio, assim tornando a manutenção e adição de novas validações muito mais fácil;
- Como gerar uma documentação seguindo a especificação OpenAPI utilizando o SpringDoc;
- Como fazer testes automatizados nos repositories e controllers da API utilizando JUnit, AssertJ e Mockito;
- Como fazer o build final da aplicação e as alternativas que temos.

Com a finalização deste curso, também finalizei a formação Java e Spring Boot, onde aprendi muita coisa e pude desenvolver um projeto muito interessante aplicando os conhecimentos obtidos durante as aulas.

Tenho noção de que ainda tenho muita coisa para aprender no Spring para realmente dominar o framework e seus módulos, mas após ter finalizado essa formação me sinto muito mais confortável para desenvolver novos projetos e aprender as coisas que ainda não sei pesquisando e aplicando-as.

Estou muito feliz com toda essa jornada de estudos que tive até agora e muito animado com o que vem pela frente!
