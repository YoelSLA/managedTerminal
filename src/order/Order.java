package order;

import java.util.ArrayList;
import java.util.List;

import bill.Bill;
import client.Client;
import driver.Driver;
import load.Load;
import service.Service;
import terminal.Terminal;
import trip.Trip;
import truck.Truck;
import turn.Turn;

public abstract class Order {

	private Bill bill;
	private Client client;
	private Terminal destiny;
	private Load load;
	private Terminal origin;
	private List<Service> services;
	private Trip trip;
	private Turn turn;

	public Order(Load load, Trip trip, Terminal origin, Terminal destiny, Client client, Driver driver, Truck truck) {
		this.bill = new Bill(this);
		this.client = client;
		this.load = load;
		this.origin = origin;
		this.services = new ArrayList<Service>();
		this.trip = trip;
		this.turn = new Turn(driver, truck, null);
	}

	public Bill getBill() {
		return bill;
	}

	public Client getClient() {
		return client;
	}

	public Terminal getDestiny() {
		return destiny;
	}

	public Load getLoad() {
		return load;
	}

	public Terminal getOrigin() {
		return origin;
	}

	public List<Service> getServices() {
		return services;
	}

	public Trip getTrip() {
		return trip;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public void registerService(Service service) {
		services.add(service);

	}

}
