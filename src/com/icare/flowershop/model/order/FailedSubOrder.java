package com.icare.flowershop.model.order;

import java.util.ArrayList;

public class FailedSubOrder extends SubOrder{
	
	private int bundle;
	private String code;
	public static final String FAILURE_MESSAGE = "BUNDLE IS NOT AVAILABLE!";
	
	public FailedSubOrder(int value, String code) {
		super(new ArrayList<Item>());
		this.setBundle(value);
		this.code = code;
	}

	public int getBundle() {
		return bundle;
	}

	public void setBundle(int bundle) {
		this.bundle = bundle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// Don't violate Liskov's substitution principle
	@Override
	public double computeTotal() {
		return -1;
	}
	
}
