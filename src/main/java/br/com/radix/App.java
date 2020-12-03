package br.com.radix;

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
        config.register(HelloWorld.class);
        JettyHttpContainerFactory.createServer(baseUri, config);
    }
}