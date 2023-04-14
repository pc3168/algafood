package com.algworks.com.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class UsuarioMixin {

    @JsonIgnore
    private LocalDateTime dataCadasatro;
}
