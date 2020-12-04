package br.com.radix.banco;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class OperacaoRequest {
    private Long numeroContaOrigem;
    private Long numeroContaDestino;
    private Double valor;

    public Long getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public Long getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public Double getValor() {
        return valor;
    }
}

@Path("/banco")
public class BancoController {

    private static List<Conta> contas = new ArrayList<>();

    @GET
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conta> listarContas() {
        return contas;
    }

    @GET
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conta obterConta(@PathParam("numero") Long numero) {

        Optional<Conta> contaEncontrada = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        return contaEncontrada.orElseThrow(NotFoundException::new);
    }

    @POST
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarConta(Conta conta) {

        contas.add(conta);
        return Response.ok().build();
    }

    @PUT
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarConta(@PathParam("numero") Long numero, Conta requisicaoEditarConta) {

        Optional<Conta> contaEditada = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        contaEditada.orElseThrow(NotFoundException::new).atualizarDados(requisicaoEditarConta.getCliente(), requisicaoEditarConta.getSaldo());
        return Response.ok().build();
    }

    @DELETE
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarConta(@PathParam("numero") Long numero) {

        Optional<Conta> contaExcluida = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        contas.remove(contaExcluida.orElseThrow(NotFoundException::new));
        return Response.ok().build();
    }

    @POST
    @Path("/operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarOperacao(OperacaoRequest operacaoRequest) {
        Optional<Conta> contaOrigem = contas.stream().filter(conta -> conta.getNumero().equals(operacaoRequest.getNumeroContaOrigem())).findFirst();
        Optional<Conta> contaDestino = contas.stream().filter(conta -> conta.getNumero().equals(operacaoRequest.getNumeroContaDestino())).findFirst();

        if (contaDestino.isPresent()) {
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(-operacaoRequest.getValor()));
            contaDestino.ifPresent(conta -> conta.realizarOperacao(operacaoRequest.getValor()));
        } else {
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacaoRequest.getValor()));
        }
        return Response.ok().build();
    }
}
