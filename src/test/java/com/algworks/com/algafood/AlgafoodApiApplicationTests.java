package com.algworks.com.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


/*
	Acrescentar a informação abaixo para subir o teste com o servidor web.
	(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;

	@BeforeEach
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		flyway.migrate();
	}


	@Test
	void deveRetornarStatus200_QuandoConsultaCozinha() {

		RestAssured.given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.statusCode(HttpStatus.OK.value());
	}

	@Test
	void deveConter4Cozinhas_QuandoConsultaCozinha() {

		RestAssured.given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", Matchers.hasSize(4))
					.body("nome",Matchers.hasItems("Indiana", "Tailandesa"));
	}

	@Test
	void testRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
				.body("{\"nome\": \"Chinesa\" }")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}


//	@Autowired
//	private CadastroCozinhaService cadastroCozinha;
//
//	@Test
//	void testeCadastroCozinhaComSucesso(){
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Chinesa");
//
//		novaCozinha = cadastroCozinha.salvar(novaCozinha);
//
//		assertThat(novaCozinha).isNotNull();
//		assertThat(novaCozinha.getId()).isNotNull();
//
//	}
//
//	@Test
//	void testarCadastrocozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//
//		ConstraintViolationException erroEsperado =
//				Assertions.assertThrows(ConstraintViolationException.class, () -> {
//					cadastroCozinha.salvar(novaCozinha);
//				});
//
//		assertThat(erroEsperado).isNotNull();
//	}
//
//	@Test
//	void deveFalhar_QuandoExcluirCozinhaEmUso(){
//		EntidadeEmUsoException erroEsperado =
//				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
//					cadastroCozinha.excluir(1L);
//				});
//
//		assertThat(erroEsperado).isNotNull();
//	}
//
//	@Test
//	void deveFalhar_QuandoExcluirCozinhaInexistente(){
//		CozinhaNaoEncontradoException erroEsperado =
//				Assertions.assertThrows(CozinhaNaoEncontradoException.class, () -> {
//					cadastroCozinha.excluir(20L);
//				});
//
//		assertThat(erroEsperado).isNotNull();
//	}

}

