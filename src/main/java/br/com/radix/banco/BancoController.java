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

        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        throw new NotFoundException();
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

        Conta contaEditada = null;
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                contaEditada = conta;
            }
        }
        if (contaEditada == null) {
            throw new NotFoundException();
        }
        contaEditada.atualizarDados(requisicaoEditarConta.getCliente(), requisicaoEditarConta.getSaldo());
        return Response.ok().build();
    }

    @DELETE
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarConta(@PathParam("numero") Long numero) {

        Conta contaExcluida = null;
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                contaExcluida = conta;
            }
        }
        if (contaExcluida == null) {
            throw new NotFoundException();
        }
        contas.remove(contaExcluida);
        return Response.ok().build();
    }
}
