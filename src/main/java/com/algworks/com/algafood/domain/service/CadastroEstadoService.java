package com.algworks.com.algafood.domain.service;

import com.algworks.com.algafood.domain.exception.EntidadeEmUsoException;
import com.algworks.com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algworks.com.algafood.domain.model.Estado;
import com.algworks.com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso ";
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        return  estadoRepository.save(estado);
    }

    public Estado buscaOuFalhar(Long id){
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }


    public void excluir(Long id){
        try{
            estadoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw  new EstadoNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw  new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO,id));
        }
    }

}
