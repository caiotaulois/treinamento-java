package br.com.radix.banco;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/banco")
public class BancoController {

    @GET
    @Path("/conta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conta> listarContas() {

        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta("cliente 1", 1L, 10.0));
        contas.add(new Conta("cliente 2", 2L, 20.0));
        contas.add(new Conta("cliente 3", 3L, 30.0));
        return contas;
    }
}
