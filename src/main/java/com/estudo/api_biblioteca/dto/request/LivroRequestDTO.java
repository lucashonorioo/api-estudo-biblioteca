package com.estudo.api_biblioteca.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class LivroRequestDTO {

    @NotBlank(message = "O titulo é obrigatorio")
    @Size(max = 100, message = "Máximo de 100 caracteres")
    private String titulo;

    @NotBlank(message = "O autor é obrigatorio")
    @Size(max = 50, message = "Máximo de 50 caracteres")
    private String autor;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private int quantidadeDisponivel;


    public LivroRequestDTO(String titulo, String autor, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }


}
