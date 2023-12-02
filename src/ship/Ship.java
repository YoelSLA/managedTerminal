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
		this.position = getTrip().originTerminal().getPosition();
	}

	public Position getPosition() {
		return position;
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

	public Trip getTrip() {
		return trip;
	}

	public Terminal getTerminal() {
		return terminal;
	}

////	public void setPosition(Position position) {
//	this.position=position;phase.newPosition(this);
//
//	}

	public void setTrip(Trip trip) {
		if (isOnTrip) {
			throw new RuntimeException("The ship has already started the trip");
		}
		this.trip = trip;
	}

	public void startTrip() {
		isOnTrip = !isOnTrip;
		terminal = getTrip().nextTerminalOf(terminal);

	}
//
//	public void setPhase(Phase phase) {
//		this.phase = phase;
//	}
//
//	public void depart() {
//		terminal = getTrip().nextTerminalOf(terminal);
//		setPhase(phase.nextPhase());
//
//	}
//
//	public void notifyInminentArrival() {
//		terminal.notifyShipInminentArrival(this);
//	}
//
//	public void notifyArrival() {
//		terminal.notifyShipArrival(this);
//	}
//
//	public void startWorking() {
//		// TODO Auto-generated method stub
//		
//	}

}
