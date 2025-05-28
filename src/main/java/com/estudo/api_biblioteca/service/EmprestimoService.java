package com.estudo.api_biblioteca.service;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.model.Emprestimo;
import com.estudo.api_biblioteca.model.Livro;
import com.estudo.api_biblioteca.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoService {

    EmprestimoResponseDTO criarEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO);
    List<EmprestimoResponseDTO> listarEmprestimos();
    EmprestimoResponseDTO buscarEmprestimoPorId(Long id);
    EmprestimoResponseDTO atualizarEmprestimo(Long id, EmprestimoRequestDTO emprestimoAtualizadoDTO);
    void deletarEmprestimo(Long id);

}
