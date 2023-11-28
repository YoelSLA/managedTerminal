package trip;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import maritimeCircuit.MaritimeCircuit;
import ship.Ship;
import terminal.Terminal;

public class Trip {

	private LocalDateTime startDate;
	private Ship ship;
	private MaritimeCircuit maritimeCircuit;

	public Trip(MaritimeCircuit maritimeCircuit, Ship ship, LocalDateTime startDate) {
		this.maritimeCircuit = maritimeCircuit;
		this.ship = ship;
		this.assginTrip(this, ship);
		this.startDate = startDate;
	}

	public LocalDateTime dateArrivedToDestiny(Terminal destiny) {
		return startDate.plus(maritimeCircuit.calculateTimeBetween(maritimeCircuit.originTerminal(), destiny),
				ChronoUnit.HOURS);
	}

	public MaritimeCircuit getMaritimeCircuit() {
		return maritimeCircuit;
	}

	public Ship getShip() {
		return ship;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public boolean hasADestinyTerminal(Terminal destiny) {
		return maritimeCircuit.hasADestinyTerminal(destiny);
	}

	public Terminal originTerminal() {
		return maritimeCircuit.originTerminal();
	}

	private void assginTrip(Trip trip, Ship ship) {
		ship.setTrip(trip);
	}

}
