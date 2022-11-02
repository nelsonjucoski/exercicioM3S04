package com.exercicio.modulo3.dto_outputs;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ProdutoDtoOutput {
    private Long id;
    private String descricao;
    private Double precoVenda;
    private Double precoCompra;
    private OffsetDateTime dataHoraCadastro;
    private OffsetDateTime dataHoraAlteracao;
}
