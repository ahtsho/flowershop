package com.icare.flowershop.order;

import java.util.List;

public class Order {

	private List<SubOrder> suborders;
	
	private double total;

	public Order(List<SubOrder> suborders) {
		this.suborders = suborders;
	}

	public Order() {
	}

	public double getTotal() {
		return total;
	}

	public List<SubOrder> getSuborders() {
		return suborders;
	}

	public void setSuborder(List<SubOrder> suborders) {
		this.suborders = suborders;
	}

	public void addSuborder(SubOrder suborder) {
		this.suborders.add(suborder);
	}

	// replace return type with Optional<Double> 
	public double computeTotal() {
		if(!this.suborders.isEmpty()) {
			this.total = this.suborders
					.stream()
					.map(sub -> sub.getSubtotal())
					.reduce(Double::sum)
					.get();
		}
		return this.total;
	}

	// change to Optional<SubOrder>
	public SubOrder getSuborderByItemCode(String code) {
		
		return this.suborders
			.stream()
			.filter(sub-> sub.getCode().equals(code)).findFirst().get();
		
	}

	
}
