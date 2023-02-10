package com.algworks.com.algafood.infrastructure.repository;

import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


public class CozinhaRepositoryImpl  {


    @PersistenceContext
    private EntityManager manager;

    
    public List<Cozinha> todos(){
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);

        return query.getResultList();
    }

    
    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    
    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class, id);
    }

    
    @Transactional
    public void remover(Long id){
        Cozinha cozinha = buscar(id);

        if (cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cozinha);
    }
}
