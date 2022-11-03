package com.exercicio.modulo3.services;

import com.exercicio.modulo3.models.ClienteModel;
import com.exercicio.modulo3.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteModel salvar(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    @Transactional
    public void delete(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    public ClienteModel buscarPorId(Long idCliente) {
        return clienteRepository.findById(idCliente).get();
    }

    public List<ClienteModel> buscarClienteNome(String nome) {
        List<ClienteModel> clienteModels = clienteRepository.buscarClienteNome(nome);
        return clienteModels;
    }

    public List<ClienteModel> ListaClientes() {
        return (List<ClienteModel>) clienteRepository.findAll();
    }
}
