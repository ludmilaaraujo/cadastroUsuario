package com.cadastro.usuario.repository;

import com.cadastro.usuario.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
}
