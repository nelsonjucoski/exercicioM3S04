package com.exercicio.modulo3.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "produto")
public class ProdutoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", length = 80)
    private String descricao;

    @Column(name = "precocompra")
    private Double precoCompra;

    @Column(name = "precovenda")
    private Double precoVenda;

    @CreationTimestamp
    @Column(name = "datahoracadastro", columnDefinition = "timestamp without time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))", updatable = false)
    private OffsetDateTime dataHoraCadastro;

    @UpdateTimestamp
    @Column(name = "datahoraalteracao", columnDefinition = "timestamp with time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))")
    private OffsetDateTime dataHoraAlteracao;

}
