package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.model.Emprestimo;
import com.estudo.api_biblioteca.model.Livro;
import com.estudo.api_biblioteca.model.Usuario;

public class EmprestimoMapper {

    public static Emprestimo toEntity(EmprestimoRequestDTO emprestimoRequestDTO, Usuario usuario, Livro livro){
        return new Emprestimo(
                usuario,
                livro,
                emprestimoRequestDTO.getDataEmprestimo(),
                emprestimoRequestDTO.getDataDevolucaoPrevista()
        );
    }

    public static EmprestimoResponseDTO emprestimoResponseDTO(Emprestimo emprestimo){
        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                UsuarioMapper.usuarioResponseDTO(emprestimo.getUsuario()).getNome(),
                LivroMapper.toDTO(emprestimo.getLivro()).getTitulo(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucaoPrevista(),
                emprestimo.getDataDevolucaoReal()
        );
    }

}
