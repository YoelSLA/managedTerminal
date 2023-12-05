package ship;

import phase.Outbound;
import phase.Phase;
import position.Position;
import terminal.Terminal;
import trip.Trip;

public class Ship {

	private String imo;
	private Boolean isOnTrip;
	private String name;
	private Phase phase;
	private Position position;
	private Terminal terminal;
	private Trip trip;

	public Ship(String imo, String name, Trip trip) {
		this.imo = imo;
		this.isOnTrip = false;
		this.name = name;
		this.phase = new Outbound();
		this.trip = trip;
		this.position = getTrip().getOriginTerminal().getPosition();
	}

	public String getImo() {
		return imo;
	}

	public Boolean getIsOnTrip() {
		return isOnTrip;
	}

	public String getName() {
		return name;
	}

	public Phase getPhase() {

		return phase;
	}

	public Position getPosition() {
		return position;
	}

	public Trip getTrip() {
		return trip;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public void setPosition(Position position) {
		this.position = position;
		phase.proceedToNextPhase(this);
	}

	public void setTrip(Trip trip) throws Exception {
		if (isOnTrip) {
			throw new RuntimeException("The ship has already started the trip");
		}
		this.trip = trip;
	}

	public Integer calculateDistanceToTerminal() {
		return position.distanceInKilometersBetween(position, terminal.getPosition());
	}

	public void depart() {
		phase.changePhase(this);
	}

	public void nextTerminal() {
		terminal = trip.getNextTerminal(getTerminal());
	}

	public void notifyArrival() {
		terminal.notifyShipArrival(this);
	}

	public void notifyDeparture() {
		terminal.notifyShipDeparture(this);
	}

	public void notifyInminentArrival() {
		terminal.notifyShipInminentArrival(this);
	}

	public void startTrip() {
		isOnTrip = !isOnTrip;

	}

	public void startWorking() {
		phase.changePhase(this);
		System.out.println("The start of work begins...");
	}

}
