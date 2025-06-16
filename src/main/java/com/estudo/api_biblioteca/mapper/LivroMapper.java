package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emprestimoList", ignore = true)
    Livro toEntity(LivroRequestDTO livroRequestDTO);

    LivroResponseDTO toDto(Livro livro);

    List<LivroResponseDTO> toDtoList(List<Livro> livros);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emprestimoList", ignore = true)
    void atualizarLivroFromDTO(LivroRequestDTO livroRequestDTO, @MappingTarget Livro livro);
}
