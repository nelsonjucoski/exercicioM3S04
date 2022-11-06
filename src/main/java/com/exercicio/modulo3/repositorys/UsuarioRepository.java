package com.exercicio.modulo3.repositorys;

import com.exercicio.modulo3.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
    ArrayList<UsuarioModel> pegaUsuarioPorNome(String nome);

    @Query(value = "select u from UsuarioModel u where u.login = ?1")
    UsuarioModel buscaUsuarioPorLogin(String login);
}
