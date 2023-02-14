package com.algworks.com.algafood.api.exceptionhandler;

public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    PARAMENTRO_INVALIDO("/prametro-invalido","Parâmetro é inválido"),
    ERRO_DE_SISTEMA("/erro_de_sistema", "Erro de Sistema"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "http://algafood.com.br"+path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
