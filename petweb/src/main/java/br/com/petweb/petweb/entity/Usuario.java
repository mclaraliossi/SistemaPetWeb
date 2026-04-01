package br.com.petweb.petweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(nullable = false, length = 40)
    private String nomeUsuario;

    @Column(nullable = false, length = 40)
    private String emailUsuario;

    @Column(nullable = false, length = 11)
    private String telefoneUsuario;

    @Column(nullable = false, length = 11)
    private String cpfUsuario;

    @Column(nullable = false, length = 40)
    private String loginUsuario;

    @Column(nullable = false, length = 150)
    private String senhaUsuario;

    private String role = "ROLE_USER";



}
