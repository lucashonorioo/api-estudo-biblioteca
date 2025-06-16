package com.estudo.api_biblioteca.dto.response;

import java.util.ArrayList;
import java.util.List;

public class LivroResponseDTO {

    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private int quantidadeDisponivel;


    public LivroResponseDTO(Long id, String isbn, String titulo, String autor, int quantidadeDisponivel) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Long getId() {
        return id;
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

}
