package com.algworks.com.algafood.domain.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaException extends NegocioException{

    private static final long serialVersionUID = 1L;
    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}
