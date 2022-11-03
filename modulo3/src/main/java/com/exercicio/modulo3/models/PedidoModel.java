package com.exercicio.modulo3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "pedido")
public class PedidoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp without time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))", updatable = false)
    private OffsetDateTime dataHoraCadastro;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp without time zone")
    private OffsetDateTime dataHoraAlteracao;

    @ManyToOne
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_cliente"))
    // @JsonBackReference
    private ClienteModel clienteModel;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "id_formaPagamento", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_formaPagamento"))
    private FormaPagamentoModel formaPagamentoModel;

    @OneToMany(mappedBy = "pedidoModel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItensPedidoModel> itensPedidoModel = new ArrayList<ItensPedidoModel>();

    @JsonManagedReference
    public List<ItensPedidoModel> pegaItensPedido() {
        return itensPedidoModel;
    }

    @JsonBackReference
    public ClienteModel pegaCliente() {
        return clienteModel;
    }

}
