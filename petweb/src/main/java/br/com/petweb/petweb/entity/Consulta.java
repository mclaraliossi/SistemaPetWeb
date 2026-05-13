package br.com.petweb.petweb.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConsulta;

    @Column(nullable = false, length = 10)
    private LocalDate dataConsulta;

    @Column(nullable = false, length = 10)
    private LocalTime horaConsulta;

    @Column(nullable = false, length = 100)
    private String observacoesConsulta;

    @Column(nullable = false, length = 9)
    private Double valorConsulta;

    @Column(nullable = false, length = 50)
    private String diagnosticoConsulta;

    @Column(nullable = false, length = 50)
    private String tratamentoConsulta;
}
