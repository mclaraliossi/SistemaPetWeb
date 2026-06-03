package br.com.petweb.petweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petweb.petweb.entity.PasswordResetToken;
import br.com.petweb.petweb.entity.Usuario;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    
    Optional<PasswordResetToken> findByToken(String token);
    
    void deleteByUsuario(Usuario usuario);
}
