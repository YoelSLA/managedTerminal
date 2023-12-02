package service;

import load.Load;

public class ExcessStorage extends Service {

	private Integer hoursOfStay;

	public ExcessStorage(Double price, Integer hoursOfStay) {
		super(price, "ExcessStorage");
		this.hoursOfStay = hoursOfStay;
	}

	@Override
	public Double getPriceFor(Load load) {
		return hoursOfStay * getPrice();
	}

	public Integer getHoursOfStay() {
		return hoursOfStay;
	}

}
