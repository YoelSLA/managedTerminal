package search.selection.selectionDate;

import java.time.LocalDateTime;
import java.util.List;

import search.criteria.Criteria;
import terminal.Terminal;
import trip.Trip;

public class ArrivalDate extends SelectionDate {

	private Terminal destiny;

	public ArrivalDate(Criteria criteria, LocalDateTime arrivalDate, Terminal destiny) {
		super(criteria, arrivalDate);
		this.destiny = destiny;
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		return trips.stream()
				.filter(t -> t.hasADestinyTerminal(destiny) && this.searchByCriteriaTo(t.dateArrivedToDestiny(destiny)))
				.toList();
	}

	public Terminal getDestiny() {
		return destiny;
	}

}
