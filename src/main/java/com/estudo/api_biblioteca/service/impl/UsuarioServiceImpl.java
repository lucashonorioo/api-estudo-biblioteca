package com.estudo.api_biblioteca.service.impl;

import com.estudo.api_biblioteca.dto.request.UsuarioRequestDTO;
import com.estudo.api_biblioteca.dto.response.UsuarioResponseDTO;
import com.estudo.api_biblioteca.exception.exceptions.BusinessException;
import com.estudo.api_biblioteca.exception.exceptions.ResourceNotFoundException;
import com.estudo.api_biblioteca.mapper.UsuarioMapper;
import com.estudo.api_biblioteca.model.Usuario;
import com.estudo.api_biblioteca.repository.UsuarioRepository;
import com.estudo.api_biblioteca.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper  usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuarioSalvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarioMapper.toDtoList(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        if(id == null || id <= 0){
            throw new BusinessException("O id precisa ser positivo e não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuario", id));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        if(id == null || id <= 0){
            throw new BusinessException("O id precisa ser positivo e não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuario", id));

        usuarioMapper.atualizarUsuarioFromDto(usuarioRequestDTO, usuario);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioSalvo);
    }

    @Override
    @Transactional
    public void deletarUsuario(Long id) {
        if(id == null || id <= 0){
            throw new BusinessException("O id precisa ser positivo e não pode ser nulo");
        }
        Usuario usuario = usuarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuario", id));
        usuarioRepository.deleteById(id);
    }
}
