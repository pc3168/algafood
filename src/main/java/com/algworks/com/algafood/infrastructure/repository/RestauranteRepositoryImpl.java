package com.algworks.com.algafood.infrastructure.repository;

import com.algworks.com.algafood.domain.model.Restaurante;
import com.algworks.com.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



/*
* Atenção o nome da classe custumizada tem que ter
* o nome do repositorio e o sufixo Impl
* e com a  anotação @Repositor y
*
* */

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;


    //Consulta dinâmica com CRITERIAQUERY
    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasLength(nome)){
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();
    }


//    //Consulta dinâmica com JPQL
//    @Override
//    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//
//        var jpql = new StringBuilder();
//        jpql.append("from Restaurante where 0 = 0 ");
//
//        var parametros = new HashMap<String, Object>();
//
//        if (StringUtils.hasLength(nome)){
//            jpql.append("and nome like :nome ");
//            parametros.put("nome", "%" + nome + "%");
//        }
//
//        if (taxaFreteInicial != null){
//            jpql.append("and taxaFrete >= :taxaInicial ");
//            parametros.put("taxaInicial", taxaFreteInicial);
//        }
//
//        if (taxaFreteFinal != null){
//            jpql.append("and taxaFrete <= :taxaFinal ");
//            parametros.put("taxaFinal", taxaFreteFinal);
//        }
//
//        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//
//        parametros.forEach((chave , valor ) -> query.setParameter(chave, valor));
//
//
//        return query.getResultList();
//    }


//      Consulta simples com JPQL
//    @Override
//    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//
//        var jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";
//
//
//        return manager.createQuery(jpql, Restaurante.class)
//                .setParameter("nome" , "%" + nome + "%" )
//                .setParameter("taxaInicial", taxaFreteInicial)
//                .setParameter("taxaFinal", taxaFreteFinal)
//                .getResultList();
//    }
}
