package com.estudo.api_biblioteca.dto.response;

import java.util.List;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;

    public UsuarioResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
