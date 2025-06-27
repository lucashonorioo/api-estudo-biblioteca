package com.estudo.api_biblioteca.service.impl;

import com.estudo.api_biblioteca.dto.request.EmprestimoRequestDTO;
import com.estudo.api_biblioteca.dto.response.EmprestimoResponseDTO;
import com.estudo.api_biblioteca.exception.exceptions.BusinessException;
import com.estudo.api_biblioteca.exception.exceptions.ResourceNotFoundException;
import com.estudo.api_biblioteca.mapper.EmprestimoMapper;
import com.estudo.api_biblioteca.model.Emprestimo;
import com.estudo.api_biblioteca.model.Livro;
import com.estudo.api_biblioteca.model.Usuario;
import com.estudo.api_biblioteca.repository.EmprestimoRepository;
import com.estudo.api_biblioteca.repository.LivroRepository;
import com.estudo.api_biblioteca.repository.UsuarioRepository;
import com.estudo.api_biblioteca.service.EmprestimoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprestimoMapper emprestimoMapper;



    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, UsuarioRepository usuarioRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    private Usuario buscarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario: ", id));
        return usuario;
    }

    private Livro buscarLivro(Long id){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro: " ,id));
        return livro;
    }

    private void validarDisponibilidade(Livro livro){
        if(livro.getQuantidadeDisponivel() <= 0){
            throw new BusinessException("Livro não disponivel para emprestimo");
        }
    }


    @Override
    @Transactional
    public EmprestimoResponseDTO criarEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {

        if(emprestimoRequestDTO.getDataDevolucaoPrevista().isBefore(emprestimoRequestDTO.getDataEmprestimo())){
            throw new BusinessException("Data de devolução invalida");
        }

        Usuario usuario = buscarUsuario(emprestimoRequestDTO.getUsuarioId());
        Livro livro = buscarLivro(emprestimoRequestDTO.getLivroId());

        validarDisponibilidade(livro);

        Emprestimo novoEmprestimo = emprestimoMapper.toEntity(emprestimoRequestDTO);
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setLivro(livro);

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() -1);
        livroRepository.save(livro);

        Emprestimo emprestimoSalvo = emprestimoRepository.save(novoEmprestimo);


        return emprestimoMapper.toDto(emprestimoSalvo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> buscarTodosEmprestimos() {
        List<Emprestimo> emprestimoList = emprestimoRepository.findAll();

        return emprestimoMapper.toDtoList(emprestimoList);
    }

    @Override
    @Transactional(readOnly = true)
    public EmprestimoResponseDTO buscarEmprestimoPorId(Long id) {
        if(id == null || id <= 0){
            throw new BusinessException("O id deve ser positivo e não nulo");
        }
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Esse emprestimo não existe:", id));
        return emprestimoMapper.toDto(emprestimo);
    }


    @Override
    @Transactional
    public EmprestimoResponseDTO atualizarEmprestimo(Long id, EmprestimoRequestDTO emprestimoRequestDTO) {
        if(id == null || id <= 0){
            throw new BusinessException("O id deve ser positivo e não nulo");
        }
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("O emprestimo não existe: ", id));


        emprestimoMapper.atualizarEmprestimoFromDto(emprestimoRequestDTO, emprestimo);

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        return emprestimoMapper.toDto(emprestimoSalvo);
    }


    @Override
    @Transactional
    public void deletarEmprestimo(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("O id deve ser positivo e não nulo");
        }
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O  emprestimo não existe", id));
        emprestimoRepository.deleteById(id);
    }
}
