# Abstract

Elaboración del taller practico de pruebas de integración. 


## Sesiones 

* Practica 1: Elaboración de casos de prueba de integracion utilizando WebTestClient
* Practica 2: Elaboración de casos de prueba de integracion utilizando RestTemplateClient


### Practica 1 - Utilizando Web Test Client

1. Adicionar la dependencia spring-boot-starter-webflux al archivo de configuración. 

        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
			<scope>test</scope>
		</dependency>

2. Seleccionamos la clase controller a la cual vamos a implementar el caso de prueba y le creamos la clase Test.
3. Elaboramos los casos de prueba.
4. Adicionamos la anotacion @SpringBootTest
5. Dentro de la anotacion adicionamos el webEnviroment de tipo RANDOM

   @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

6. Adicionamos la etiqueta de ordenamiento

	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

7. Creamos un atributo de tipo WebTestClient y le agregamos la anotacion Autowired

	@Autowired
    private WebTestClient client ;

8. Creamos un objeto ObjectMapper que nos permitira realizar conversion entre clases

	ObjectMapper mapper = new ObjectMapper();

9. Creamos un objeto de tipo int el cual contiene la anotación @LocalServerPort

   @LocalServerPort
   int port;	

10. Creamos los objetos requeridos para la prueba

11. Creamos el metodo beforeEach. Instanciamos el objeto WebTestClient y las entradas a los casos de prueba

12. Codificamos la prueba.

13. Ejecutamos la prueba. 


### Practica 2 - Utilizando Rest Test Template

1. Seleccionamos la clase controller a la cual vamos a implementar el caso de prueba y le creamos la clase Test.
2. Elaboramos los casos de prueba.
3. Adicionamos la anotacion @SpringBootTest
4. Dentro de la anotacion adicionamos el webEnviroment de tipo RANDOM

   @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

5. Adicionamos la etiqueta de ordenamiento

   @TestMethodOrder(MethodOrderer.OrderAnnotation.class)

6. Creamos un atributo de tipo TestRestTemplate y le agregamos la anotacion Autowired

   @Autowired
   private TestRestTemplate client ;

8. Creamos un objeto ObjectMapper que nos permitira realizar conversion entre clases

   ObjectMapper mapper = new ObjectMapper();

9. Creamos un objeto de tipo int el cual contiene la anotación @LocalServerPort

   @LocalServerPort
   int port;

10. Creamos los objetos requeridos para la prueba
11. Creamos el metodo beforeEach. Instanciamos el objeto TestRestTemplate y las entradas a los casos de prueba
12. Creamos un metodo para retornar la URL del servicio a direccionar. 
13. Codificamos la prueba. 
14. Ejecutamos la prueba.