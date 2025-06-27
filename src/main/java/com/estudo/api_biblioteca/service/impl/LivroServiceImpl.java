package com.estudo.api_biblioteca.service.impl;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.exception.exceptions.BusinessException;
import com.estudo.api_biblioteca.exception.exceptions.IsbnJaExistenteException;
import com.estudo.api_biblioteca.exception.exceptions.ResourceNotFoundException;
import com.estudo.api_biblioteca.mapper.LivroMapper;
import com.estudo.api_biblioteca.model.Livro;
import com.estudo.api_biblioteca.repository.LivroRepository;
import com.estudo.api_biblioteca.service.LivroService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroServiceImpl(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }


    @Override
    @Transactional
    public LivroResponseDTO criarLivro(LivroRequestDTO livroRequestDTO) {
        Livro livro = livroMapper.toEntity(livroRequestDTO);
        try {
            Livro livroSalvo = livroRepository.save(livro);
            return livroMapper.toDto(livroSalvo);
        } catch (DataIntegrityViolationException e) {
            throw new IsbnJaExistenteException(livroRequestDTO.getIsbn());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarTodosLivros() {
        List<Livro> livroList = livroRepository.findAll();

        return livroMapper.toDtoList(livroList);
    }

    @Override
    @Transactional(readOnly = true)
    public LivroResponseDTO buscarLivroPorId(Long id) {
        if(id == null || id <= 0){
            throw new BusinessException("O ID deve ser positvo e não nulo");
        }
        Livro livro = livroRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Livro", id));
        return livroMapper.toDto(livro);
    }

    @Override
    @Transactional
    public LivroResponseDTO atualizarLivro(Long id, LivroRequestDTO livroRequestDTO) {
        if(id == null || id <= 0){
            throw new BusinessException("O ID deve ser positvo e não nulo");
        }
        Livro livro = livroRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Livro",  id));

        livroMapper.atualizarLivroFromDTO(livroRequestDTO, livro);

        Livro livroSalvo = livroRepository.save(livro);
        return livroMapper.toDto(livroSalvo);
    }

    @Override
    @Transactional
    public void deletarLivro(Long id) {
        if(id == null || id <= 0){
            throw new BusinessException("O ID deve ser positvo e não nulo");
        }
        Livro livro = livroRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Livro", id));
        livroRepository.deleteById(id);
    }
}
