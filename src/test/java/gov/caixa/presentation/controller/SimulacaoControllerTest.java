package gov.caixa.presentation.controller;

import gov.caixa.application.usecase.BuscarHistoricoUseCase;
import gov.caixa.application.usecase.CriarSimulacaoUseCase;
import gov.caixa.domain.model.Simulacao;
import gov.caixa.presentation.dto.SimulacaoRequest;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@QuarkusTest
class SimulacaoControllerTest {

    @InjectMock
    CriarSimulacaoUseCase criarSimulacaoUseCase;

    @InjectMock
    BuscarHistoricoUseCase buscarHistoricoUseCase;

    @Test
    void deveCriarSimulacaoComSucesso() {
        SimulacaoRequest request = new SimulacaoRequest(
                123L,
                new BigDecimal("1000.00"),
                12,
                "CDB");
        Simulacao simulacao = new Simulacao(
                1L, 123L, "CDB Caixa", "CDB",
                new BigDecimal("1000.00"), 12,
                new BigDecimal("0.10"), new BigDecimal("1100.00"),
                Instant.now());

        when(criarSimulacaoUseCase.executar(eq(123L), any(BigDecimal.class), eq(12), eq("CDB")))
                .thenReturn(simulacao);

        given()
                .contentType("application/json")
                .body(request)
                .when().post("/simulacoes")
                .then()
                .statusCode(200)
                .body("produtoValidado.nome", equalTo("CDB Caixa"))
                .body("resultadoSimulacao.valorFinal", equalTo(1100.0f));
    }

    @Test
    void deveBuscarHistoricoComSucesso() {
        Simulacao simulacao = new Simulacao(
                1L, 123L, "CDB Caixa", "CDB",
                new BigDecimal("1000.00"), 12,
                new BigDecimal("0.10"), new BigDecimal("1100.00"),
                Instant.now());

        when(buscarHistoricoUseCase.executar(123L)).thenReturn(List.of(simulacao));

        given()
                .queryParam("clienteId", 123L)
                .when().get("/simulacoes")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].produto", equalTo("CDB Caixa"));
    }
}
