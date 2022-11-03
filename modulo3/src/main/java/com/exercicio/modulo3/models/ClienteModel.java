package com.exercicio.modulo3.models;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "cliente")
public class ClienteModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 80)
    private String nome;
    @Column(name = "cpf", length = 11, unique = true)
    private Long cpf;
    @Column(name = "rg", length = 7, unique = true)
    private Long rg;

}
