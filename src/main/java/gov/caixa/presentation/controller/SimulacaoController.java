package gov.caixa.presentation.controller;

import gov.caixa.application.usecase.BuscarHistoricoUseCase;
import gov.caixa.application.usecase.BuscarResumoUseCase;
import gov.caixa.application.usecase.CriarSimulacaoUseCase;
import gov.caixa.presentation.dto.HistoricoItemResponse;
import gov.caixa.presentation.dto.ResumoResponse;
import gov.caixa.presentation.dto.SimulacaoRequest;
import gov.caixa.presentation.dto.SimulacaoResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/simulacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacaoController {

    private final CriarSimulacaoUseCase criarSimulacaoUseCase;
    private final BuscarHistoricoUseCase buscarHistoricoUseCase;
    private final BuscarResumoUseCase buscarResumoUseCase;

    public SimulacaoController(CriarSimulacaoUseCase criarSimulacaoUseCase,
            BuscarHistoricoUseCase buscarHistoricoUseCase,
            BuscarResumoUseCase buscarResumoUseCase) {
        this.criarSimulacaoUseCase = criarSimulacaoUseCase;
        this.buscarHistoricoUseCase = buscarHistoricoUseCase;
        this.buscarResumoUseCase = buscarResumoUseCase;
    }

    @POST
    @Transactional
    public Response criar(@Valid SimulacaoRequest request) {
        var resultado = criarSimulacaoUseCase.executar(
                request.clienteId(),
                request.valor(),
                request.prazoMeses(),
                request.tipoProduto());

        return Response.ok(SimulacaoResponse.from(resultado.simulacao(), resultado.produto())).build();
    }

    @GET
    public List<HistoricoItemResponse> historico(@QueryParam("clienteId") Long clienteId) {
        return buscarHistoricoUseCase.executar(clienteId)
                .stream()
                .map(HistoricoItemResponse::from)
                .toList();
    }

    @GET
    @Path("/resumo")
    public ResumoResponse resumo(@QueryParam("clienteId") Long clienteId) {
        return ResumoResponse.from(buscarResumoUseCase.executar(clienteId));
    }
}
