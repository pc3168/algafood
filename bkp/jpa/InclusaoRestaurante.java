package com.algworks.com.algafood.jpa;

import com.algworks.com.algafood.AlgafoodApiApplication;
import com.algworks.com.algafood.domain.model.Restaurante;
import com.algworks.com.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestaurante {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("DuJão");
        restaurante1.setTaxaFrete(new BigDecimal(2.50));

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Maria Benedita");
        restaurante2.setTaxaFrete(new BigDecimal(1.00));

        restaurante1 = restauranteRepository.salvar(restaurante1);
        restaurante2 = restauranteRepository.salvar(restaurante2);

        System.out.printf("%d - %s - %s\n", restaurante1.getId() , restaurante1.getNome(), restaurante1.getTaxaFrete().toString());
        System.out.printf("%d - %s - %s\n", restaurante2.getId() , restaurante2.getNome(), restaurante2.getTaxaFrete().toString());

    }
}
