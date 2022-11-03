package com.exercicio.modulo3.dto_outputs;

import lombok.Data;

@Data
public class ItensPedidoDtoOutput {
    private Long id;
    private Long id_produto;
    private String descricaoProduto;
    private Double quantidade;
    private Double valorItem;
}
