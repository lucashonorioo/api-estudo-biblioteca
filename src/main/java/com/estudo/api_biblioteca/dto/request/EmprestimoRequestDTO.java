package com.estudo.api_biblioteca.dto.request;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class EmprestimoRequestDTO {

    @NotNull(message = "Id do usuario é obrigatorio")
    private Long usuarioId;

    @NotNull(message = "Id do livro é obrigatorio")
    private Long livroId;

    @NotNull(message = "Data de emprestimo é obrigatoria")
    @PastOrPresent(message = "Data emprestimo deve ser hoje ou no passado")
    private LocalDate dataEmprestimo;

    @NotNull(message = "Data de devolução prevista é obrigatoria")
    @Future(message = "Data de devolução deve ser no futuro")
    private LocalDate dataDevolucaoPrevista;



    public EmprestimoRequestDTO(Long usuarioId, Long livroId, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
}
