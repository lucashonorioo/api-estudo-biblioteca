package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "livroId", target = "livro.id")
    @Mapping(target = "id", ignore = true)
    Emprestimo toEntity(EmprestimoRequestDTO emprestimoRequestDTO);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "livro.id", target = "livroId")
    EmprestimoResponseDTO toDto(Emprestimo emprestimo);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "livro.id", target = "livroId")
    List<EmprestimoResponseDTO> toDtoList(List<Emprestimo> emprestimos);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "livroId", target = "livro.id")
    void atualizarEmprestimoFromDto(EmprestimoRequestDTO emprestimoRequestDTO, @MappingTarget Emprestimo emprestimo);
}
