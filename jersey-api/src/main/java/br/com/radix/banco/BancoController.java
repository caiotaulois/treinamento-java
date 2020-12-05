package br.com.radix.banco;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/banco")
public class BancoController {

    private BancoService bancoService = new BancoService();

    @GET
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContaDTO> listarContas() {
        List<Conta> contas = bancoService.listarContas();
        return contas.stream().map(ContaDTO::new).collect(Collectors.toList());
    }

    @GET
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContaDTO obterConta(@PathParam("numero") Long numero) {

        Conta conta = bancoService.obterConta(numero);
        return new ContaDTO(conta);
    }

    @POST
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarConta(CriaContaDTO dto) {

        bancoService.criarConta(dto.gerarConta());
        return Response.ok().build();
    }

    @PUT
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarConta(@PathParam("numero") Long numero, EditarContaDTO dto) {

        bancoService.editarConta(numero, dto.getCliente());
        return Response.ok().build();
    }

    @DELETE
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarConta(@PathParam("numero") Long numero) {

        bancoService.deletarConta(numero);
        return Response.ok().build();
    }

    @GET
    @Path("/conta/{numero}/operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OperacaoDTO> listarOperacoes(@PathParam("numero") Long numeroConta) {

        List<Operacao> operacoes = bancoService.listarOperacoes(numeroConta);
        return operacoes.stream().map(OperacaoDTO::new).collect(Collectors.toList());
    }

    @POST
    @Path("/operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarOperacao(RealizarOperacaoDTO dto) {
        bancoService.realizarOperacao(dto.getNumeroContaOrigem(), dto.getNumeroContaDestino(), dto.getValor());
        return Response.ok().build();
    }
}
