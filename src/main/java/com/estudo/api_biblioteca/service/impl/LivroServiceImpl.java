package com.estudo.api_biblioteca.service.impl;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.exception.exceptions.IsbnJaExistenteException;
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

     //   try {
            Livro livroSalvo = livroRepository.save(livro);
            return livroMapper.toDto(livroSalvo);
     //   } catch () {
       // throw new IsbnJaExistenteException(livroRequestDTO.getIsbn());
    }



    @Override
    public List<LivroResponseDTO> buscarTodosLivros() {
        List<Livro> livroList = livroRepository.findAll();

        return livroMapper.toDtoList(livroList);
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
