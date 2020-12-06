package br.com.radix.springbootapi.service;

import br.com.radix.springbootapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> list() {
        return Collections.emptyList();
    }

    @Override
    public Product get(Long id) {
        return null;
    }
}
