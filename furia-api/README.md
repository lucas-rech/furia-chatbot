# Furia Chatbot API

A **Furia Chatbot API** é o backend da aplicação Furia Chatbot, desenvolvida em **Java** utilizando o framework **Spring Boot**. Essa API gerencia a lógica do chatbot, integra-se com um serviço de inteligência artificial (IA) para respostas dinâmicas e fornece atalhos pré-configurados armazenados em um arquivo `.csv`.

## Funcionalidades

### 1. **Integração com IA**
A API utiliza um serviço de IA para gerar respostas dinâmicas com base em um **prompt** configurado. Caso a pergunta do usuário não encontre correspondência nos atalhos pré-configurados, a API faz uma chamada ao serviço de IA para gerar uma resposta.

### 2. **Atalhos Pré-configurados**
A API lê um arquivo `.csv` contendo perguntas e respostas prontas, além de comandos rápidos que podem ser utilizados no chatbot. Esses atalhos são retornados ao frontend para facilitar a interação do usuário.

### 3. **Endpoints**
A API expõe os seguintes endpoints:
- **POST `/talk`**: Recebe uma mensagem do usuário e retorna uma resposta, seja de um atalho pré-configurado ou gerada pela IA.
- **GET `/shortcuts`**: Retorna todos os atalhos pré-configurados disponíveis no arquivo `.csv`.

### 4. **Documentação com Swagger**
A API possui uma interface de documentação interativa gerada pelo **Springdoc OpenAPI**, disponível em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

### 5. **Tratamento de Erros**
A API possui um controlador global para lidar com exceções, garantindo respostas apropriadas para diferentes cenários, como:
- **400 BAD REQUEST**: Entrada inválida ou vazia.
- **408 REQUEST TIMEOUT**: Tempo excedido ao processar uma pergunta.
- **503 SERVICE UNAVAILABLE**: Erro ao ler arquivos de configuração.

---

## Estrutura do Projeto

### 1. **Camada de Controladores**
Os controladores são responsáveis por expor os endpoints da API:
- [`BotController`](furia-api/src/main/java/com/lucasrech/furiaapi/controller/BotController.java): Gerencia as requisições de mensagens e atalhos.

### 2. **Camada de Serviços**
Os serviços contêm a lógica de negócio:
- [`BotService`](furia-api/src/main/java/com/lucasrech/furiaapi/services/BotService.java): Processa mensagens, busca respostas nos atalhos ou delega ao serviço de IA.
- [`GPAPIService`](furia-api/src/main/java/com/lucasrech/furiaapi/services/GPAPIService.java): Integra-se com o serviço de IA para gerar respostas dinâmicas.

### 3. **Camada de Utilitários**
- [`ReadFiles`](furia-api/src/main/java/com/lucasrech/furiaapi/util/ReadFiles.java): Lê arquivos `.csv` e o prompt de configuração.
- [`QuestionMatcher`](furia-api/src/main/java/com/lucasrech/furiaapi/util/QuestionMatcher.java): Implementa lógica de correspondência de perguntas utilizando o algoritmo de Levenshtein.

### 4. **Camada de Exceções**
Define exceções personalizadas e um controlador global para tratá-las:
- [`GlobalExceptionController`](furia-api/src/main/java/com/lucasrech/furiaapi/exceptions/GlobalExceptionController.java): Lida com exceções e retorna respostas apropriadas.
- Exceções personalizadas:
  - [`EmptyInputException`](furia-api/src/main/java/com/lucasrech/furiaapi/exceptions/EmptyInputException.java)
  - [`FileNotReadedException`](furia-api/src/main/java/com/lucasrech/furiaapi/exceptions/FileNotReadedException.java)
  - [`TimeOutProcessingQuestion`](furia-api/src/main/java/com/lucasrech/furiaapi/exceptions/TimeOutProcessingQuestion.java)

### 5. **Configuração**
- [`application.properties`](furia-api/src/main/resources/application.properties): Configurações da API, como URLs, chaves de API e caminhos de arquivos.
- [`WebConfig`](furia-api/src/main/java/com/lucasrech/furiaapi/config/WebConfig.java): Configura o CORS para permitir requisições do frontend.

---

## Configuração

### Pré-requisitos
- **Java 24**
- **Maven**

### Passos para Configuração
1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/furia-chatbot.git
   cd furia-chatbot/furia-api

2. Configure as variáveis de ambiente: Edite o arquivo `application.properties`:

    ```bash
    groq.api.url=<URL_DA_API_DE_IA>
    groq.api.key=<CHAVE_DA_API_DE_IA>
    quotes.file.path=src/main/resources/static/quotes.csv
    prompt.api.path=src/main/resources/static/prompt.txt
    
    frontend.url=http://localhost:5173
    ```

3. Inicie o servidor: 
   ```bash
   ./mvnw spring-boot:run
   ```

## Estrutura de Arquivos Importantes
### Atalhos e Respostas:
Atualmente funciona como um `.csv` mas evoluirá para um banco de dados. 
- **`quotes.csv:`** Contém perguntas, respostas e atalhos.
  A estrutura das coluans do arquivo é seguida por `perguntas, respostas, atalhos`:
  ![Screenshot de tabela quotes.csv](/docs/quotes.png)

### **Prompt de IA:** 
- **`prompt.txt:`** Define o comportamento do chatbot ao interagir com o serviço de IA.

## Links Importantes
- **API de IA utilizada**: https://console.groq.com
- **Swagger da API**: http://localhost:8080/swagger-ui.html
