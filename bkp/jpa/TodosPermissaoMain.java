package com.algworks.com.algafood.jpa;

import com.algworks.com.algafood.AlgafoodApiApplication;
import com.algworks.com.algafood.domain.model.Permissao;
import com.algworks.com.algafood.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class TodosPermissaoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

        //Adicionar
        Permissao permissao1 = new Permissao();
        permissao1.setNome("permissao1");
        permissao1.setDescricao("nomePermissao1");

        Permissao permissao2 = new Permissao();
        permissao2.setNome("permissao2");
        permissao2.setDescricao("nomePermissao2");

        Permissao permissao3 = new Permissao();
        permissao3.setNome("permissao3");
        permissao3.setDescricao("nomePermissao3");

        permissaoRepository.salvar(permissao1);
        permissaoRepository.salvar(permissao2);
        permissaoRepository.salvar(permissao3);

        //Listar
        System.out.println("Listar");
        java.util.List<Permissao> permissaosList = permissaoRepository.todos();
        for(Permissao permissao : permissaosList){
            System.out.println("Lista de permissão "+ permissao);
        }

        //Buscar por ID
        System.out.println("Buscar Por Id");
        Permissao permissaoBuscar = permissaoRepository.buscar(2L);
        System.out.println("Buscar por "+permissaoBuscar);

        //Atualizar
        System.out.println("Atualizar");
        permissaoBuscar.setNome("Alterando o nome da permissão");
        permissaoBuscar.setDescricao("Alterando a descrição da permissão");
        permissaoRepository.salvar(permissaoBuscar);


        //Apagar
        System.out.println("Apagar");
        permissaoRepository.remover(1L);


        //Listar
        System.out.println("Listar");
        permissaosList = permissaoRepository.todos();
        for(Permissao permissao : permissaosList){
            System.out.println("Lista de permissão "+ permissao);
        }



    }
}
