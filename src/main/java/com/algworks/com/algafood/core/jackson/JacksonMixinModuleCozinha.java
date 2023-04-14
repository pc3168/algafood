package com.algworks.com.algafood.core.jackson;

import com.algworks.com.algafood.api.model.mixin.CozinhaMixin;
import com.algworks.com.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModuleCozinha extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModuleCozinha() {
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
