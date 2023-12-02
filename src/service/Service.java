package service;

import load.Load;

public abstract class Service {

	private String name;
	private Double price;

	public Service(Double price, String name) {
		this.name = name;
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public abstract Double getPriceFor(Load load);

	public String getName() {
		return name;
	}

}
