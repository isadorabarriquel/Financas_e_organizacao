package com.example.financas.repositories;

import com.example.financas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RepositorioUsuario extends JpaRepository<Usuario, UUID> {
}
