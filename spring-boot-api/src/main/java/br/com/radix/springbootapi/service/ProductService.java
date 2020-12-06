package br.com.radix.springbootapi.service;

import br.com.radix.springbootapi.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> list();

    Product get(Long id);

}
