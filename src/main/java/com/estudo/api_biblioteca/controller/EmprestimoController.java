package com.estudo.api_biblioteca.controller;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    ResponseEntity<EmprestimoResponseDTO> criarEmprestimo(@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        EmprestimoResponseDTO emprestimoResponseDTO = emprestimoService.criarEmprestimo(emprestimoRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emprestimoResponseDTO.getId()).toUri();

        return ResponseEntity.created(location).body(emprestimoResponseDTO);
    }

    @GetMapping
    ResponseEntity<List<EmprestimoResponseDTO>> buscarTodosEmprestimos(){
        List<EmprestimoResponseDTO> emprestimoResponseDTOS = emprestimoService.buscarTodosEmprestimos();

        return ResponseEntity.ok().body(emprestimoResponseDTOS);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<EmprestimoResponseDTO> buscarEmprestimoPorId(@PathVariable Long id){
        EmprestimoResponseDTO emprestimoResponseDTO = emprestimoService.buscarEmprestimoPorId(id);
        return ResponseEntity.ok().body(emprestimoResponseDTO);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<EmprestimoResponseDTO> atualizarEmprestimo(@PathVariable Long id, @Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        EmprestimoResponseDTO emprestimoResponseDTO = emprestimoService.atualizarEmprestimo(id, emprestimoRequestDTO);
        return ResponseEntity.ok().body(emprestimoResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id){
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

}
