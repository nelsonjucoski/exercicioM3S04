package com.exercicio.modulo3.services;

import com.exercicio.modulo3.models.ItensPedidoModel;
import com.exercicio.modulo3.repositorys.ItensPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItensPedidoService {
    @Autowired
    ItensPedidoRepository itensPedidoRepository;

    @Transactional
    public void deleteItensPedidoPorId(Long idItensPedido) {
        itensPedidoRepository.deleteById(idItensPedido);
    }

    public ItensPedidoModel pegaItensPedidoPorId(Long idItensPedido) {
        return itensPedidoRepository.findById(idItensPedido).get();
    }

    @Transactional
    public void deleteItensPedido(ItensPedidoModel itensPedidoModel) {
        itensPedidoRepository.delete(itensPedidoModel);
    }
}
