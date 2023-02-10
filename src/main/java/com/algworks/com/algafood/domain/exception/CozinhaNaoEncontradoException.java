package com.algworks.com.algafood.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;
    public CozinhaNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public CozinhaNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de cozinha com código %d",id));
    }

}
