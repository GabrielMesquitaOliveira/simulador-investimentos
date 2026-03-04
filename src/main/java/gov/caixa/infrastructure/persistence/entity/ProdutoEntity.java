package gov.caixa.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "tipo_produto", nullable = false)
    private String tipoProduto;

    @Column(name = "rentabilidade_anual", nullable = false)
    private BigDecimal rentabilidadeAnual;

    @Column(nullable = false)
    private String risco;

    @Column(name = "prazo_min_meses", nullable = false)
    private int prazoMinMeses;

    @Column(name = "prazo_max_meses", nullable = false)
    private int prazoMaxMeses;

    @Column(name = "valor_min", nullable = false)
    private BigDecimal valorMin;

    @Column(name = "valor_max", nullable = false)
    private BigDecimal valorMax;
}
