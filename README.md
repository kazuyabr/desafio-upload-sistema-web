# desafio-upload-sistema-web

O projeto é separado em 2 Frontend e Backend

## Backend
Primeiro vamos rodar o backend para termos o sistema que serve nossa aplicação
No backend utilizamos Springboot 2.7.7-SNAPSHOT.
Para rodar a aplicação basta seguir os passos:
-Na pasta do arquivo rode ```mvn clean install```
-Depois disto basta buscar pelo terminal a pasta target de seu projeto e rodar ```java -jar desafio-0.0.1-SNAPSHOT.jar```

Depois disto a aplicação ja estara pronta para servir nosso front e os arquivos subidos estarão armazenados na pasta temporaria dentro da pasta ```contato-disco\arquivos```


## Frontend

O frontend foi desenvolvido em Angular CLI 15.0.4 com Angular Material 15 com o propósito de ser uma pagina Single Application e permitir uploads de multiplos arquivos (indepentente de sua extensão)
Esta aplicação só possui dois componentes que são a HOME e UPLOAD. (Home é só para ter uma index menos poluida).

Para rodar essa aplicação primeiramente você deve ter rodado o backend e depois siga os passos:

- Instale o CLI do Angular de modo global com o comando ```npm install -g @angular/cli```
- atualize o projeto rodando ```npm install```
- rode o projeto ```npm start```
 
Aqui ele ja estara rodando a aplicação e você podera acessa-la pelo link ```http://localhost:4200/```