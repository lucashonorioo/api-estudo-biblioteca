package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "livroId", target = "livro.id")
    Emprestimo toEntity(EmprestimoRequestDTO emprestimoRequestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "livro.id", target = "livroId")
    EmprestimoResponseDTO toDTO(Emprestimo emprestimo);

}
