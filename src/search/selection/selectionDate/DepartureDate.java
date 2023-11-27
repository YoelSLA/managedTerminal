package search.selection.selectionDate;

import java.time.LocalDateTime;
import java.util.List;

import search.criteria.Criteria;
import terminal.Terminal;
import trip.Trip;

public class DepartureDate extends SelectionDate {

	private Terminal origin;

	public DepartureDate(Criteria criteria, LocalDateTime departureDate, Terminal origin) {
		super(criteria, departureDate);
		this.origin = origin;
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		// Filtrar las travesías por el terminal de origen
		List<Trip> tripsWithManagedTerminalOrigin = trips.stream().filter(t -> t.originTerminal().equals(origin))
				.toList();
		// Filtrar las travesías por otros criterios utilizando searchByCriteriaTo
		List<Trip> filteredTrips = tripsWithManagedTerminalOrigin.stream()
				.filter(t -> this.searchByCriteriaTo(t.getStartDate())).toList();
		return filteredTrips;
	}

	public Terminal getOrigin() {
		return origin;
	}

}
