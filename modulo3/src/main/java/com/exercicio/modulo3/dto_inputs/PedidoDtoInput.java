package com.exercicio.modulo3.dto_inputs;

import lombok.Data;
import java.util.List;
@Data
public class PedidoDtoInput {
    private Long id;
    private Long id_cliente;
    private Long id_formaPagamento;
    private List<ItensPedidoDtoInput> itensPedidoModel;
}
