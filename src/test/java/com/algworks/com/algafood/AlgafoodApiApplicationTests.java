package com.algworks.com.algafood;

import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.repository.CozinhaRepository;
import com.algworks.com.algafood.util.DatabaseCleaner;
import com.algworks.com.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;


/*
	Acrescentar a informação abaixo para subir o teste com o servidor web.
	(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;
	@Autowired
	private CozinhaRepository cozinhaRepository;

	private int totalRegistros;
	private Cozinha testeCozinha;
	private final int COZINHA_INEXISTENTE = 100;
	private String conteudoCozinha = ResourceUtils.getContentFromResource(
			"/json/cozinhaSalvar.json");


	@BeforeEach
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		databaseCleaner.clearTables();
		prepararDados();
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
	void deveConterTotalCozinhas_QuandoConsultaCozinha() {

		RestAssured.given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", Matchers.hasSize(totalRegistros));
	}

	@Test
	void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
				//.body("{\"nome\": \"Chinesa\" }")
				.body(conteudoCozinha)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {

		RestAssured.given()
				.pathParam("cozinhaId", testeCozinha.getId())
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(testeCozinha.getNome()));
	}

	@Test
	void deveRetornarStatus404_quandoConsultarCozinhaInexistente(){
		RestAssured.given()
				.pathParam("cozinhaId", COZINHA_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados(){
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);


		testeCozinha = cozinha2;
		totalRegistros = (int) cozinhaRepository.count();

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

