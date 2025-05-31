package com.estudo.api_biblioteca.service.impl;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.mapper.LivroMapper;
import com.estudo.api_biblioteca.model.Livro;
import com.estudo.api_biblioteca.repository.LivroRepository;
import com.estudo.api_biblioteca.service.LivroService;

import java.util.List;

public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroServiceImpl(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }


    @Override
    public LivroResponseDTO criarLivro(LivroRequestDTO livroRequestDTO) {
        Livro livro = livroMapper.toEntity(livroRequestDTO);

        if(livroRequestDTO.getIsbn() == null || livroRequestDTO.getIsbn().isEmpty()){
            throw new IllegalArgumentException("O Isb não pode ser vazio");
        }
        if(livroRequestDTO.getTitulo() == null || livroRequestDTO.getTitulo().isEmpty()){
            throw new IllegalArgumentException("O titulo não pode ser vazio");
        }
        if(livroRequestDTO.getAutor() == null || livroRequestDTO.getAutor().isEmpty()){
            throw new IllegalArgumentException("O titulo não pode ser vazio");
        }
        if(livroRequestDTO.getQuantidadeDisponivel() < 0){
            throw new IllegalArgumentException("A quantidade não pode ser menor que zero");
        }
        
        Livro livroSalvo = livroRepository.save(livro);


        return livroMapper.toDto(livroSalvo);
    }


    @Override
    public List<LivroRequestDTO> buscarTodosLivros() {
        return List.of();
    }

    @Override
    public LivroResponseDTO buscarLivroPorId(Long id) {
        return null;
    }

    @Override
    public LivroResponseDTO atualizarLivro(Long id, LivroResponseDTO livroResponseDTO) {
        return null;
    }

    @Override
    public void deletarLivro(Long id) {

    }
}
