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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAnimal;

    @Column(nullable = false, length = 40)
    private String nomeAnimal;

    @Column(nullable = false, length = 20)
    private String racaAnimal;

    @Column(nullable = false, length = 15)
    private String porteAnimal;

    @Column(nullable = false, length = 50)
    private String pesoAnimal;


}
