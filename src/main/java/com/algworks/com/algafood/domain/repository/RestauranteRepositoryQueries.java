package com.algworks.com.algafood.domain.repository;

import com.algworks.com.algafood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> find(String nome, BigDecimal taxaFrenteInicial, BigDecimal taxaFreteFinal);
}
