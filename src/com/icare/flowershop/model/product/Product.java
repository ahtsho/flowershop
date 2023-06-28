package com.icare.flowershop.model.product;

import java.util.List;

public interface Product {

	public String getName();
	public String getCode();
	public void setCode(String code);
	List<PricedBundle> getBundels();
	void setBundels(List<PricedBundle> bundels);
	
}
