package com.shoppingapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.service.CheckoutService;

@RestController
public class CheckoutController {
	
    @Autowired
    CheckoutService checkoutService;

    
    @PostMapping("/products/checkout")
    private String checkOutProducts(@RequestBody String data) throws Exception
    {
    	try {
        return checkoutService.calculateCartItems(data);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		throw e;
    	}
    }

}