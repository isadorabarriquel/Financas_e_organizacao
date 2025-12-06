package com.example.financas.services;

import com.example.financas.models.Transacao;
import com.example.financas.models.Usuario;
import com.example.financas.repositories.RepositorioCategoria;
import com.example.financas.repositories.RepositorioTransacao;
import com.example.financas.repositories.RepositorioUsuario;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResumoMensalService {

    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTransacao repositorioTransacao;
    private final EmailService emailService;
    private final RepositorioCategoria repositorioCategoria;


    public ResumoMensalService(
            RepositorioUsuario repositorioUsuario,
            RepositorioTransacao repositorioTransacao,
            RepositorioCategoria repositorioCategoria,
            EmailService emailService
    ) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTransacao = repositorioTransacao;
        this.repositorioCategoria = repositorioCategoria;
        this.emailService = emailService;
    }

    // roda todo minuto só para teste
    @Scheduled(cron = "0 * * * * *")
    public void enviarRelatoriosMensais() {
        System.out.println("[ResumoMensal] Rodando em: " + LocalDateTime.now());

        LocalDate hoje = LocalDate.now();

        // mês anterior
        LocalDate inicioMesAnterior = hoje.minusMonths(1).withDayOfMonth(1);
        LocalDate fimMesAnterior = hoje.withDayOfMonth(1).minusDays(1);

        LocalDateTime inicio = inicioMesAnterior.atStartOfDay();
        LocalDateTime fim = fimMesAnterior.atTime(23, 59, 59);

        List<Usuario> usuarios = repositorioUsuario.findAll();

        for (Usuario usuario : usuarios) {

            // se o usuário não quer resumo, pula
            if (!usuario.isResumoMensal()) {
                continue;
            }

            // se não tem e-mail, pula
            if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
                continue;
            }

            UUID usuarioId = usuario.getId();

            List<Transacao> transacoes = repositorioTransacao
                    .findByUsuarioIdAndDataBetween(usuarioId, inicio, fim);

            // se não teve transação no mês, pula
            if (transacoes.isEmpty()) {
                continue;
            }

            //  CORREÇÃO: ignora transações sem categoria_id (null)
            List<Transacao> transacoesComCategoria = transacoes.stream()
                    .filter(t -> t.getCategoriaId() != null)
                    .toList();

            if (transacoesComCategoria.isEmpty()) {
                // se todas eram sem categoria, não há o que resumir
                continue;
            }

            // soma os valores por categoria
            Map<UUID, BigDecimal> gastosPorCategoria = transacoesComCategoria.stream()
                    .collect(Collectors.groupingBy(
                            Transacao::getCategoriaId,
                            Collectors.reducing(
                                    BigDecimal.ZERO,
                                    Transacao::getValor,
                                    BigDecimal::add
                            )
                    ));

            StringBuilder corpo = new StringBuilder();
            corpo.append("Olá ").append(usuario.getNome()).append(",\n\n");
            corpo.append("Este é seu resumo de gastos por categoria do mês ")
                    .append(inicioMesAnterior.getMonthValue())
                    .append("/")
                    .append(inicioMesAnterior.getYear())
                    .append(":\n\n");

            gastosPorCategoria.forEach((categoriaId, total) -> {
                /*corpo.append("- Categoria ID ").append(categoriaId)
                        .append(": R$ ")
                        .append(String.format("%.2f", total))
                        .append("\n");*/
                String nomeCategoria = repositorioCategoria.findById(categoriaId)
                        .map(c -> c.getNome())
                        .orElse("Categoria desconhecida");

                corpo.append("- ").append(nomeCategoria)
                        .append(": R$ ")
                        .append(String.format("%.2f", total))
                        .append("\n");

            });

            emailService.enviarEmail(
                    usuario.getEmail(),
                    "Seu resumo mensal de gastos",
                    corpo.toString()
            );
        }
    }
}
