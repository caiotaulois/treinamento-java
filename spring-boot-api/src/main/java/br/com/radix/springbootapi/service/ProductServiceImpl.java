package br.com.radix.springbootapi.service;

import br.com.radix.springbootapi.exception.ProductNotFoundException;
import br.com.radix.springbootapi.model.Product;
import br.com.radix.springbootapi.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> list() {
        return repository.findAll();
    }

    @Override
    public Product get(Long id) {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }
}
