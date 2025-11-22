package com.example.financas.services;

import com.example.financas.dtos.usuario.UsuarioRequestDTO;
import com.example.financas.dtos.usuario.UsuarioResponseDTO;
import com.example.financas.mappers.UsuarioMapper;
import com.example.financas.models.Usuario;
import com.example.financas.repositories.RepositorioUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class UsuarioService {
    private final RepositorioUsuario repositorioUsuario;

    public UsuarioService(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<UsuarioResponseDTO> getAllUsuarios(
            int paginaAtual,
            int tamanhoPagina,
            String nome) {
        Pageable pageable = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome").ascending());
        Page<Usuario> page = repositorioUsuario.findAll(pageable);
        /*return page.stream().map(UsuarioMapper::toDto).toList();*/

        Stream<Usuario> stream = page.stream();

        if (nome != null && !nome.isBlank()) {
            String nomeUpper = nome.toUpperCase();
            stream = stream.filter(u -> u.getNome() != null &&
                    u.getNome().toUpperCase().contains(nomeUpper));
        }
        return stream
                .map(UsuarioMapper::toDto)
                .toList();
    }

    public UsuarioResponseDTO getUsuarioPorId(UUID id) {
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário " + id +" não encontrado"));

        return UsuarioMapper.toDto(usuario);
    }

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCriadoEm(LocalDateTime.now());
        usuario.setAtualizadoEm(LocalDateTime.now());

        Usuario salvo = repositorioUsuario.save(usuario);
        return UsuarioMapper.toDto(salvo);
    }

    public UsuarioResponseDTO updateUsuario(UUID id, UsuarioRequestDTO dto) {
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setAtualizadoEm(LocalDateTime.now());

        Usuario atualizado = repositorioUsuario.save(usuario);
        return UsuarioMapper.toDto(atualizado);
    }

    public void deleteUsuario(UUID id) {
        if (!repositorioUsuario.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        repositorioUsuario.deleteById(id);
    }
}
