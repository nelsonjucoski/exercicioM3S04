package com.exercicio.modulo3.services;

import com.exercicio.modulo3.models.PedidoModel;
import com.exercicio.modulo3.repositorys.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public PedidoModel salvar(PedidoModel pedidoModel) {
        return pedidoRepository.save(pedidoModel);
    }

    @Transactional
    public void delete(Long idPedido) {
        pedidoRepository.deleteById(idPedido);
    }

    public PedidoModel pegaPedidoPorId(Long idPedido) {
        return pedidoRepository.findById(idPedido).get();
    }

    public List<PedidoModel> listaPedidos(){
        return pedidoRepository.findAll();
    }

    public List<PedidoModel> pegaPedidoPorIdCliente(Long IdCliente){
        return pedidoRepository.pegaPedidoPorIdCliente(IdCliente);
    }

}
