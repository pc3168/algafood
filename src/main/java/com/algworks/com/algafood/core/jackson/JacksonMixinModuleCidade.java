package com.algworks.com.algafood.core.jackson;

import com.algworks.com.algafood.api.model.mixin.CidadeMixin;
import com.algworks.com.algafood.domain.model.Cidade;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModuleCidade extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModuleCidade() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
