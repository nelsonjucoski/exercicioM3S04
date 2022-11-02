package com.exercicio.modulo3.controllers;

import com.exercicio.modulo3.dto_inputs.PedidoDtoInput;
import com.exercicio.modulo3.dto_outputs.ItensPedidoDtoOutput;
import com.exercicio.modulo3.dto_outputs.PedidoDtoOutput;
import com.exercicio.modulo3.models.ItensPedidoModel;
import com.exercicio.modulo3.models.PedidoModel;
import com.exercicio.modulo3.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItensPedidoService itensPedidoService;

    @PostMapping(name = "/", produces = "application/json")
    public ResponseEntity<PedidoDtoOutput> cadastrar(@RequestBody PedidoDtoInput pedidoDtoInput) {
        PedidoModel pedidoModel = pedidoService.salvar(converteDtoInput(pedidoDtoInput));
        return new ResponseEntity<PedidoDtoOutput>(converteDtoOutput(pedidoModel), HttpStatus.CREATED);
    }
    @PutMapping(name = "/", produces = "application/json")
    public ResponseEntity<PedidoDtoOutput> atualizar(@RequestBody PedidoDtoInput pedidoDtoInput){
        PedidoModel pedidoModel = pedidoService.salvar(converteDtoInput(pedidoDtoInput));
        return new ResponseEntity<PedidoDtoOutput>(converteDtoOutput(pedidoModel), HttpStatus.OK);
    }
    @DeleteMapping
    @ResponseBody
    public  ResponseEntity<String> delete(@RequestParam Long idPedido){
        pedidoService.delete(idPedido);
        return new ResponseEntity<String>("Pedido deletado com sucesso!!!", HttpStatus.OK);
    }

    @GetMapping(value = "/buscaporid/{idPedido}", produces = "application/json")
    public ResponseEntity<PedidoDtoOutput> buscaPedidoId(@PathVariable(value = "idPedido") Long idPedido) {
        PedidoDtoOutput pedidoDtoOutput = converteDtoOutput(pedidoService.pegaPedidoPorId(idPedido));
        return new ResponseEntity<PedidoDtoOutput>(pedidoDtoOutput, HttpStatus.OK);
    }
    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<PedidoDtoOutput>> listaPedidos(){
        List<PedidoModel> pedidoModels = pedidoService.listaPedidos();
        return new ResponseEntity<List<PedidoDtoOutput>>(converteListPedido(pedidoModels), HttpStatus.OK);
    }
    @GetMapping(value = "/cliente/{idCliente}")
    private ResponseEntity<List<PedidoDtoOutput>> pedidosPorIdCliente(@PathVariable(name = "idCliente")Long idCliente){
        List<PedidoModel> pedidoModels = pedidoService.pegaPedidoPorIdCliente(idCliente);
        List<PedidoDtoOutput> pedidoDtoOutputs = converteListPedido(pedidoModels);
        return new ResponseEntity<List<PedidoDtoOutput>>(pedidoDtoOutputs, HttpStatus.OK);
    }


    /**
     * Metodos de converção DTO
     **/


    private PedidoDtoOutput converteDtoOutput(PedidoModel pedidoModel) {
        PedidoDtoOutput pedidoDtoOutput = new PedidoDtoOutput();
        pedidoDtoOutput.setId(pedidoModel.getId());
        pedidoDtoOutput.setId_cliente(pedidoModel.getClienteModel().getId());
        pedidoDtoOutput.setNome_cliente(pedidoModel.getClienteModel().getNome());
        pedidoDtoOutput.setId_formaPagamento(pedidoModel.getFormaPagamentoModel().getId());
        pedidoDtoOutput.setFormaPagamentoDecricao(pedidoModel.getFormaPagamentoModel().getDescricao());


        for (int i = 0; i < pedidoModel.getItensPedidoModel().size(); i++) {
            ItensPedidoDtoOutput itensPedidoDtoOutput = new ItensPedidoDtoOutput();

            itensPedidoDtoOutput.setId(pedidoModel.getItensPedidoModel().get(i).getId());
            itensPedidoDtoOutput.setId_produto(pedidoModel.getItensPedidoModel().get(i).getProduto().getId());
            itensPedidoDtoOutput.setDescricaoProduto(pedidoModel.getItensPedidoModel().get(i).getProduto().getDescricao());
            itensPedidoDtoOutput.setQuantidade(pedidoModel.getItensPedidoModel().get(i).getQuantidade());
            itensPedidoDtoOutput.setValorItem(pedidoModel.getItensPedidoModel().get(i).getValorItem());

            pedidoDtoOutput.getItensPedidoModel().add(itensPedidoDtoOutput);
        }

        return pedidoDtoOutput;
    }

    private PedidoModel converteDtoInput(PedidoDtoInput pedidoDtoInput) {
        PedidoModel pedidoModel = new PedidoModel();

        pedidoModel.setId(pedidoDtoInput.getId());
        pedidoModel.setClienteModel(clienteService.buscarPorId(pedidoDtoInput.getId_cliente()));
        pedidoModel.setFormaPagamentoModel(formaPagamentoService.buscaPorId(pedidoDtoInput.getId_formaPagamento()));

        for (int i = 0; i < pedidoDtoInput.getItensPedidoModel().size(); i++) {
            ItensPedidoModel itensPedidoModel = new ItensPedidoModel();
            itensPedidoModel.setId(pedidoDtoInput.getItensPedidoModel().get(i).getId());
            itensPedidoModel.setPedidoModel(pedidoModel);
            itensPedidoModel.setProduto(produtoService.buscarPorId(pedidoDtoInput.getItensPedidoModel().get(i).getId_produto()));

            if (pedidoDtoInput.getItensPedidoModel().get(i).getId() == null) {
                itensPedidoModel.setValorItem(produtoService.buscarPorId(pedidoDtoInput.getItensPedidoModel().get(i).getId_produto()).getPrecoVenda());
            } else {
                itensPedidoModel.setValorItem(pedidoDtoInput.getItensPedidoModel().get(i).getValorItem());
            }
            itensPedidoModel.setQuantidade(pedidoDtoInput.getItensPedidoModel().get(i).getQuantidade());
            pedidoModel.getItensPedidoModel().add(itensPedidoModel);
        }
        return pedidoModel;
    }

    private List<PedidoDtoOutput> converteListPedido(List<PedidoModel> pedidoModels) {
        return pedidoModels.stream()
                .map(PedidoModel -> converteDtoOutput(PedidoModel))
                .collect(Collectors.toList());
    }
}
