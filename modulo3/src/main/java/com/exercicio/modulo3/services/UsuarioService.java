package com.exercicio.modulo3.services;

import com.exercicio.modulo3.models.UsuarioModel;
import com.exercicio.modulo3.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        usuarioModel.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
        return usuarioRepository.save(usuarioModel);
    }

    @Transactional
    public void delete(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Transactional(readOnly = true)
    public UsuarioModel buscaUsuarioPorId(Long idUsuario) {
        UsuarioModel usuarioModel = usuarioRepository.findById(idUsuario).get();
        usuarioModel.getTelefoneModels().size();
        return usuarioModel;
    }

    @Transactional(readOnly = true)
    public List<UsuarioModel> pegaUsuarioPorNome(String nome) {
        List<UsuarioModel> usuarioModels = usuarioRepository.pegaUsuarioPorNome(nome);

        for (UsuarioModel usuarioModel : usuarioModels) {
            usuarioModel.getTelefoneModels().isEmpty();
        }
        return usuarioModels;
    }

    @Transactional(readOnly = true)
    public List<UsuarioModel> listaUsuarios() {
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();

        for (UsuarioModel usuarioModel : usuarioModels) {
            usuarioModel.getTelefoneModels().isEmpty();
        }

        return usuarioModels;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.buscaUsuarioPorLogin(userName);

        if (usuarioModel == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!!!");
        }
        return new User(usuarioModel.getLogin(), usuarioModel.getPassword(), usuarioModel.getAuthorities());
    }
}
