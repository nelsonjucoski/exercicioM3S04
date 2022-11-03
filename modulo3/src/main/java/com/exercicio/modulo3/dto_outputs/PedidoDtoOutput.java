package com.exercicio.modulo3.dto_outputs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoDtoOutput {
    private Long id;
    private Long id_cliente;
    private String nome_cliente;
    private Long id_formaPagamento;
    private String formaPagamentoDecricao;
    private List<ItensPedidoDtoOutput> itensPedidoModel = new ArrayList<ItensPedidoDtoOutput>();



}
