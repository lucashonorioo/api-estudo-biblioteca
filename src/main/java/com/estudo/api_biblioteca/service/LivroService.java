package com.estudo.api_biblioteca.service;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;


import java.util.List;

public interface LivroService {

    LivroResponseDTO criarLivro(LivroRequestDTO livroRequestDTO);
    List<LivroRequestDTO> buscarTodosLivros();
    LivroResponseDTO buscarLivroPorId(Long id);
    LivroResponseDTO atualizarLivro(Long id, LivroResponseDTO livroResponseDTO);
    void deletarLivro(Long id);

}
