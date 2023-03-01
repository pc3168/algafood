package com.algworks.com.algafood.api.controller;

import com.algworks.com.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algworks.com.algafood.domain.exception.NegocioException;
import com.algworks.com.algafood.domain.exception.ValidacaoException;
import com.algworks.com.algafood.domain.model.Restaurante;
import com.algworks.com.algafood.domain.repository.RestauranteRepository;
import com.algworks.com.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("{id}")
    public Restaurante buscar(@PathVariable  Long id){
        return cadastroRestaurante.buscaOuFalha(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante){
        try{
            return cadastroRestaurante.salvar(restaurante);
        }catch (CozinhaNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("{id}")
    public Restaurante atualizar(@PathVariable Long id ,
                                 @RequestBody @Valid Restaurante restaurante){

        Restaurante restauranteAtual = cadastroRestaurante.buscaOuFalha(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual,
                "id", "formasPagamento", "endereco", "dataCadastro");
        //"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try{
            return cadastroRestaurante.salvar(restauranteAtual);
        }catch (CozinhaNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PatchMapping("{id}")
    public Restaurante atualizarParcial(@PathVariable Long id ,
                                        @RequestBody Map<String, Object> campos,
                                        HttpServletRequest request){
        Restaurante restauranteAtual = cadastroRestaurante.buscaOuFalha(id);

        merge(campos, restauranteAtual, request);
        validate(restauranteAtual, "restaurante");

        return atualizar(id, restauranteAtual);
    }

    private void validate(Restaurante restaurante, String objectName){
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

        validator.validate(restaurante, bindingResult);

        if(bindingResult.hasErrors()){
            throw new ValidacaoException(bindingResult);
        }
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino,
                              HttpServletRequest request) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        //aula 5.4
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class,nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });

        }catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause,serverHttpRequest);
        }

    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        cadastroRestaurante.excluir(id);
    }


//    @GetMapping("{id}")
//    public ResponseEntity<Restaurante> buscar(@PathVariable  Long id){
//        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
//
//        if (!restaurante.isPresent()){
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(restaurante.get());
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
//        try{
//            restaurante = cadastroRestaurante.salvar(restaurante);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
//        }catch (EntidadeNaoEncontradaException e ){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<?> atualizar(@PathVariable Long id ,
//                                       @RequestBody Restaurante restaurante){
//
//        try {
//            Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
//
//            if (restauranteAtual.isPresent()){
//                BeanUtils.copyProperties(restaurante, restauranteAtual.get(),
//                        "id", "formasPagamento", "endereco", "dataCadastro");
//                //"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
//
//                Restaurante restauranteSalvar = cadastroRestaurante.salvar(restauranteAtual.get());
//                return ResponseEntity.ok(restauranteSalvar);
//            }
//
//            return ResponseEntity.notFound().build();
//
//        }catch(EntidadeNaoEncontradaException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//
//    }
//
//    @PatchMapping("{id}")
//    public ResponseEntity<?> atualizarParcial(@PathVariable Long id ,
//                                              @RequestBody Map<String, Object> campos){
//        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
//
//        if (!restauranteAtual.isPresent()){
//            return ResponseEntity.notFound().build();
//        }
//
//        merge(campos, restauranteAtual.get());
//
//        return atualizar(id, restauranteAtual.get());
//    }
//
//    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
//        //aula 5.4
//        ObjectMapper objectMapper = new ObjectMapper();
//        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//
//        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//            Field field = ReflectionUtils.findField(Restaurante.class,nomePropriedade);
//            field.setAccessible(true);
//
//            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
//            ReflectionUtils.setField(field, restauranteDestino, novoValor);
//        });
//
//    }
//
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Restaurante> remover(@PathVariable Long id){
//        try {
//            cadastroRestaurante.excluir(id);
//            return ResponseEntity.noContent().build();
//
//        }catch (EntidadeNaoEncontradaException e){
//            return ResponseEntity.notFound().build();
//        }
//        catch (EntidadeEmUsoException e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//
//    }


    @GetMapping("/custom")
    public List<Restaurante> custom(){
        System.out.println("custom");
        return restauranteRepository.listaTotasPersonalisado();
    }

    @GetMapping("/agrupado")
    public List<Restaurante> agrupado(String nome , BigDecimal ini, BigDecimal fim){
        System.out.println(nome + " "+ini + " " + fim);

        //List<Map<String, Object>> campos = restauranteRepository.listaComGroupBy();
//        return restauranteRepository.consultaPorNome2(nome, id);
        return restauranteRepository.find(nome, ini, fim);
//        System.out.println(restauranteRepository.listaComGroupBy());
//        List<String> lista = restauranteRepository.listaComGroupBy();
//        System.out.println(lista.size());
//        for (String valor : lista) {
//            String[] val = valor.split(",");
//            for (String str: val) {
//                System.out.println(str);
//            }
//            System.out.println("-----------------------");
//        }
//        for (Map campo1: campos) {
//            campo1.forEach((chave , valor) -> {
//                System.out.print(chave + " = " + valor+" ");
//            });
//            System.out.println();
//        }
    }


}
