package load;

public class Reefer extends Load {

	private Double consumptionkWh;

	public Reefer(Double length, Double height, Double width, Double weight, Double consumptionkWh) {
		super(length, height, width, weight, "Reefer");
		this.consumptionkWh = consumptionkWh;
	}

	public Double getConsumptionkWh() {
		return consumptionkWh;
	}

	public void setConsumption(Double consumptionkWh) {
		this.consumptionkWh = consumptionkWh;
	}

}
