package com.cadastro.usuario.controller;

import com.cadastro.usuario.request.ModelDadosUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teste")
public class TestRotaController {

    @GetMapping
    public ResponseEntity test() {
        ModelDadosUsuario modelDadosUsuario = new ModelDadosUsuario();
        return new ResponseEntity(modelDadosUsuario, HttpStatus.ACCEPTED);
    }
}
