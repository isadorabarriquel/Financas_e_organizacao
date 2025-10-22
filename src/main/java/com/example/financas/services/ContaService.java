package com.example.financas.services;

import com.example.financas.repositories.RepositorioConta;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    private final RepositorioConta repositorioConta;

    public ContaService(
            RepositorioConta repositorioConta
    ) {
        this.repositorioConta = repositorioConta;
    }

}
