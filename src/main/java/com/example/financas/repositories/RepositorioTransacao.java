package com.example.financas.repositories;

import com.example.financas.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RepositorioTransacao extends JpaRepository<Transacao, UUID> {
}
