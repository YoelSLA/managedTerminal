package trip;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import maritimeCircuit.MaritimeCircuit;
import ship.Ship;
import terminal.Terminal;

/**
 * @author alejandrabesel Clase que representa a un viaje.
 * 
 *         Esta clase gestiona la información del viaje.
 * 
 */

public class Trip {

	private LocalDateTime startDate;
	private Ship ship;
	private MaritimeCircuit maritimeCircuit;

	public Trip(MaritimeCircuit maritimeCircuit, Ship ship, LocalDateTime startDate) {
		this.maritimeCircuit = maritimeCircuit;
		this.ship = ship;
		this.asignarViaje(this, ship);
		this.startDate = startDate;
	}

	private void asignarViaje(Trip trip, Ship ship) {
		ship.otorgarViaje(trip);
	}

	/**
	 * @author alejandrabesel return Devuelve el circuito marítimo que realiza el
	 *         viaje.
	 */
	public MaritimeCircuit getMaritimeCircuit() {
		return maritimeCircuit;
	}

	/**
	 * @author alejandrabesel return Devuelve la instancia de buque que hara ese
	 *         viaje
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * @author alejandrabesel return Devuelve el dia de inicio del viaje
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public boolean hasADestinyTerminal(Terminal destiny) {
		System.out.println("Terminals in Maritime Circuit: "
				+ maritimeCircuit.getStretchs().stream().map(s -> s.getDestiny()).toList());
		System.out.println("Destiny1: " + destiny); // Verifica que el terminal de destino sea el correcto
		return maritimeCircuit.hasADestinyTerminal(destiny);

	}

	public LocalDateTime dateArrivedToDestiny(Terminal destiny) {

		System.out
				.println(maritimeCircuit.getStretchs().stream().map(s -> s.getOrigin()).map(s -> s.getName()).toList());
		System.out.println("OriginMetodo: " + maritimeCircuit.originTerminal());
		Integer durationOfHours = maritimeCircuit.calculateTimeBetween(maritimeCircuit.originTerminal(), destiny);
		System.out.println(durationOfHours);

		return startDate.plus(durationOfHours, ChronoUnit.HOURS);
	}

	public Terminal originTerminal() {
		return maritimeCircuit.originTerminal();
	}

}
