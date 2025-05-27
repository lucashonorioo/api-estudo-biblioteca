package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;
import com.estudo.api_biblioteca.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO){
        return new Usuario(
                usuarioRequestDTO.getNome()
        );
    }

    public static UsuarioResponseDTO usuarioResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome()
        );
    }

}
