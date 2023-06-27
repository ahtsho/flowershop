package com.icare.flowershop.product;

import java.util.List;

public class Flower implements Product{

	private String name;
	private String code;
	private List<PricedBundle> bundels;
	
	public Flower(String name, String code, List<PricedBundle> bundles) {
		this.name = name;
		this.code = code;
		this.setBundels(bundles);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public List<PricedBundle> getBundels() {
		return bundels;
	}
	
	@Override
	public void setBundels(List<PricedBundle> bundels) {
		this.bundels = bundels;
	}
	
	
}
