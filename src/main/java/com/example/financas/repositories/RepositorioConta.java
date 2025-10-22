package com.example.financas.repositories;

import com.example.financas.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RepositorioConta extends JpaRepository<Conta, UUID> {
}
