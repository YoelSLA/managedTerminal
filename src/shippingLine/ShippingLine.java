package shippingLine;

import java.util.ArrayList;
import java.util.List;

import maritimeCircuit.MaritimeCircuit;
import ship.Ship;
import trip.Trip;

public class ShippingLine {

	private String cuit;
	private List<MaritimeCircuit> maritimeCircuits;
	private String name;
	private List<Ship> ships;
	private List<Trip> trips;

	public ShippingLine(String cuit, String name) {
		this.cuit = cuit;
		this.maritimeCircuits = new ArrayList<MaritimeCircuit>();
		this.name = name;
		this.ships = new ArrayList<Ship>();
		this.trips = new ArrayList<Trip>();
	}

	public String getCuit() {
		return cuit;
	}

	public List<MaritimeCircuit> getMaritimeCircuits() {
		return maritimeCircuits;
	}

	public String getName() {
		return name;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void registerMaritimeCircuit(MaritimeCircuit maritimeCircuit) {
		maritimeCircuits.add(maritimeCircuit);
	}

	public void registerShip(Ship ship) {
		ships.add(ship);
	}

	public void registerTrip(Trip trip) throws Exception {
		validateShipRegistrationIn(trip);
		validateMaritimeCircuitRegistrationIn(trip);
		trips.add(trip);
	}

	private void validateMaritimeCircuitRegistrationIn(Trip trip) throws Exception {
		if (!getMaritimeCircuits().contains(trip.getMaritimeCircuit())) {
			throw new RuntimeException("The maritime circuit is not registered in the shipping line.");
		}
	}

	private void validateShipRegistrationIn(Trip trip) throws Exception {
		if (!getShips().contains(trip.getShip())) {
			throw new RuntimeException("The ship is not registered in the shipping line.");
		}
	}
}
