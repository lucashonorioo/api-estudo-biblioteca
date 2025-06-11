package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {


    Livro toEntity(LivroRequestDTO livroRequestDTO);

    LivroResponseDTO toDto(Livro livro);

    List<LivroResponseDTO> toDtoList(List<Livro> livros);

    @Mapping(target = "id", ignore = true)
    void atualizarLivroFromDTO(LivroRequestDTO livroRequestDTO, @MappingTarget Livro livro);
}
