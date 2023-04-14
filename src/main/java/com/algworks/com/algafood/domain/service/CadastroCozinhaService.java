package com.algworks.com.algafood.domain.service;

import com.algworks.com.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algworks.com.algafood.domain.exception.EntidadeEmUsoException;
import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroCozinhaService {
    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;


    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha buscarOuFalhar(Long id){
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradoException(id));
    }


    @Transactional
    public void excluir(Long id){
        try {
            cozinhaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id));
        }
    }
}
