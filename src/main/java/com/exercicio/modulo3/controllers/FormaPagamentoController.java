package com.exercicio.modulo3.controllers;


import com.exercicio.modulo3.dto_inputs.ClienteDtoInput;
import com.exercicio.modulo3.dto_inputs.FormaPagamentoDtoInput;
import com.exercicio.modulo3.dto_outputs.FormaPagamentoDtoOutput;
import com.exercicio.modulo3.models.ClienteModel;
import com.exercicio.modulo3.models.FormaPagamentoModel;
import com.exercicio.modulo3.services.FormaPagamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Formas de pagamentos")
@RestController
@RequestMapping(value = "/formapagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;


    @ApiOperation(value = "Salvar forma de pagamento")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<FormaPagamentoDtoOutput> cadastrar(@RequestBody FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamentoModel formaPagamentoModel = converteFormPagDtoInput(formaPagamentoDtoInput);
        formaPagamentoService.salvar(formaPagamentoModel);
        return new ResponseEntity<FormaPagamentoDtoOutput>(converteFormPagDtoOutput(formaPagamentoModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar forma de pagamento")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<FormaPagamentoDtoOutput> atualizar(@RequestBody FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamentoModel formaPagamentoModel = formaPagamentoService.salvar(converteFormPagDtoInput(formaPagamentoDtoInput));
        return new ResponseEntity<FormaPagamentoDtoOutput>(converteFormPagDtoOutput(formaPagamentoModel), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar Forma de pagamento")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long idFormapagamento) {
        formaPagamentoService.delete(idFormapagamento);
        return new ResponseEntity<String>("Forma de pagamento deletada com sucesso!", HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar forma de pagamento por ID")
    @GetMapping(value = "/{idFormapagamento}")
    public ResponseEntity<FormaPagamentoDtoOutput> buscaPorId(@PathVariable(value = "idFormapagamento") Long idFormapagamento) {
        FormaPagamentoDtoOutput formaPagamentoDtoOutput = converteFormPagDtoOutput(formaPagamentoService.buscaPorId(idFormapagamento));
        return new ResponseEntity<FormaPagamentoDtoOutput>(formaPagamentoDtoOutput, HttpStatus.OK);
    }
    @ApiOperation(value = "Buscar forma de pagamento por descrição")
    @GetMapping(value = "/buscadescicao/{descricao}")
    public ResponseEntity<List<FormaPagamentoDtoOutput>> buscarFormaPagDescricao(@PathVariable(name = "descricao") String descricao) {
        List<FormaPagamentoModel> formaPagamentoModels = formaPagamentoService.buscarFormaPagDescricao(descricao);
        List<FormaPagamentoDtoOutput> formaPagamentoDtoOutputs = converteListFormaPagamento(formaPagamentoModels);
        return new ResponseEntity<List<FormaPagamentoDtoOutput>>(formaPagamentoDtoOutputs, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar todas formas de pagamentos")
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<FormaPagamentoDtoOutput>> ListaFormaPagamento() {
        List<FormaPagamentoModel> formaPagamentoModels = formaPagamentoService.ListaFormaPagamento();
        List<FormaPagamentoDtoOutput> formaPagamentoDtoOutputs = converteListFormaPagamento(formaPagamentoModels);
        return new ResponseEntity<List<FormaPagamentoDtoOutput>>(formaPagamentoDtoOutputs, HttpStatus.OK);
    }

    /*** Metodos de convessão de objetos ***/
    private FormaPagamentoModel converteFormPagDtoInput(FormaPagamentoDtoInput formaPagamentoDtoInput) {
        FormaPagamentoModel formaPagamentoModel = new FormaPagamentoModel();
        formaPagamentoModel.setId(formaPagamentoDtoInput.getId());
        formaPagamentoModel.setDescricao(formaPagamentoDtoInput.getDescricao());
        return formaPagamentoModel;
    }

    private FormaPagamentoDtoOutput converteFormPagDtoOutput(FormaPagamentoModel formaPagamentoModel) {
        FormaPagamentoDtoOutput formaPagamentoDtoOutput = new FormaPagamentoDtoOutput();
        formaPagamentoDtoOutput.setId(formaPagamentoModel.getId());
        formaPagamentoDtoOutput.setDescricao(formaPagamentoModel.getDescricao());
        return formaPagamentoDtoOutput;
    }

    private List<FormaPagamentoDtoOutput> converteListFormaPagamento(List<FormaPagamentoModel> formaPagamentoModels) {
        return formaPagamentoModels.stream()
                .map(formaPagamentoModel -> converteFormPagDtoOutput(formaPagamentoModel))
                .collect(Collectors.toList());
    }

}
