package com.algworks.com.algafood.api.controller;

import com.algworks.com.algafood.domain.model.Cozinha;
import com.algworks.com.algafood.domain.repository.CozinhaRepository;
import com.algworks.com.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }


    @GetMapping("{id}")
    public Cozinha buscar(@PathVariable Long id){
        return cadastroCozinha.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("{id}")
    public Cozinha atualizar(@PathVariable Long id,
                                             @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(id);

        BeanUtils.copyProperties(cozinha , cozinhaAtual, "id");

        return cadastroCozinha.salvar(cozinhaAtual);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id)  {
        cadastroCozinha.excluir(id);
    }



    //    @GetMapping("{id}")
//    public ResponseEntity<Cozinha> buscar(@PathVariable Long id){
//        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
//
//        if (cozinha.isPresent()){
//            return ResponseEntity.ok(cozinha.get());
//        }
//
//        return ResponseEntity.notFound().build();
//    }


//    @PutMapping("{id}")
//    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
//                                             @RequestBody Cozinha cozinha){
//        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
//
//        if(cozinhaAtual.isPresent()){
//            BeanUtils.copyProperties(cozinha , cozinhaAtual.get(), "id");
//
//            Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
//            return ResponseEntity.ok(cozinhaSalva);
//        }
//
//        return ResponseEntity.notFound().build();
//
//    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Cozinha> remover(@PathVariable Long id){
//        try {
//            cadastroCozinha.excluir(id);
//            return ResponseEntity.noContent().build();
//
//        }catch (EntidadeEmUsoException e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//
//        }catch (EntidadeNaoEncontradaException e ){
//            return ResponseEntity.notFound().build();
//        }
//
//    }


    @GetMapping("/por-nome/")
    public List<Cozinha> porNome(String nome){
        return cozinhaRepository.findByNomeContaining(nome);
    }


}
