package com.exercicio.modulo3.controllers;

import com.exercicio.modulo3.dto_inputs.UsuarioDtoInput;
import com.exercicio.modulo3.dto_outputs.TelefoneDtoOutput;
import com.exercicio.modulo3.dto_outputs.UsuarioDtoOutput;
import com.exercicio.modulo3.models.TelefoneModel;
import com.exercicio.modulo3.models.UsuarioModel;
import com.exercicio.modulo3.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Usuários")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Salvar usuário")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDtoOutput> cadastrar(@RequestBody UsuarioDtoInput usuarioDtoInput) {
        UsuarioModel usuarioModel = converteDtoInput(usuarioDtoInput);
        usuarioService.salvar(usuarioModel);
        return new ResponseEntity<UsuarioDtoOutput>(converteDtoOutput(usuarioModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar usuário")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDtoOutput> atualizar(@RequestBody UsuarioDtoInput usuarioDtoInput) {
        UsuarioModel usuarioModel = converteDtoInput(usuarioDtoInput);
        usuarioService.salvar(usuarioModel);
        return new ResponseEntity<UsuarioDtoOutput>(converteDtoOutput(usuarioModel), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar usuário")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario) {
        usuarioService.delete(idUsuario);
        return new ResponseEntity<String>("Usuário deletado com sucesso!!", HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar usuário por ID")
    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioDtoOutput> pegaUsuarioPorId(@PathVariable(value = "idUsuario") Long idUsuario) {
        UsuarioModel usuarioModel = usuarioService.buscaUsuarioPorId(idUsuario);
        UsuarioDtoOutput usuarioDtoOutput = converteDtoOutput(usuarioModel);
        return new ResponseEntity<UsuarioDtoOutput>(usuarioDtoOutput, HttpStatus.OK);
    }

    @ApiOperation(value = "Bsucar usuário por nome")
    @GetMapping(value = "buscarPorNome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioDtoOutput>> buscUsuarioPorNome(@RequestParam (name = "nome") String nome){
        List<UsuarioModel> usuarioModels = usuarioService.pegaUsuarioPorNome(nome);
        List<UsuarioDtoOutput> usuarioDtoOutputs = converteListUsuario(usuarioModels);
        return  new ResponseEntity<List<UsuarioDtoOutput>>(usuarioDtoOutputs, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar todos os usuário")
    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioDtoOutput>> listaUsuarios(){
        List<UsuarioModel> usuarioModels = usuarioService.listaUsuarios();
        List<UsuarioDtoOutput> usuarioDtoOutputs = converteListUsuario(usuarioModels);
        return new ResponseEntity<List<UsuarioDtoOutput>>(usuarioDtoOutputs, HttpStatus.OK);
    }

    /*** Metodos de convessão de objetos ***/

    private UsuarioDtoOutput converteDtoOutput(UsuarioModel usuarioModel) {
        UsuarioDtoOutput usuarioDtoOutput = new UsuarioDtoOutput();
        usuarioDtoOutput.setId(usuarioModel.getId());
        usuarioDtoOutput.setNome(usuarioModel.getNome());
        usuarioDtoOutput.setLogin(usuarioModel.getLogin());
        usuarioDtoOutput.setSenha(usuarioModel.getSenha());

        for (int i = 0; i < usuarioModel.getTelefoneModels().size(); i++) {
            TelefoneDtoOutput telefoneDtoOutput = new TelefoneDtoOutput();
            telefoneDtoOutput.setTipo(usuarioModel.getTelefoneModels().get(i).getTipo());
            telefoneDtoOutput.setNumero(usuarioModel.getTelefoneModels().get(i).getNumero());
            telefoneDtoOutput.setId(usuarioModel.getTelefoneModels().get(i).getId());

            usuarioDtoOutput.getTelefoneDtoOutputs().add(telefoneDtoOutput);
        }
        return usuarioDtoOutput;

    }

    private UsuarioModel converteDtoInput(UsuarioDtoInput usuarioDtoInput) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuarioDtoInput.getId());
        usuarioModel.setNome(usuarioDtoInput.getNome());
        usuarioModel.setLogin(usuarioDtoInput.getLogin());
        usuarioModel.setSenha(usuarioDtoInput.getSenha());

        for (int i = 0; i < usuarioDtoInput.getTelefoneDtoOutputs().size(); i++) {
            TelefoneModel telefoneModel = new TelefoneModel();
            telefoneModel.setTipo(usuarioDtoInput.getTelefoneDtoOutputs().get(i).getTipo());
            telefoneModel.setNumero(usuarioDtoInput.getTelefoneDtoOutputs().get(i).getNumero());
            telefoneModel.setId(usuarioDtoInput.getTelefoneDtoOutputs().get(i).getId());
            telefoneModel.setUsuario(usuarioModel);

            usuarioModel.getTelefoneModels().add(telefoneModel);
        }
        return usuarioModel;
    }

    private List<UsuarioDtoOutput> converteListUsuario(List<UsuarioModel> usuarioModels) {
        return usuarioModels.stream()
                .map(usuarioModel -> converteDtoOutput(usuarioModel))
                .collect(Collectors.toList());
    }

}
