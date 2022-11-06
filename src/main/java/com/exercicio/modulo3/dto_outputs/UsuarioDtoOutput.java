package com.exercicio.modulo3.dto_outputs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class UsuarioDtoOutput {
    private Long id;
    private String nome;
    private String login;

    private String senha;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = "tipo", allowGetters = true)
    private List<TelefoneDtoOutput> telefoneDtoOutputs = new ArrayList<TelefoneDtoOutput>();

}
