package com.exercicio.modulo3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItensPedidoModel implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valorItem")
    private double valorItem;

    @Column(name = "quantidade")
    private double quantidade;


    @OneToOne
    @JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_produto"))
    private ProdutoModel produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pedido"))
    private PedidoModel pedidoModel;

    @JsonBackReference
    public  PedidoModel getPedido(){
        return pedidoModel;
    }

}
