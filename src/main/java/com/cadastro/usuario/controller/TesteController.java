package com.cadastro.usuario.controller;

import com.cadastro.usuario.entity.UsuarioEntity;
import com.cadastro.usuario.repository.UsuarioRepository;
import com.cadastro.usuario.request.ModelDadosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
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
        modelDadosUsuario.setEndereco(usuarioEntity.getNome());
        modelDadosUsuario.setCpf(usuarioEntity.getCpf());

        return modelDadosUsuario;
    }

    @PostMapping("/dadosusuario")
    public UsuarioEntity usuarioResposta(@RequestBody ModelDadosUsuario usuario) {
        return usuarioRepository.save(converteRequestEmEntidade(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> usuarioResponse(@PathVariable long id) {
        return ResponseEntity.ok(usuarioRepository.findById(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody ModelDadosUsuario usuario) {
        return usuarioRepository.findById(id).map(record -> {
            record.setNome(usuario.getNome());
            record.setEndereco(usuario.getEndereco());
            record.setCpf(usuario.getCpf());
            UsuarioEntity updated = usuarioRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }


}
