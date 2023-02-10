package com.algworks.com.algafood.jpa;

import com.algworks.com.algafood.AlgafoodApiApplication;
import com.algworks.com.algafood.domain.model.Cidade;
import com.algworks.com.algafood.domain.model.Estado;
import com.algworks.com.algafood.domain.repository.CidadeRepository;
import com.algworks.com.algafood.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class TodosCidadeMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CidadeRepository CidadeRepository = applicationContext.getBean(CidadeRepository.class);
        EstadoRepository EstadoRepository = applicationContext.getBean(EstadoRepository.class);

        //Adicionar estado
        System.out.println("Adicionar Estados" );
        Estado estado1 = new Estado();
        estado1.setNome("MG");
        estado1 = EstadoRepository.salvar(estado1);

        Estado estado2 = new Estado();
        estado2.setNome("BA");
        estado2 = EstadoRepository.salvar(estado2);

        Estado estado3 = new Estado();
        estado3.setNome("PE");
        estado3 = EstadoRepository.salvar(estado3);

        //Adicionar
        System.out.println("Adicionar");
        Cidade cidade1 = new Cidade();
        cidade1.setNome("Paraguaçu");
        cidade1.setEstado(estado1);

        CidadeRepository.salvar(cidade1);

        Cidade cidade2 = new Cidade();
        cidade2.setNome("Varginha");
        cidade2.setEstado(estado2);
        CidadeRepository.salvar(cidade2);

        Cidade cidade3 = new Cidade();
        cidade3.setNome("Alfenas");
        cidade3.setEstado(estado3);
        CidadeRepository.salvar(cidade3);


        //Listar
        List<Cidade> cidadeList = CidadeRepository.todos();
        for(Cidade cidade : cidadeList){
            System.out.println(cidade);
        }

        //Busca por um Id
        Cidade cidadeBusca = CidadeRepository.buscar(2L);
        System.out.printf("Busca por Id %s\n", cidadeBusca.getNome());

        //Atualizar
        cidadeBusca.setNome("Pouso Alegre");
        CidadeRepository.salvar(cidadeBusca);


        //Apagar
        CidadeRepository.remover(1L);

        //Listar
        cidadeList = CidadeRepository.todos();
        for(Cidade cidade : cidadeList){
            System.out.println(cidade);
        }

    }

}
