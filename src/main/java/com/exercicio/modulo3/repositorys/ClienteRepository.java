package com.exercicio.modulo3.repositorys;

import com.exercicio.modulo3.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    @Query(value = "select c from ClienteModel c  where c.nome like %?1%")
    List<ClienteModel>buscarClienteNome(String nome);

}
