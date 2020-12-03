package br.com.radix.banco;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public Response listarContas() {

        List<Integer> numeroContas = new ArrayList<>();
        numeroContas.add(1);
        numeroContas.add(2);
        numeroContas.add(3);
        return Response.ok(numeroContas).build();
    }
}
