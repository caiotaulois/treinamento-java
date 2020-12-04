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
        contaEditada.orElseThrow(NotFoundException::new).atualizarDados(requisicaoEditarConta.getCliente());
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

    @GET
    @Path("/conta/{numero}/operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Operacao> listarOperacoes(@PathParam("numero") Long numeroConta) {

        Optional<Conta> contaEncontrada = contas.stream().filter(conta -> conta.getNumero().equals(numeroConta)).findFirst();
        if (contaEncontrada.isPresent()) {
            return contaEncontrada.get().getOperacoes();
        }
        throw new NotFoundException();
    }

    @POST
    @Path("/operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarOperacao(Operacao operacao) {
        Optional<Conta> contaOrigem = contas.stream().filter(conta -> conta.getNumero().equals(operacao.getNumeroContaOrigem())).findFirst();
        Optional<Conta> contaDestino = contas.stream().filter(conta -> conta.getNumero().equals(operacao.getNumeroContaDestino())).findFirst();

        if (!contaOrigem.isPresent()) {
            throw new NotFoundException();
        }
        if (contaDestino.isPresent()) {
            Operacao operacaoSaque = new Operacao(contaOrigem.get().getNumero(), contaDestino.get().getNumero(), -operacao.getValor());
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacaoSaque));
            Operacao operacaoDeposito = new Operacao(contaOrigem.get().getNumero(), contaDestino.get().getNumero(), operacao.getValor());
            contaDestino.ifPresent(conta -> conta.realizarOperacao(operacaoDeposito));
        } else {
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacao));
        }
        return Response.ok().build();
    }
}
