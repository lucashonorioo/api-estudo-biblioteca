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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Transactional
    @Override
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


        return emprestimoMapper.toDTO(emprestimoSalvo);
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @Override
    public Emprestimo buscarEmprestimoPorId(Long id) {
        if(id == null || id <= 0){
            throw new IllegalArgumentException("O id do emprestimo deve ser positivo");
        }
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Esse emprestimo não existe:", id));
        return emprestimo;
    }
    @Transactional
    @Override
    public Emprestimo atualizarEmprestimo(Long id, Emprestimo emprestimoAtualizado) {
        if(id == null || id <= 0){
            throw new IllegalArgumentException("O id do emprestimo deve ser positivo");
        }
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("O emprestimo não existe: ", id));

        if(emprestimoAtualizado.getDataDevolucaoPrevista() != null
                && emprestimoAtualizado.getDataEmprestimo() != null
                && emprestimoAtualizado.getDataDevolucaoPrevista().isBefore(emprestimoAtualizado.getDataEmprestimo())){
            throw new BusinessException("Data de devolução presvista não pode ser antes da data de emprestimo");
        }

        if(emprestimoAtualizado.getDataEmprestimo() != null){
            emprestimo.setDataEmprestimo(emprestimoAtualizado.getDataEmprestimo());
        }
        if(emprestimoAtualizado.getDataDevolucaoPrevista() != null){
            emprestimo.setDataDevolucaoPrevista(emprestimoAtualizado.getDataDevolucaoPrevista());
        }
        if(emprestimoAtualizado.getDataDevolucaoReal() != null){
            emprestimo.setDataDevolucaoReal(emprestimoAtualizado.getDataDevolucaoReal());
        }


        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    @Override
    public void deletarEmprestimo(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do empréstimo inválido: " + id);
        }
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O  emprestimo não existe", id));
        emprestimoRepository.deleteById(id);
    }
}
