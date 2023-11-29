package ship;

import phase.Inbound;
import phase.Phase;
import position.Position;
import trip.Trip;

public class Ship {

	private String imo;
	private Boolean isOnTrip;
	private String name;
	private Phase phase;
	private Position position;
	private Trip trip;

	public Ship(Position geographicalPosition, String imo, String name) {
		this.imo = imo;
		this.isOnTrip = false;
		this.name = name;
		this.phase = new Inbound();
		this.position = geographicalPosition;
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

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setTrip(Trip trip) {
		if (isOnTrip) {
			throw new RuntimeException("The ship has already started the trip");
		}
		this.trip = trip;
	}

	public void startTrip() {
		isOnTrip = !isOnTrip;
	}

}
