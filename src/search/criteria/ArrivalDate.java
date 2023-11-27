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
		System.out.println("ENTREEEE"); // Mensaje de depuraci�n para verificar si el m�todo se ejecuta correctamente
		System.out.println("destiny: " + destiny); // Mensaje de depuraci�n para verificar el valor de destiny
		System.out.println("fechaArrival: " + arrivalDate); // Mensaje de depuraci�n para verificar el valor de
															// arrivalDate

		// Filtrar las traves�as bas�ndose en la condici�n
		List<Trip> filteredTrips = trips.stream().filter(trip -> {
			// Mensaje de depuraci�n para verificar si la traves�a cumple con la condici�n
			System.out.println("Checking trip: " + trip);

			// Verificar si la traves�a tiene el destino y la fecha de llegada correctos
			boolean hasCorrectDestiny = trip.hasADestinyTerminal(destiny);
			boolean hasCorrectArrivalDate = trip.dateArrivedToDestiny(destiny).equals(arrivalDate);

			// Mensajes de depuraci�n para verificar los resultados de las condiciones
			// individuales
			System.out.println("Has correct destiny: " + hasCorrectDestiny);
			System.out.println("Has correct arrival date: " + hasCorrectArrivalDate);

			// Devolver true solo si ambas condiciones son verdaderas
			return hasCorrectDestiny && hasCorrectArrivalDate;
		}).toList();

		// Mensaje de depuraci�n para verificar las traves�as filtradas
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
