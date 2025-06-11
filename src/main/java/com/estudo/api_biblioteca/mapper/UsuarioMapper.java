package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;
import com.estudo.api_biblioteca.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO toDto(Usuario usuario);

    List<UsuarioResponseDTO> toDtoList(List<Usuario> usuarioList);

    @Mapping(target = "id", ignore = true)
    void atualizarUsuarioFromDto(UsuarioRequestDTO usuarioRequestDTO, Usuario usuario);


}
