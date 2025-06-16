package com.estudo.api_biblioteca.controller;

import com.estudo.api_biblioteca.dto.request.LivroRequestDTO;
import com.estudo.api_biblioteca.dto.response.LivroResponseDTO;
import com.estudo.api_biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/livros")
public class LivroController {

    private final LivroService livroService;


    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criarLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO){
        LivroResponseDTO livroResponseDTO = livroService.criarLivro(livroRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(livroResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> buscarTodosLivros(){
        List<LivroResponseDTO> livroResponseDTOS = livroService.buscarTodosLivros();
        if (livroResponseDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(livroResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarLivroPorId(@PathVariable Long id){
        LivroResponseDTO livroResponseDTO = livroService.buscarLivroPorId(id);

        return ResponseEntity.ok().body(livroResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO livroRequestDTO){
        LivroResponseDTO livroResponseDTO = livroService.atualizarLivro(id, livroRequestDTO);

        return ResponseEntity.ok().body(livroResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }



}
