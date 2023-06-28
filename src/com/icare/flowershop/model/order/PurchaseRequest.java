package com.icare.flowershop.model.order;

public class PurchaseRequest {

	private int amount;
	private String code;
	
	public PurchaseRequest(int amount, String code) {
		this.amount = amount;
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

}
