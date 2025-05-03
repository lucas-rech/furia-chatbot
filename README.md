# Furia Chatbot

Furia Chatbot é um projeto desenvolvido como parte de um desafio da Furia e-Sports. Trata-se de uma aplicação web de página única (SPA) que apresenta um chatbot temático de Counter-Strike (CS:GO). O chatbot, chamado Panto, é o mascote oficial da Furia e-Sports e interage com os usuários utilizando inteligência artificial e atalhos pré-configurados.

![Desktop screenshot exemplo](/docs/screen_desktop.png)


## Tecnologias Utilizadas

- **Frontend**: React, Vite, Tailwind CSS, TypeScript
- **Backend**: Java, Spring Boot
- **Integração com IA**: Serviço de IA para geração de respostas dinâmicas
- **Armazenamento de Atalhos**: Arquivo `.csv` para respostas prontas

## Funcionalidades

- **Chatbot Interativo**: O Panto responde a perguntas relacionadas ao universo de CS:GO.
- **Integração com IA**: Respostas dinâmicas são geradas com base em um prompt injetado na API.
- **Atalhos Rápidos**: Comandos pré-configurados podem ser utilizados para respostas rápidas.
- **Interface Responsiva**: Desenvolvida com Tailwind CSS para uma experiência otimizada em diferentes dispositivos.

## Como Utilizar

### Pré-requisitos

- **Backend**:
  - Java 24
  - Maven
- **Frontend**:
  - Node.js (versão 18 ou superior)
  - Gerenciador de pacotes npm ou yarn

### Passos para Configuração

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/furia-chatbot.git
   cd furia-chatbot

2. **Configuração do Backend**:
    
    Navegue até o diretório furia-api:
    ```bash
    cd furia-api
    ```

    Configure as variáveis de ambiente no arquivo **`application.properties`**:
    ```bash
    spring.application.name=furia-api

    groq.api.url=<URL_DA_API_DE_IA>
    groq.api.key=<CHAVE_DA_API_DE_IA>
    quotes.file.path=src/main/resources/static/quotes.csv
    prompt.api.path=src/main/resources/static/prompt.txt
    
    frontend.url=http://localhost:

    springdoc.api-docs.path=/api-docs
    ```


    Inicie o servidor:
    ```bash
    ./mvnw spring-boot:run
    ```
3. **Configuração do frontend**:
   
   Navegue até o diretório **furia-frontend**:
   ```bash
   cd ../furia-frontend
   ```

   Instale as dependências
   ```bash
   npm install
   ```

   Configure a variável de ambiente no arquivo **.env**:
   ```bash
   VITE_API_URL=http://localhost:8080
   ```

   Inicie o servidor frontend de desenvolvimento:
   ```bash
   npm run dev
   ```
4. **Acesse a aplicação**:
   Abra o navegador e acesse http://localhost:5173

## Links Importantes
- API de IA Utilizada: https://console.groq.com/
- Swagger da API: http://localhost:8080/swagger-ui.html
  
## Estrutura do Projeto

### Backend
Backend (`furia-api`)

1. **Spring Boot**: 
   Gerencia a lógica do chatbot, integração com IA e leitura de atalhos do arquivo .csv.
2. **Endpoints**:
    - POST /talk: Envia uma mensagem para o chatbot.
    - GET /shortcuts: Retorna os atalhos pré-configurados.
  
### Frontend
Frontend (`furia-frontend)`

1. **React + Vite**: 
   Interface do chatbot com design responsivo.
2. **Tailwind CSS**:
   Estilização moderna e responsiva.
3. **Funcionalidades**:
   - Campo de entrada para mensagens.
   - Sugestões de atalhos ao digitar **/**.
  

## Licença
Este projeto é licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
