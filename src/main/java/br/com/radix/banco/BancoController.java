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
import java.util.ArrayList;
import java.util.List;

@Path("/banco")
public class BancoController {

    @GET
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conta> listarContas() {
        return this.contas;
    }

    @GET
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conta obterConta(@PathParam("numero") Long numero) {

        Conta conta = new Conta("cliente teste", numero, 0.0);
        return conta;
    }

    @POST
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarConta() {

        return null;
    }

    @PUT
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarConta(@PathParam("numero") Long numero) {

        return null;
    }

    @DELETE
    @Path("/conta/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarConta(@PathParam("numero") Long numero) {

        return null;
    }
}
