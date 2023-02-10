package com.algworks.com.algafood.jpa;

import com.algworks.com.algafood.AlgafoodApiApplication;
import com.algworks.com.algafood.domain.model.Estado;
import com.algworks.com.algafood.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class TodosEstadoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        EstadoRepository EstadoRepository = applicationContext.getBean(EstadoRepository.class);

        //Adicionar
        System.out.println("Adicionar");
        Estado Estado1 = new Estado();
        Estado1.setNome("MG");
        EstadoRepository.salvar(Estado1);

        Estado Estado2 = new Estado();
        Estado2.setNome("BA");
        EstadoRepository.salvar(Estado2);

        Estado Estado3 = new Estado();
        Estado3.setNome("PE");
        EstadoRepository.salvar(Estado3);


        //Listar
        List<Estado> EstadoList = EstadoRepository.todos();
        for(Estado Estado : EstadoList){
            System.out.println(Estado);
        }

        //Busca por um Id
        Estado EstadoBusca = EstadoRepository.buscar(2L);
        System.out.printf("Busca por Id %s\n", EstadoBusca.getNome());

        //Atualizar
        EstadoBusca.setNome("SP");
        EstadoRepository.salvar(EstadoBusca);


        //Apagar
        EstadoRepository.remover(1L);

        //Listar
        EstadoList = EstadoRepository.todos();
        for(Estado Estado : EstadoList){
            System.out.println(Estado);
        }

    }

}
