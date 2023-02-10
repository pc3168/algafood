package com.algworks.com.algafood.infrastructure.repository;

import com.algworks.com.algafood.domain.model.Restaurante;
import com.algworks.com.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestauranteRepositoryImpl  implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> todos(){
        TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        return manager.merge(restaurante);
    }

    @Override
    public Restaurante buscar(Long id){
        return manager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public void remover(Long id){
        Restaurante restaurante = buscar(id);

        if (restaurante == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(restaurante);
    }

}
