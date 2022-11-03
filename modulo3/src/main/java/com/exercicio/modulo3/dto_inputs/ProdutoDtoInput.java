package com.exercicio.modulo3.dto_inputs;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ProdutoDtoInput {
    private Long id;
    private String descricao;
    private Double precoVenda;
    private Double precoCompra;
    private OffsetDateTime dataHoraCadastro;
    private OffsetDateTime dataHoraAlteracao;

}
