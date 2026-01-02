## Desafio: Explorando Padrões de Projeto na Prática com Spring Boot
Este repositório foi desenvolvido para o desafio de projeto do curso de Padrões de Projeto. A solução explora a integração entre o framework Spring e os conceitos de design patterns para criar uma aplicação robusta e escalável.

### 🎯 Objetivo do Projeto
- Demonstrar a aplicação prática de padrões de projeto em um ecossistema Java moderno, focando em:

- Abstração de complexidade através de serviços.

- Manutenibilidade do código.

- Integração eficiente com APIs externas.

### 🏗️ Padrões de Projeto Aplicados
- A aplicação foca em três tipos principais de padrões:

- Singleton: Utilizado nativamente pelo Spring Framework para gerenciar os Beans da aplicação (Controllers, Services e Repositories).

- Strategy: Aplicado para definir diferentes comportamentos de negócio, permitindo que a lógica mude dinamicamente conforme a necessidade (ex: diferentes formas de persistência ou integração).

- Facade: Implementado para prover uma interface simplificada de consumo da API externa ViaCEP, mascarando a complexidade da integração com o Spring Cloud OpenFeign.

### 🚀 Stack Tecnológica
 - Linguagem: Java 21 (LTS)

 - Framework: Spring Boot 3.4.1

 - Gerenciamento de Dependências: Maven

 - Cloud: Spring Cloud OpenFeign

 - Banco de Dados: H2 (Banco de dados em memória para testes)

- Documentação: SpringDoc OpenAPI (Swagger)

- Produtividade: Lombok

### 🔧 Como Executar a Solução
Certifique-se de ter o Java 21 e Maven instalados.

Clone este repositório:

```git clone https://github.com/alexpaulo100/spring-design-patterns-lab.git ```


Compile e execute os testes:

```mvn clean install```
Inicie a aplicação:

```mvn test ```

Explore a API: Acesse o Swagger UI em: http://localhost:8080/swagger-ui/index.html





