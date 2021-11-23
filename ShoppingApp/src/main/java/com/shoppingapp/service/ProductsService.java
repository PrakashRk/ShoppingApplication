package com.shoppingapp.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingapp.model.Products;
import com.shoppingapp.repository.ProductsRepository;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<Products>();
        productsRepository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public void saveOrUpdate(Products mvoie) {
    	productsRepository.save(mvoie);
    }
    
    public Products getProductByCode(String pCode) {
    	return productsRepository.getProductByCode(pCode);
    }

}