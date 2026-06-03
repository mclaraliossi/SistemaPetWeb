package br.com.petweb.petweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String para, String assunto, String corpo) {
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(para);
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);
            mensagem.setFrom("seu-email@gmail.com"); // Mude isso!
            
            mailSender.send(mensagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }
}
