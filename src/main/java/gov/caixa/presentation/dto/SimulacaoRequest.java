package gov.caixa.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SimulacaoRequest(
        @NotNull(message = "clienteId é obrigatório") Long clienteId,

        @NotNull(message = "valor é obrigatório") @Positive(message = "valor deve ser positivo") BigDecimal valor,

        @Positive(message = "prazoMeses deve ser positivo") int prazoMeses,

        @NotBlank(message = "tipoProduto é obrigatório") String tipoProduto) {
}
