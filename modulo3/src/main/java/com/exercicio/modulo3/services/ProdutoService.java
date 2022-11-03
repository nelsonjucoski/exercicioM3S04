package com.exercicio.modulo3.services;

import com.exercicio.modulo3.models.ProdutoModel;
import com.exercicio.modulo3.repositorys.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoModel salvar(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    @Transactional
    public void delete(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    public ProdutoModel buscarPorId(Long idProduto) {
        return produtoRepository.findById(idProduto).get();
    }

    public List<ProdutoModel> buscarProdutoDescricao(String descricao) {
        List<ProdutoModel> produtoModels = produtoRepository.buscarProdutoDescricao(descricao);
        return produtoModels;
    }

    public List<ProdutoModel> listaProdutos() {
        return (List<ProdutoModel>) produtoRepository.findAll();
    }
}
