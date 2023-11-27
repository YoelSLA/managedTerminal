package search.criteria;

import java.time.LocalDateTime;
import java.util.List;

import terminal.Terminal;
import trip.Trip;

/**
 * Clase que representa el criterio de filtro por fecha de llegada, implementa
 * interfaz Criteria. Permite filtrar circuitos maritimos.
 * 
 * @author Gabriela Fascetta.
 */
public class ArrivalDate implements Criteria {

	private LocalDateTime arrivalDate;
	private Terminal destiny;

	public ArrivalDate(LocalDateTime arrivalDate, Terminal destiny) {
		this.arrivalDate = arrivalDate;
		this.destiny = destiny;
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		System.out.println("ENTREEEE"); // Mensaje de depuración para verificar si el método se ejecuta correctamente
		System.out.println("destiny: " + destiny); // Mensaje de depuración para verificar el valor de destiny
		System.out.println("fechaArrival: " + arrivalDate); // Mensaje de depuración para verificar el valor de
															// arrivalDate

		// Filtrar las travesías basándose en la condición
		List<Trip> filteredTrips = trips.stream().filter(trip -> {
			// Mensaje de depuración para verificar si la travesía cumple con la condición
			System.out.println("Checking trip: " + trip);

			// Verificar si la travesía tiene el destino y la fecha de llegada correctos
			boolean hasCorrectDestiny = trip.hasADestinyTerminal(destiny);
			boolean hasCorrectArrivalDate = trip.dateArrivedToDestiny(destiny).equals(arrivalDate);

			// Mensajes de depuración para verificar los resultados de las condiciones
			// individuales
			System.out.println("Has correct destiny: " + hasCorrectDestiny);
			System.out.println("Has correct arrival date: " + hasCorrectArrivalDate);

			// Devolver true solo si ambas condiciones son verdaderas
			return hasCorrectDestiny && hasCorrectArrivalDate;
		}).toList();

		// Mensaje de depuración para verificar las travesías filtradas
		System.out.println("Filtered trips: " + filteredTrips);

		return filteredTrips;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public Terminal getDestiny() {
		return destiny;
	}

}
