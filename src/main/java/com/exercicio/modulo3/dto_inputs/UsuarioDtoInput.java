package com.exercicio.modulo3.dto_inputs;


import com.exercicio.modulo3.dto_outputs.TelefoneDtoOutput;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioDtoInput {
    private Long id;
    private String nome;
    private String login;
    private String senha;

    private List<TelefoneDtoOutput> telefoneDtoOutputs = new ArrayList<TelefoneDtoOutput>();
}
