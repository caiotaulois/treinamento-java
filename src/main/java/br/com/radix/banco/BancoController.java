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
import java.util.stream.Collectors;

@Path("/banco")
public class BancoController {

    private static List<Conta> contas = new ArrayList<>();

    @GET
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContaDTO> listarContas() {
        return contas.stream().map(ContaDTO::new).collect(Collectors.toList());
    }

    @GET
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContaDTO obterConta(@PathParam("numero") Long numero) {

        Optional<Conta> contaEncontrada = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        if (contaEncontrada.isPresent()) {
            return new ContaDTO(contaEncontrada.get());
        }
        throw new NotFoundException();
    }

    @POST
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarConta(CriaContaDTO dto) {

        contas.add(dto.gerarConta());
        return Response.ok().build();
    }

    @PUT
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarConta(@PathParam("numero") Long numero, EditarContaDTO dto) {

        Optional<Conta> contaEditada = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        contaEditada.orElseThrow(NotFoundException::new).atualizarDados(dto.getCliente());
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
    public List<OperacaoDTO> listarOperacoes(@PathParam("numero") Long numeroConta) {

        Optional<Conta> contaEncontrada = contas.stream().filter(conta -> conta.getNumero().equals(numeroConta)).findFirst();
        if (contaEncontrada.isPresent()) {
            return contaEncontrada.get().getOperacoes().stream().map(OperacaoDTO::new).collect(Collectors.toList());
        }
        throw new NotFoundException();
    }

    @POST
    @Path("/operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarOperacao(RealizarOperacaoDTO dto) {
        Optional<Conta> contaOrigem = contas.stream().filter(conta -> conta.getNumero().equals(dto.getNumeroContaOrigem())).findFirst();
        Optional<Conta> contaDestino = contas.stream().filter(conta -> conta.getNumero().equals(dto.getNumeroContaDestino())).findFirst();

        if (!contaOrigem.isPresent()) {
            throw new NotFoundException();
        }
        if (contaDestino.isPresent()) {
            Operacao operacaoSaque = new Operacao(contaOrigem.get().getNumero(), contaDestino.get().getNumero(), -dto.getValor());
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacaoSaque));
            Operacao operacaoDeposito = new Operacao(contaOrigem.get().getNumero(), contaDestino.get().getNumero(), dto.getValor());
            contaDestino.ifPresent(conta -> conta.realizarOperacao(operacaoDeposito));
        } else {
            Operacao operacao = new Operacao(contaOrigem.get().getNumero(), null, dto.getValor());
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacao));
        }
        return Response.ok().build();
    }
}
