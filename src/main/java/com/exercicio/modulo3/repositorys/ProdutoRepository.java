package com.exercicio.modulo3.repositorys;

import com.exercicio.modulo3.models.ClienteModel;
import com.exercicio.modulo3.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    @Query(value = "select p from ProdutoModel p  where p.descricao like %?1%")
    List<ProdutoModel> buscarProdutoDescricao(String descricao);
}
