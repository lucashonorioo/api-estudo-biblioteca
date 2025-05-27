package com.estudo.api_biblioteca.dto.response;

import com.estudo.api_biblioteca.model.Livro;
import com.estudo.api_biblioteca.model.Usuario;

import java.time.LocalDate;

public class EmprestimoResponseDTO {

    private Long id;
    private String nomeUsuario;
    private String nomeLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    public EmprestimoResponseDTO(Long id, String nomeUsuario, String nomeLivro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.nomeLivro = nomeLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public Long getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }
}
