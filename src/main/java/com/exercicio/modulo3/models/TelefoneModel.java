package com.exercicio.modulo3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "telefone")
public class TelefoneModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numero;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario"))
    @JsonBackReference
    private UsuarioModel usuarioModel;
}
