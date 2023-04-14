package com.algworks.com.algafood.core.jackson;

import com.algworks.com.algafood.api.model.mixin.RestauranteMixin;
import com.algworks.com.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModuleRestaurante extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModuleRestaurante() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
//        //setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
//        setMixInAnnotation(Cidade.class, CidadeMixin.class);
//        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
