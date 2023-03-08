package com.algworks.com.algafood;

import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.model.Restaurante;
import com.algworks.com.algafood.domain.repository.CozinhaRepository;
import com.algworks.com.algafood.domain.repository.RestauranteRepository;
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

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private String salvarRestaurante = ResourceUtils.getContentFromResource(
            "/json/restaurante_salvar.json");

    private String semFrete = ResourceUtils.getContentFromResource(
            "/json/restaurante_semFrete.json");

    private String semCozinha = ResourceUtils.getContentFromResource(
            "/json/restaurante_semCozinha.json");

    private String cozinhaInexistente = ResourceUtils.getContentFromResource(
            "/json/restaurante_cozinhaInexistente.json");


    private static final String  DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";

    private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";



    @BeforeEach
    void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    void deveRetornarStatus200_QuandoConsultaRestaurante(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveRetornarStatus204_QuandoExcluirRestaurante(){
        RestAssured.given()
                .pathParam("id", 1)
                .accept(ContentType.JSON)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void deveRetornarStatus201_QuandoCriarUmRestaurante(){
        RestAssured.given()
                .body(salvarRestaurante)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }


    @Test
    void deveRetornarStatus400_QuandoRestauranteSemFrete(){
        RestAssured.given()
                .body(semFrete)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    void deveRetornarStatus400_QuandoRestauranteSemCozinha(){
        RestAssured.given()
                .body(semCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    void deveRetornarStatus400_QuandoCozinhaForInexistente(){
        RestAssured.given()
                .body(cozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title" , Matchers.equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
    }




    private void prepararDados(){
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinha1 = cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Mexicana");
        cozinha2 = cozinhaRepository.save(cozinha2);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Java Steakhouse");
        restaurante1.setTaxaFrete(new BigDecimal(9.5));
        restaurante1.setCozinha(cozinha1);
        restauranteRepository.save(restaurante1);

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Thai Delivery");
        restaurante2.setTaxaFrete(new BigDecimal(15));
        restaurante2.setCozinha(cozinha2);
        restauranteRepository.save(restaurante2);

        Restaurante restaurante3 = new Restaurante();
        restaurante3.setNome("Bar da Maria");
        restaurante3.setTaxaFrete(new BigDecimal(6.5));
        restaurante3.setCozinha(cozinha2);
        restauranteRepository.save(restaurante3);
    }


}
