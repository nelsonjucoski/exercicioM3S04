package com.exercicio.modulo3.dto_inputs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ClienteDtoInput {
    private Long id;
    private String nome;
    private Long cpf;
    private Long rg;
}
