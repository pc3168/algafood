package com.algworks.com.algafood.domain.repository;

import com.algworks.com.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestauranteRepository
        extends JpaRepository<Restaurante, Long> , RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {


    //@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> findByNomeContaining(String nome);
    @Query(value = "SELECT * FROM restaurante where nome like '%Thai%'" , nativeQuery = true)
    List<Restaurante> listaTotasPersonalisado();

//    @Query(value = "SELECT nome, cozinha_id, count(*) as total FROM restaurante group by cozinha_id, nome" ,
//            nativeQuery = true)
//    List<Map<String, Object>> listaComGroupBy();
//
    @Query(value = "SELECT nome, cozinha_id, count(*) as total FROM restaurante group by cozinha_id, nome" ,
            nativeQuery = true)
    List<String> listaComGroupBy();

    @Query("From Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultaPorNome(String nome , @Param("id") Long cozinhaId);


    //@Query("From Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultaPorNome2(String nome , @Param("id") Long cozinhaId);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);


}
