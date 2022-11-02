package com.exercicio.modulo3.services;

import com.exercicio.modulo3.models.FormaPagamentoModel;
import com.exercicio.modulo3.repositorys.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamentoModel salvar(FormaPagamentoModel formaPagamentoModel){
        return formaPagamentoRepository.save(formaPagamentoModel);
    }

    @Transactional
    public void delete(Long idFormaPagamento){
        formaPagamentoRepository.deleteById(idFormaPagamento);
    }

    public FormaPagamentoModel buscaPorId(Long idFormapagamento){
        return formaPagamentoRepository.findById(idFormapagamento).get();
    }

    public List<FormaPagamentoModel>buscarFormaPagDescricao(String descricao){
        List<FormaPagamentoModel>formaPagamentoModels = formaPagamentoRepository.buscarFormaPagDescricao(descricao);
        return formaPagamentoModels;
    }

    public List<FormaPagamentoModel> ListaFormaPagamento(){
        return (List<FormaPagamentoModel>) formaPagamentoRepository.findAll();
    }
}
