package service;

import java.time.Duration;
import java.time.LocalDateTime;

import load.Load;
import load.Reefer;

public class Electricity extends Service {

	private LocalDateTime startConnection;
	private LocalDateTime endConnection;

	public Electricity(Double costPerkWh, LocalDateTime startConnection) {
		super(costPerkWh, "Electricity");
		this.startConnection = startConnection;
	}

	public LocalDateTime getEndConnection() {
		return endConnection;
	}

	@Override
	public Double getPriceFor(Load load) {
		// Se hace un downcasting para obtener el reefer.
		Reefer reefer = (Reefer) load;
		// Se calcula las horas las cuales estuvo el reefer conectado.
		Long hours = Math.abs(Duration.between(getStartConnection(), getEndConnection()).toHours());

		return (reefer.getConsumptionkWh() * hours.intValue()) * getPrice();
	}

	public LocalDateTime getStartConnection() {
		return startConnection;
	}

	public void setEndConnection(LocalDateTime endConnection) {
		this.endConnection = endConnection;
	}

}
