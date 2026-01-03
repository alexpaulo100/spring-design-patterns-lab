## Lab Spring - Design Patterns, Testing & CI/CD
Este repositório foi desenvolvido para o desafio de projeto do curso de Padrões de Projeto. A solução explora a integração entre o framework Spring e os conceitos de design patterns para criar uma aplicação robusta e escalável.

[![Java CI with Maven](https://github.com/alexpaulo100/spring-design-patterns-lab/actions/workflows/maven.yml/badge.svg)](https://github.com/alexpaulo100/spring-design-patterns-lab/actions/workflows/maven.yml)
### 🎯 Objetivo do Projeto
- Demonstrar a aplicação prática de padrões de projeto em um ecossistema Java moderno, focando em:

- Abstração de complexidade através de serviços.

- Manutenibilidade do código.

- Integração eficiente com APIs externas.

### 🏗️ Padrões de Projeto Aplicados
- A aplicação foca em três tipos principais de padrões:

- Singleton: Singleton: Utilizado nativamente pelo Spring para gerenciar os Beans da aplicação (Controllers, Services e Repositories).

- Strategy: Aplicado para definir diferentes comportamentos de negócio, permitindo que a lógica de busca de CEP e persistência mude dinamicamente.

- Facade: Implementado para prover uma interface simplificada de consumo da API externa ViaCEP, mascarando a complexidade da integração com o Spring Cloud OpenFeign.

### 🛡️ Qualidade de Software & Resiliência
- Para tornar o projeto "Production Ready", foram implementados:

- Testes Unitários: Suíte de testes utilizando JUnit 5 e Mockito, cobrindo cenários de sucesso e casos de borda (edge cases), como CEPs inexistentes e exclusão de registros.

- Tratamento de Exceções Global: Implementação de um GlobalExceptionHandler com exceções customizadas (ResourceNotFoundException). Isso garante que a API retorne respostas JSON padronizadas e códigos HTTP semanticamente corretos (400, 404, etc.).

- CI/CD Automatizado: Pipeline configurado via GitHub Actions que executa automaticamente o build e todos os testes a cada push ou pull request, garantindo a integridade do código.


### 🚀 Stack Tecnológica
 - Linguagem: Java 21 (LTS)

 - Framework: Spring Boot 3.4.1

 - Gerenciamento de Dependências: Maven

 - Cloud: Spring Cloud OpenFeign

 - Banco de Dados: H2 (Banco de dados em memória para testes)
 - Teste: JUnit 5, Mockito e Maven Surefire

- Documentação: SpringDoc OpenAPI (Swagger)

- Produtividade: Lombok
- Automação: GitHub Actions

### 🔧 Como Executar a Solução
- Certifique-se de ter o Java 21 e Maven instalados.

- Clone este repositório:

```git clone https://github.com/alexpaulo100/spring-design-patterns-lab.git ```


- Compile e execute os testes:

```
mvn clean install
```
- Execute os testes unitários(Validando a lógica e CI/CD local.)

```
mvn test 
```
- Inicie a aplicação:

``` 
mvn spring-boot:run
```


Explore a API: Acesse o Swagger UI em: http://localhost:8080/swagger-ui/index.html



![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)