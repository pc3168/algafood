package com.algworks.com.algafood.api.model.mixin;

import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.model.Endereco;
import com.algworks.com.algafood.domain.model.FormaPagamento;
import com.algworks.com.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

public abstract class RestauranteMixin {

    /*
        Estou ignorando a propriedade cozinha.nome quando for passado para atualizar sendo
        que o mesmo não pode ser atualizado mas na busca o mesmo tem que aparecer
     */
    @JsonIgnoreProperties(value = "nome", allowGetters = true )
    //@JsonIgnoreProperties("hibernateLazyInitializer")
    //@JsonIgnoreProperties({"hibernateLazyInitializer","algumacoisa"})
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento ;


    @JsonIgnore
    private List<Produto> produtos;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;
}
