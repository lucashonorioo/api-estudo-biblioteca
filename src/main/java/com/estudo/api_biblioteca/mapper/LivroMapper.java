package com.estudo.api_biblioteca.mapper;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroMapper {

    public static Livro toEntity(LivroRequestDTO dto){
        return new Livro(
                dto.getTitulo(),
                dto.getAutor(),
                dto.getQuantidadeDisponivel()
        );
    }

    public static LivroResponseDTO toDTO(Livro livro){
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getQuantidadeDisponivel()


        );
    }

}
