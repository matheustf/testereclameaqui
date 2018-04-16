###### Build
[![Build Status](https://travis-ci.org/matheustf/testereclameaqui.svg?branch=master)](https://travis-ci.org/matheustf/testereclameaqui)

###### Qualidade de Código
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/56390a4a4cab48b2865639e2dcd2d3d2)](https://www.codacy.com/app/matheustf/testereclameaqui?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=matheustf/testereclameaqui&amp;utm_campaign=Badge_Grade)

###### Cobertura de Testes
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/56390a4a4cab48b2865639e2dcd2d3d2)](https://www.codacy.com/app/matheustf/testereclameaqui?utm_source=github.com&utm_medium=referral&utm_content=matheustf/testereclameaqui&utm_campaign=Badge_Coverage)

### Projeto ReclameAqui

#  Ferramentas
###### Codigicação:
	Java 8
	Spring Boot 1.5.10

###### Build:
	Maven 3.3.9

###### Persistencia:
	MongoDB

###### Servidor Web:
	Container Spring(Tomcat)

###### Testes Unitários:
	Junit
	Mockito

###### Testes Integrados:	
	Spring Test
	MockMvc
###### Plugin:
	Cobertura

### Devops:
	Travis CI
	Heroku
	Cobertura Plugin
	Codacy
	mLab (MongoDB)
	Visibilidade de status no github

###### Facilidades para teste:
	Disponibilização de suite de Testes
	Disponibilização de colections Postman
	Disponibilização de interface Swagger

# Sequencia de Devops

1. Após cada commit no github o código é enviado ao TravisCI 

2. Travis compila a aplicação e executa todos os testes 
 2.1.Travis envia email caso a build quebre

3. Travis compila a cobertura e gera o xml de cobertura de testes

4. Travis informa status de build ao github

5. Se OK, o código é enviado para o Codacy e para o Heroku

6. Codacy atualiza dashboard com validações de qualidade de código, pontos criticos para possíveis erros e percentual de cobertura de testes

7. Codacy atualiza a pagina do github com informações de cobertura de testes e qualidade de código

9. Heroku atualiza a aplicação e disponibiliza na URL 
	8.1. Api
	8.2. Swagger
	8.3. mLab(Mongodb) é carregado com dados para facilitar o teste manual da api
