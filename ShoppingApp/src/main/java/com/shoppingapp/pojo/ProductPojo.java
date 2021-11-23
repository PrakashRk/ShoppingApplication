package com.shoppingapp.pojo;

public class ProductPojo {
	
    private String productCode;
    private int quantity;
	   
	public ProductPojo(String productCode, int quantity) {
		this.productCode=productCode;
		this.quantity=quantity;
	}
	public ProductPojo() {}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	    
	
}
