package br.com.radix;

import br.com.radix.banco.BancoController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8181).build();
        ResourceConfig config = new ResourceConfig();
        config.register(BancoController.class);
        config.register(JacksonFeature.class);
        JettyHttpContainerFactory.createServer(baseUri, config);
    }
}