package br.com.petweb.petweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.PasswordResetToken;
import br.com.petweb.petweb.entity.Usuario;
import br.com.petweb.petweb.repository.PasswordResetTokenRepository;
import br.com.petweb.petweb.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
    private EmailService emailService; // ADICIONAR

  public Usuario save(Usuario usuario){
    // Criptográfa a senha antes de salvar
    usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
    return usuarioRepository.save(usuario);
  }

  public Usuario findById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
        
}

 public void solicitarRecuperacaoSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmailUsuario(email)
            .orElseThrow(() -> new RuntimeException("Usuário com este email não encontrado"));

        // Deletar token antigo se existir
        if (usuario.getPasswordResetToken() != null) {
          passwordResetTokenRepository.deleteByUsuario(usuario);
        }

        String token = UUID.randomUUID().toString();
        
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUsuario(usuario);
        resetToken.setDataExpiracao(LocalDateTime.now().plusHours(2));
        
        passwordResetTokenRepository.save(resetToken);

        // Enviar email
        String linkRecuperacao = "http://localhost:8080/usuarios/redefinir-senha?token=" + token;
        emailService.enviarEmail(email, 
            "Recuperação de Senha - PetWeb", 
            "Clique neste link para redefinir sua senha: " + linkRecuperacao + "\n\nO link expira em 2 horas.");
    }

    public void redefinirSenha(String token, String novaSenha) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.getDataExpiracao().isBefore(LocalDateTime.now())) {
            passwordResetTokenRepository.delete(resetToken);
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setSenhaUsuario(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

        passwordResetTokenRepository.delete(resetToken);
    }


}
