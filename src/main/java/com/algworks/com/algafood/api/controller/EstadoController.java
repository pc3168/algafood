package com.algworks.com.algafood.api.controller;

import com.algworks.com.algafood.domain.model.Estado;
import com.algworks.com.algafood.domain.repository.EstadoRepository;
import com.algworks.com.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;


    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("{id}")
    public Estado buscar(@PathVariable Long id){
        return cadastroEstado.buscaOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody @Valid Estado estado){
        return cadastroEstado.salvar(estado);
    }

    @PutMapping("{id}")
    public Estado atualizar(@PathVariable  Long id , @RequestBody @Valid Estado estado){
        Estado estadoAtual = cadastroEstado.buscaOuFalhar(id);

        BeanUtils.copyProperties(estado, estadoAtual,"id");

        return cadastroEstado.salvar(estadoAtual);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        cadastroEstado.excluir(id);
    }


//    @PostMapping
//    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
//        estado = cadastroEstado.salvar(estado);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<Estado> atualizar(@PathVariable  Long id , @RequestBody Estado estado){
//        Optional<Estado> estadoAtual = estadoRepository.findById(id);
//
//        if (estadoAtual.isPresent()){
//            BeanUtils.copyProperties(estado, estadoAtual.get(),"id");
//
//            Estado estadoSalva = cadastroEstado.salvar(estadoAtual.get());
//
//            return ResponseEntity.ok(estadoSalva);
//        }
//
//        return ResponseEntity.notFound().build();
//    }




}
