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
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduto;

    @Column(nullable = false, length = 90)
    private String descricaoProduto;

    private Double valorProduto;

    @Column(nullable = false, length = 10)
    private String unidadeProduto;

    @Column(nullable = false, length = 20)
    private String marcaProduto;

    @OneToMany(mappedBy = "produto")
    private List<ItemDoPedido> itens;
    
}
