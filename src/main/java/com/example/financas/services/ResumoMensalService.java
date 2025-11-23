package com.example.financas.services;

import com.example.financas.models.Transacao;
import com.example.financas.models.Usuario;
import com.example.financas.repositories.RepositorioTransacao;
import com.example.financas.repositories.RepositorioUsuario;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    public ResumoMensalService(
            RepositorioUsuario repositorioUsuario,
            RepositorioTransacao repositorioTransacao,
            EmailService emailService
    ) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTransacao = repositorioTransacao;
        this.emailService = emailService;
    }

    // seg - min - hora - dia_do_mes - mes -dia_da_semana
    @Scheduled(cron = "0 0 8 1 * *")
    public void enviarRelatoriosMensais() {

        LocalDate hoje = LocalDate.now();

        LocalDate inicioMesAnterior = hoje.minusMonths(1).withDayOfMonth(1);
        LocalDate fimMesAnterior = hoje.withDayOfMonth(1).minusDays(1);

        LocalDateTime inicio = inicioMesAnterior.atStartOfDay();
        LocalDateTime fim = fimMesAnterior.atTime(23, 59, 59);

        List<Usuario> usuarios = repositorioUsuario.findAll();

        for (Usuario usuario : usuarios) {
            //envia p quem ativou o resumo
            if (!usuario.isResumoMensal()) {
                continue;
            }
            if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
                continue;
            }

            UUID usuarioId = usuario.getId();

            //busca as transac. do mes anterior por usuario
            List<Transacao> transacoes = repositorioTransacao
                    .findByUsuarioIdAndDataBetween(usuarioId, inicio, fim);

            if (transacoes.isEmpty()) {
                continue;
            }

            //agrupa por id de categ. e soma valores
            Map<UUID, Double> gastosPorCategoria = transacoes.stream()
                    .collect(Collectors.groupingBy(
                            Transacao::getCategoriaId,
                            Collectors.reducing(
                                    0.0,             // valor inic.
                                    Transacao::getValor,
                                    Double::sum
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
                corpo.append("- Categoria ID ").append(categoriaId)
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
