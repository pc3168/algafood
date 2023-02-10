package com.algworks.com.algafood.jpa;

import com.algworks.com.algafood.AlgafoodApiApplication;
import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class BuscarCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = cadastroCozinha.buscar(2L);

        System.out.println(cozinha.getNome());


    }
}
