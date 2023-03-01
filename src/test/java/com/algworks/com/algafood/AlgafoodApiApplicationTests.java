package com.algworks.com.algafood;

import com.algworks.com.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algworks.com.algafood.domain.exception.EntidadeEmUsoException;
import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	void testeCadastroCozinhaComSucesso(){
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();

	}

	@Test
	void testarCadastrocozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		ConstraintViolationException erroEsperado =
				Assertions.assertThrows(ConstraintViolationException.class, () -> {
					cadastroCozinha.salvar(novaCozinha);
				});

		assertThat(erroEsperado).isNotNull();
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaEmUso(){
		EntidadeEmUsoException erroEsperado =
				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
					cadastroCozinha.excluir(1L);
				});

		assertThat(erroEsperado).isNotNull();
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaInexistente(){
		CozinhaNaoEncontradoException erroEsperado =
				Assertions.assertThrows(CozinhaNaoEncontradoException.class, () -> {
					cadastroCozinha.excluir(20L);
				});

		assertThat(erroEsperado).isNotNull();
	}

}

