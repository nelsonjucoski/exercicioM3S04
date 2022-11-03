package com.exercicio.modulo3.controllers;


import com.exercicio.modulo3.dto_inputs.ProdutoDtoInput;
import com.exercicio.modulo3.dto_outputs.ProdutoDtoOutput;
import com.exercicio.modulo3.models.ProdutoModel;
import com.exercicio.modulo3.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<ProdutoDtoOutput> cadastrar(@RequestBody ProdutoDtoInput produtoDtoInput) {
        ProdutoModel produtoModel = converteDtoInput(produtoDtoInput);
        produtoService.salvar(produtoModel);
        return new ResponseEntity<ProdutoDtoOutput>(converteDtoOutput(produtoModel), HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<ProdutoDtoOutput> atualizar(@RequestBody ProdutoDtoInput produtoDtoInput) {
        ProdutoModel produtoModel = converteDtoInput(produtoDtoInput);
        produtoService.salvar(produtoModel);
        return new ResponseEntity<ProdutoDtoOutput>(converteDtoOutput(produtoModel), HttpStatus.OK);
    }

    @DeleteMapping(name = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idProduto) {
        produtoService.delete(idProduto);
        return new ResponseEntity<String>("Produto deletado com sucesso!", HttpStatus.OK);
    }
    @GetMapping(name = "/")
    @ResponseBody
    public ResponseEntity<List<ProdutoDtoOutput>> listaProdutos(){
        List<ProdutoModel> produtoModels = produtoService.listaProdutos();
        List<ProdutoDtoOutput> produtoDtoOutputs = converteListProdutos(produtoModels);
        return new ResponseEntity<List<ProdutoDtoOutput>>(produtoDtoOutputs, HttpStatus.OK);
    }

    @GetMapping(value = "/buscardescricao/{descricao}")
    public ResponseEntity<List<ProdutoDtoOutput>> buscarProdutoDescricao(@PathVariable(name = "descricao") String descricao){
    List<ProdutoModel> produtoModels = produtoService.buscarProdutoDescricao(descricao);
    List<ProdutoDtoOutput> produtoDtoOutputs = converteListProdutos(produtoModels);
    return  new ResponseEntity<List<ProdutoDtoOutput>>(produtoDtoOutputs, HttpStatus.OK);
    }

    /*** Metodos de convessão de objetos ***/
    private ProdutoModel converteDtoInput(ProdutoDtoInput produtoDtoInput) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(produtoDtoInput.getId());
        produtoModel.setDescricao(produtoDtoInput.getDescricao());
        produtoModel.setPrecoCompra(produtoDtoInput.getPrecoCompra());
        produtoModel.setPrecoVenda(produtoDtoInput.getPrecoVenda());
        return produtoModel;
    }

    private ProdutoDtoOutput converteDtoOutput(ProdutoModel produtoModel) {
        ProdutoDtoOutput produtoDtoOutput = new ProdutoDtoOutput();
        produtoDtoOutput.setId(produtoModel.getId());
        produtoDtoOutput.setDescricao(produtoModel.getDescricao());
        produtoDtoOutput.setPrecoCompra(produtoModel.getPrecoCompra());
        produtoDtoOutput.setPrecoVenda(produtoModel.getPrecoVenda());
        produtoDtoOutput.setDataHoraCadastro(produtoModel.getDataHoraCadastro());
        produtoDtoOutput.setDataHoraAlteracao(produtoModel.getDataHoraAlteracao());
        return produtoDtoOutput;
    }

    private List<ProdutoDtoOutput> converteListProdutos(List<ProdutoModel> produtoModels) {
        return produtoModels.stream()
                .map(ProdutoModel -> converteDtoOutput(ProdutoModel))
                .collect(Collectors.toList());
    }
}
