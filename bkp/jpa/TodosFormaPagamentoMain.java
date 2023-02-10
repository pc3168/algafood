package com.algworks.com.algafood.jpa;

import com.algworks.com.algafood.AlgafoodApiApplication;
import com.algworks.com.algafood.domain.model.FormaPagamento;
import com.algworks.com.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class TodosFormaPagamentoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

        //Adicionar
        FormaPagamento formaPagamento1 = new FormaPagamento();
        formaPagamento1.setDescricao("Cartão");
        formaPagamentoRepository.salvar(formaPagamento1);

        FormaPagamento formaPagamento2 = new FormaPagamento();
        formaPagamento2.setDescricao("Dinheiro");
        formaPagamentoRepository.salvar(formaPagamento2);

        FormaPagamento formaPagamento3 = new FormaPagamento();
        formaPagamento3.setDescricao("Cheque");
        formaPagamentoRepository.salvar(formaPagamento3);


        //Listar
        List<FormaPagamento> formaPagamentoList = formaPagamentoRepository.todos();
        for(FormaPagamento formaPagamento : formaPagamentoList){
            System.out.println(formaPagamento);
        }

        //Busca por um Id
        FormaPagamento formaPagamentoBusca = formaPagamentoRepository.buscar(2L);
        System.out.printf("Busca por Id %s\n", formaPagamentoBusca.getDescricao());

        //Atualizar
        formaPagamentoBusca.setDescricao("Pix");
        formaPagamentoRepository.salvar(formaPagamentoBusca);


        //Apagar
        formaPagamentoRepository.remover(1L);

        //Listar
        formaPagamentoList = formaPagamentoRepository.todos();
        for(FormaPagamento formaPagamento : formaPagamentoList){
            System.out.println(formaPagamento);
        }

    }

}
