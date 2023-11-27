package search.selection.selectionDate;

import java.time.LocalDateTime;
import java.util.List;

import search.criteria.Criteria;
import terminal.Terminal;
import trip.Trip;

public class DepartureDate extends SelectionDate {

	public DepartureDate(Criteria criteria, LocalDateTime departureDate, Terminal origin) {
		super(criteria, departureDate, origin);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		// Filtrar las traves�as por el terminal de origen
		List<Trip> tripsWithManagedTerminalOrigin = trips.stream().filter(t -> t.originTerminal().equals(terminal))
				.toList();
		// Filtrar las traves�as por otros criterios utilizando searchByCriteriaTo
		List<Trip> filteredTrips = tripsWithManagedTerminalOrigin.stream()
				.filter(t -> this.searchByCriteriaTo(t.getStartDate())).toList();
		return filteredTrips;
	}

	public Terminal getOrigin() {
		return terminal;
	}
	

}
