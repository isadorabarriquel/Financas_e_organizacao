package com.example.financas.repositories;

import com.example.financas.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RepositorioTransacao extends JpaRepository<Transacao, UUID> {
    List<Transacao> findByUsuarioIdAndDataBetween(
            UUID usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
    boolean existsByCategoriaId(UUID categoriaId);

    boolean existsByContaId(UUID contaId);

}
