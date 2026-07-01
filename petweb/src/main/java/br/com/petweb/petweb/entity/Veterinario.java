package br.com.petweb.petweb.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVeterinario;

    @Column(nullable = false, length = 40)
    private String nomeVeterinario;

    @Column(nullable = false, length = 100)
    private String especialidadeVeterinario;

    @Column(nullable = false, length = 15)
    private String telefoneVeterinario;

    @Column(nullable = false, length = 6)
    private Integer crmVeterinario;

    @Column(nullable = false, length = 14)
    private String cpfVeterinario;

    @OneToMany(mappedBy = "veterinario")
    private List<Consulta> consulta;

}
