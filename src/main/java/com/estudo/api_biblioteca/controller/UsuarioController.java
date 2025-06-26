package com.estudo.api_biblioteca.controller;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;
import com.estudo.api_biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.criarUsuario(usuarioRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioResponseDTO.getId()).toUri();
        return ResponseEntity.created(location).body(usuarioResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios(){
        List<UsuarioResponseDTO> buscarTodosUsuarios= usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok().body(buscarTodosUsuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(usuarioResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.atualizarUsuario(id, usuarioRequestDTO);
        return ResponseEntity.ok().body(usuarioResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
