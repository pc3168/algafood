package com.algworks.com.algafood.api.controller;


import com.algworks.com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algworks.com.algafood.domain.exception.NegocioException;
import com.algworks.com.algafood.domain.model.Cidade;
import com.algworks.com.algafood.domain.repository.CidadeRepository;
import com.algworks.com.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("{id}")
    public Cidade buscar(@PathVariable Long id){
        return cadastroCidade.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        try{
            return cadastroCidade.salvar(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("{id}")
    public Cidade atualizar(@PathVariable Long id , @RequestBody Cidade cidade){
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
//        try{
//            return cadastroCidade.salvar(cidadeAtual);
//        }catch (EntidadeNaoEncontradaException e){
//            throw new NegocioException(e.getMessage(),e);
//        }
        return adicionar(cidadeAtual);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        cadastroCidade.excluir(id);
    }




//    @PutMapping("{id}")
//    public ResponseEntity<?> atualizar(@PathVariable Long id , @RequestBody Cidade cidade){
//        Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
//
//        try{
//            if (cidadeAtual.isPresent()){
//                BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
//                Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
//                return  ResponseEntity.ok(cidadeSalva);
//            }
//        }catch (EntidadeNaoEncontradaException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> remover(@PathVariable Long id){
//        try{
//            cadastroCidade.excluir(id);
//            return ResponseEntity.noContent().build();
//        }catch (EntidadeNaoEncontradaException e){
//            return ResponseEntity.notFound().build();
//        }catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(e.getMessage());
//        }
//    }
}
