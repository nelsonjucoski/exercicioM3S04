package com.exercicio.modulo3.repositorys;

import com.exercicio.modulo3.models.FormaPagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoModel, Long> {

    @Query(value = "select f from FormaPagamentoModel f where f.descricao like %?1%")
    List<FormaPagamentoModel>buscarFormaPagDescricao(String descricao);
}
