package br.com.radix.springbootapi.controller;

import br.com.radix.springbootapi.model.Product;
import br.com.radix.springbootapi.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Product list(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping()
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }
}
