package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;
import com.estudo.api_biblioteca.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO toDTO(Usuario usuario);


}
