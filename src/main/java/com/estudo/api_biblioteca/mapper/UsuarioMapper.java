package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;
import com.estudo.api_biblioteca.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emprestimoList", ignore = true)
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO toDto(Usuario usuario);

    List<UsuarioResponseDTO> toDtoList(List<Usuario> usuarioList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emprestimoList", ignore = true)
    void atualizarUsuarioFromDto(UsuarioRequestDTO usuarioRequestDTO, @MappingTarget Usuario usuario);


}
