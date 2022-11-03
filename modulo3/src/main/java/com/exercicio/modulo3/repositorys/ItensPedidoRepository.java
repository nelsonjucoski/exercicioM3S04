package com.exercicio.modulo3.repositorys;

import com.exercicio.modulo3.models.ItensPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedidoModel, Long> {

}
