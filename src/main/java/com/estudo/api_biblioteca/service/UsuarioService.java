package com.estudo.api_biblioteca.service;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO);
    List<UsuarioResponseDTO> buscarTodosUsuarios();
    UsuarioResponseDTO buscarUsuarioPorId(Long id);
    UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO);
    void deletarUsuario(Long id);

}
