package search.criteria;

import java.time.LocalDateTime;
import java.util.List;

import terminal.Terminal;
import trip.Trip;

public class DepartureDate implements Criteria {

	private LocalDateTime departureDate;
	private Terminal origin;

	public DepartureDate(LocalDateTime departureDate, Terminal origin) {
		this.departureDate = departureDate;
		this.origin = origin;
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		return trips.stream()
				.filter(t -> t.hasADestinyTerminal(destiny) && t.dateArrivedToDestiny(destiny).equals(arrivalDate))
				.toList();
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public Terminal getOrigin() {
		return origin;
	}

}
