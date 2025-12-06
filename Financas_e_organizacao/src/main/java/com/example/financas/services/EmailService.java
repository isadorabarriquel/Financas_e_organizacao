package com.example.financas.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // E-mail que vai aparecer como REMETENTE (vem do application.properties)
    @Value("${spring.mail.username}")
    private String remetente;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(String para, String assunto, String texto) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(remetente);  // de quem está enviando (config do properties)
        mensagem.setTo(para);         // pra quem vai (e-mail do usuário no banco)
        mensagem.setSubject(assunto);
        mensagem.setText(texto);

        mailSender.send(mensagem);
    }
}
