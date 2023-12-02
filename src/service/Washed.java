package service;

import load.Load;

public class Washed extends Service {

	private Double otherPrice;

	public Washed(Double priceToBigLoad, Double priceToSmallLoad) {
		super(priceToBigLoad, "Washed");
		this.otherPrice = priceToSmallLoad;
	}

	@Override
	public Double getPriceFor(Load load) {
		return (load.getVolume() > 70) ? getPrice() : otherPrice;
	}

	public Double getOtherPrice() {
		return otherPrice;
	}

}
