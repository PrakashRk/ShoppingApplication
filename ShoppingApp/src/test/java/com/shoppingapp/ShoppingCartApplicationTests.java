package com.shoppingapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.pojo.ProductDataPojo;
import com.shoppingapp.pojo.ProductPojo;

@SpringBootTest
class ShoppingCartApplicationTests {

	
	@Test
	void checkInputMilk() throws  Exception {
		List<ProductPojo>productPojoList = new ArrayList<ProductPojo>();
    	ProductDataPojo productDataPojo = new ProductDataPojo();
  
    	productPojoList.add(new ProductPojo("BRD",0));
    	productPojoList.add(new ProductPojo("MLK",10));
    	productPojoList.add(new ProductPojo("BTR",0));
    	productDataPojo.setProductPojoList(productPojoList);
    	assertEquals(this.calculateItems(new ObjectMapper().writeValueAsString(productDataPojo)),"4.2");
	}
	
	@Test
	void checkInputButter() throws  Exception {
		List<ProductPojo>productPojoList = new ArrayList<ProductPojo>();
    	ProductDataPojo productDataPojo = new ProductDataPojo();
  
    	productPojoList.add(new ProductPojo("BRD",0));
    	productPojoList.add(new ProductPojo("MLK",0));
    	productPojoList.add(new ProductPojo("BTR",10));
    	productDataPojo.setProductPojoList(productPojoList);
    	assertEquals(this.calculateItems(new ObjectMapper().writeValueAsString(productDataPojo)),"2.8");
	}
	
	@Test
	void checkInputBread() throws  Exception {
		List<ProductPojo>productPojoList = new ArrayList<ProductPojo>();
    	ProductDataPojo productDataPojo = new ProductDataPojo();
  
    	productPojoList.add(new ProductPojo("BRD",10));
    	productPojoList.add(new ProductPojo("MLK",0));
    	productPojoList.add(new ProductPojo("BTR",0));
    	productDataPojo.setProductPojoList(productPojoList);
    	assertEquals(this.calculateItems(new ObjectMapper().writeValueAsString(productDataPojo)),"5.0");
	}
	
	@Test
	void checkInputTotalSum() throws  Exception {
		List<ProductPojo>productPojoList = new ArrayList<ProductPojo>();
    	ProductDataPojo productDataPojo = new ProductDataPojo();
  
    	productPojoList.add(new ProductPojo("BRD",10));
    	productPojoList.add(new ProductPojo("MLK",10));
    	productPojoList.add(new ProductPojo("BTR",10));
    	productDataPojo.setProductPojoList(productPojoList);
    	assertEquals(this.calculateItems(new ObjectMapper().writeValueAsString(productDataPojo)),"12.0");
	}
	
	
	public String calculateItems(String data) throws Exception
	{

    	Double sum=0.0;	
    	
		ProductDataPojo productDataPojo = new ObjectMapper().readValue(data,ProductDataPojo.class);
		
		List<ProductPojo>productPojoList = productDataPojo.getProductPojoList();
		Double price=0.00;
		for(ProductPojo item:productPojoList)
		{
            if(item.getProductCode().equals("BRD"))
            	price=1.0;
            if(item.getProductCode().equals("MLK"))
            	price=0.6;
            if(item.getProductCode().equals("BTR"))
            	price=0.4;
			sum=sum + this.applyOffer(item,price);
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
