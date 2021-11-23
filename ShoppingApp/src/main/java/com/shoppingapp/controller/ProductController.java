package com.shoppingapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.pojo.ProductDataPojo;
import com.shoppingapp.pojo.ProductPojo;
import com.shoppingapp.service.ProductsService;

@Controller
public class ProductController {

    @Autowired
    ProductsService productsService;

    @GetMapping("/testApi")
    private @ResponseBody String testApi() throws Exception
    {
    	List<ProductPojo>productPojoList = new ArrayList<ProductPojo>();
    	ProductDataPojo productDataPojo = new ProductDataPojo();
  
    	productPojoList.add(new ProductPojo("BRD",10));
    	productPojoList.add(new ProductPojo("MLK",10));
    	productPojoList.add(new ProductPojo("BTR",10));
    	productDataPojo.setProductPojoList(productPojoList);
    	String jsonData = new ObjectMapper().writeValueAsString(productDataPojo);
    	RestTemplate restTemplate = new RestTemplate();
    	String response = restTemplate.postForObject( "http://localhost:8080/products/checkout", jsonData , String.class );
        return response;
    }
    
    
    

}