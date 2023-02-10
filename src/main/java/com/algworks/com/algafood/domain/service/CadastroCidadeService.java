package com.algworks.com.algafood.domain.service;

import com.algworks.com.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algworks.com.algafood.domain.exception.EntidadeEmUsoException;
import com.algworks.com.algafood.domain.model.Cidade;
import com.algworks.com.algafood.domain.model.Estado;
import com.algworks.com.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscaOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public Cidade buscarOuFalhar(Long id){
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }


    public void excluir(Long id){
        try{
            cidadeRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new CidadeNaoEncontradaException(id);
        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, id));
        }
    }


}
