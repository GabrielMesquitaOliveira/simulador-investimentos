package gov.caixa.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "simulacoes")
@Getter
@Setter
@NoArgsConstructor
public class SimulacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "produto_nome", nullable = false)
    private String produtoNome;

    @Column(name = "tipo_produto", nullable = false)
    private String tipoProduto;

    @Column(name = "valor_investido", nullable = false)
    private BigDecimal valorInvestido;

    @Column(name = "prazo_meses", nullable = false)
    private int prazoMeses;

    @Column(name = "rentabilidade_aplicada", nullable = false)
    private BigDecimal rentabilidadeAplicada;

    @Column(name = "valor_final", nullable = false)
    private BigDecimal valorFinal;

    @Column(name = "data_simulacao", nullable = false)
    private Instant dataSimulacao;
}
