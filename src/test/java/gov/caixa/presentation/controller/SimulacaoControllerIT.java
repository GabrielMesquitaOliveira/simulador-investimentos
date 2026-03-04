package gov.caixa.presentation.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class SimulacaoControllerIT {

    private static final String VALID_PAYLOAD = """
            {
                "clienteId": 123,
                "valor": 10000.00,
                "prazoMeses": 12,
                "tipoProduto": "CDB"
            }
            """;

    @Test
    void postSimulacaoValida_deveRetornar200ComBodyCorreto() {
        given()
                .contentType(ContentType.JSON)
                .body(VALID_PAYLOAD)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(200)
                .body("produtoValidado.nome", not(emptyOrNullString()))
                .body("resultadoSimulacao.valorFinal", greaterThan(10000.0f))
                .body("resultadoSimulacao.prazoMeses", equalTo(12))
                .body("dataSimulacao", not(emptyOrNullString()));
    }

    @Test
    void postSimulacaoTipoProdutoInexistente_deveRetornar422() {
        String payload = """
                {
                    "clienteId": 1,
                    "valor": 1000.00,
                    "prazoMeses": 6,
                    "tipoProduto": "TIPO_INEXISTENTE"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(422)
                .body("message", containsString("TIPO_INEXISTENTE"));
    }

    @Test
    void postSimulacaoCamposInvalidos_deveRetornar400() {
        String payloadInvalido = """
                {
                    "clienteId": null,
                    "valor": -500,
                    "prazoMeses": 0,
                    "tipoProduto": ""
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payloadInvalido)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(400);
    }

    @Test
    void getHistorico_deveRetornar200ComLista() {
        // First create a simulation so there's history
        given().contentType(ContentType.JSON).body(VALID_PAYLOAD)
                .post("/simulacoes");

        given()
                .queryParam("clienteId", 123)
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].clienteId", equalTo(123));
    }
}
