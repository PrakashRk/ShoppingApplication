package com.shoppingapp.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.model.Products;
import com.shoppingapp.pojo.ProductDataPojo;
import com.shoppingapp.pojo.ProductPojo;
import com.shoppingapp.repository.ProductsRepository;

@Service
public class CheckoutService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ProductsService productsService;
    
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
    
    public String calculateCartItems(String data) throws Exception
    {
    	Double sum=0.0;	
    	
		ProductDataPojo productDataPojo = new ObjectMapper().readValue(data,ProductDataPojo.class);
		
		List<ProductPojo>productPojoList = productDataPojo.getProductPojoList();
		for(ProductPojo item:productPojoList)
		{
			Products products= productsService.getProductByCode(item.getProductCode());
			
			sum=sum + this.applyOffer(item,products.getPrice());
		}
		
		return String.valueOf(Math.round(sum*100.0)/100.0);
    }
    
    public Double applyOffer(ProductPojo item,Double price)
    {
    	Double amount=0.0;
    	if(item.getQuantity()==0)
    		return 0.0;
    	
    	if(item.getProductCode().equals("BRD"))
		{
    		if(item.getQuantity()%2!=0) 
    			item.setQuantity(item.getQuantity()+1);
    		
    		amount=price*(item.getQuantity()/2);
    		
		}else if(item.getProductCode().equals("MLK"))
		{
    		Double OfferAmount = (item.getQuantity()/3)*(2*price);
    		Double extra = (item.getQuantity()%3)*price;
    		amount = OfferAmount + extra;
		}
		else if(item.getProductCode().equals("BTR"))
		{
    		Double totalAmt = item.getQuantity()*price;
    		Integer offerQty = Integer.valueOf(item.getQuantity()/3);
    		Double offerAmt= offerQty * price;
    		amount = totalAmt-offerAmt;
		}
    	else
		{
			return (price*item.getQuantity());
		}
    	
    	return amount;
    }
}