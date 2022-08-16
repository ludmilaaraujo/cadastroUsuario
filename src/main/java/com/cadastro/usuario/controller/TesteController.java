package com.cadastro.usuario.controller;

import com.cadastro.usuario.entity.UsuarioEntity;
import com.cadastro.usuario.repository.UsuarioRepository;
import com.cadastro.usuario.request.ModelDadosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class TesteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity converteRequestEmEntidade(ModelDadosUsuario modelDadosUsuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setCpf(modelDadosUsuario.getCpf());
        usuarioEntity.setNome(modelDadosUsuario.getNome());
        usuarioEntity.setEndereco(modelDadosUsuario.getEndereco());

        return usuarioEntity;
    }

    public ModelDadosUsuario converteEntidadeEmResponse(UsuarioEntity usuarioEntity) {
        ModelDadosUsuario modelDadosUsuario = new ModelDadosUsuario();
        modelDadosUsuario.setCpf(usuarioEntity.getCpf());
        modelDadosUsuario.setEndereco(usuarioEntity.getEndereco());
        modelDadosUsuario.setNome(usuarioEntity.getNome());

        return modelDadosUsuario;
    }

    @PostMapping("/")
    public UsuarioEntity usuarioResposta(@RequestBody ModelDadosUsuario usuario) {
        return usuarioRepository.save(converteRequestEmEntidade(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> usuarioResponse(@PathVariable long id) {
        return ResponseEntity.ok(usuarioRepository.findById(id).get());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody ModelDadosUsuario modelDadosUsuario) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if (usuarioEntity.isPresent()){
            usuarioEntity.get().setNome(modelDadosUsuario.getNome());
            usuarioEntity.get().setEndereco(modelDadosUsuario.getEndereco());
            usuarioEntity.get().setCpf(modelDadosUsuario.getCpf());
            ModelDadosUsuario modeloDadosAlterado = converteEntidadeEmResponse(usuarioEntity.get());
            return new ResponseEntity(modeloDadosAlterado, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
