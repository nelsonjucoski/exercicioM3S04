package com.exercicio.modulo3.dto_inputs;

import lombok.Data;

@Data
public class ItensPedidoDtoInput {
    private Long id;
    private Double valorItem;
    private Double quantidade;
    private Long id_produto;
    private Long id_pedido;
}
