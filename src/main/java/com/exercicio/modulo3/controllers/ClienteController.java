package com.exercicio.modulo3.controllers;

import com.exercicio.modulo3.dto_inputs.ClienteDtoInput;
import com.exercicio.modulo3.dto_outputs.ClienteDtoOutput;
import com.exercicio.modulo3.models.ClienteModel;
import com.exercicio.modulo3.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Clientes")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @ApiOperation( value = "Salvar usuário")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<ClienteDtoOutput> cadastrar(@RequestBody ClienteDtoInput clienteDtoInput) {
        ClienteModel clienteModel = converteDtoInput(clienteDtoInput);
        clienteService.salvar(clienteModel);
        return new ResponseEntity<ClienteDtoOutput>(converteDtoOutput(clienteModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar usuário")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<ClienteDtoOutput> atualizar(@RequestBody ClienteDtoInput clienteDtoInput) {
        ClienteModel clienteModel = clienteService.salvar(converteDtoInput(clienteDtoInput));
        return new ResponseEntity<ClienteDtoOutput>(converteDtoOutput(clienteModel), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar usuário")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> deletar(@ApiParam(value = "Passe o id do usuário", example = "1") @RequestParam Long idCliente) {
        clienteService.delete(idCliente);
        return new ResponseEntity<String>("Cliente deletado com sucesso!", HttpStatus.OK);
    }
    @ApiOperation(value = "Buscar um usuário por seu ID")
    @GetMapping(value = "/{idProduto}")
    public ResponseEntity<ClienteDtoOutput> buscaPorId(@PathVariable(value = "idProduto") Long idProduto) {
        ClienteDtoOutput clienteDtoOutput = converteDtoOutput(clienteService.buscarPorId(idProduto));
        return new ResponseEntity<ClienteDtoOutput>(clienteDtoOutput, HttpStatus.OK);
    }
    @ApiOperation(value = "Buscar um usuário por sue nome")
    @GetMapping(value = "/buscanome/{nome}")
    public ResponseEntity<List<ClienteDtoOutput>> buscaClienteNome(@PathVariable(name = "nome") String nome){
        List<ClienteModel> clienteModels = clienteService.buscarClienteNome(nome);
        List<ClienteDtoOutput> clienteDtoOutputs = converteListClientes(clienteModels);
        return new ResponseEntity<List<ClienteDtoOutput>>(clienteDtoOutputs, HttpStatus.OK);
    }

    @ApiOperation(value = "Lista todos os usuários")
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<ClienteDtoOutput>> ListarClientes() {
        List<ClienteModel> clienteModels = clienteService.ListaClientes();
        List<ClienteDtoOutput> clienteDtoOutputs = converteListClientes(clienteModels);
        return new ResponseEntity<List<ClienteDtoOutput>>(clienteDtoOutputs, HttpStatus.OK);
    }


    /*** Metodos de convessão de objetos ***/

    private ClienteModel converteDtoInput(ClienteDtoInput clienteDtoInput) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDtoInput.getId());
        clienteModel.setNome(clienteDtoInput.getNome());
        clienteModel.setCpf(clienteDtoInput.getCpf());
        clienteModel.setRg(clienteDtoInput.getRg());
        return clienteModel;
    }

    private ClienteDtoOutput converteDtoOutput(ClienteModel clienteModel) {
        ClienteDtoOutput clienteDtoOutput = new ClienteDtoOutput();
        clienteDtoOutput.setId(clienteModel.getId());
        clienteDtoOutput.setNome(clienteModel.getNome());
        clienteDtoOutput.setCpf(clienteModel.getCpf());
        clienteDtoOutput.setRg(clienteModel.getRg());
        return clienteDtoOutput;
    }

    private List<ClienteDtoOutput> converteListClientes(List<ClienteModel> clienteModels) {
        return clienteModels.stream()
                .map(ClienteModel -> converteDtoOutput(ClienteModel))
                .collect(Collectors.toList());
    }


}
