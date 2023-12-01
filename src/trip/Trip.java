package trip;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import maritimeCircuit.MaritimeCircuit;
import ship.Ship;
import terminal.Terminal;

public class Trip {

	private MaritimeCircuit maritimeCircuit;
	private static Integer numTrip = 0;
	private Ship ship;
	private LocalDateTime startDate;

	public Trip(MaritimeCircuit maritimeCircuit, Ship ship, LocalDateTime startDate) {
		this.maritimeCircuit = maritimeCircuit;
		Trip.numTrip = numTrip++;
		this.ship = ship;
		this.assginTrip(this, ship);
		this.startDate = startDate;
	}

	public LocalDateTime calculateArrivalDateToTerminal(Terminal terminal) {
		// Se obtiene la terminal origen del circuito marítimo.
		final Terminal originTerminal = maritimeCircuit.originTerminal();

		// Se calcula las horas totales hasta la llegada a la terminal del parámetro.
		final Integer hoursToArrival = maritimeCircuit.calculateTotalHoursBetweenTerminals(originTerminal, terminal);

		// Se suma las horas al startDate para obtener la fecha de llegada a la terminal
		// del parámetro.
		LocalDateTime arrivalDate = startDate.plus(hoursToArrival, ChronoUnit.HOURS);

		return arrivalDate;
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

	public boolean hasATerminal(Terminal terminal) {
		return maritimeCircuit.hasATerminal(terminal);
	}

	public Terminal nextTerminalOf(Terminal terminal) {
		// Se obtiene el �ndice de la terminal del par�metro.
		final Integer indexTerminal = maritimeCircuit.originTerminals().indexOf(terminal);
		// Se retorna la siguiente terminal del par�metro.
		return maritimeCircuit.originTerminals().get(indexTerminal + 1);
	}

	public Terminal originTerminal() {
		return maritimeCircuit.originTerminal();
	}

	private void assginTrip(Trip trip, Ship ship) {
		ship.setTrip(trip);
	}

}
