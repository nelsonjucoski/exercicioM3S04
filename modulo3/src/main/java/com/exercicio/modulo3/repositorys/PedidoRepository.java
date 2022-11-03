package com.exercicio.modulo3.repositorys;

import com.exercicio.modulo3.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

    @Query("select p from PedidoModel p where p.clienteModel.id = ?1")
    List<PedidoModel> pegaPedidoPorIdCliente(Long idCliente);
}
