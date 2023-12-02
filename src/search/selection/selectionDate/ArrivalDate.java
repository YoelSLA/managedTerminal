package search.selection.selectionDate;

import java.time.LocalDateTime;
import java.util.List;

import search.criteria.Criteria;
import terminal.Terminal;
import trip.Trip;

public class ArrivalDate extends SelectionDate {

	public ArrivalDate(Criteria criteria, LocalDateTime arrivalDate, Terminal destiny) {
		super(criteria, arrivalDate, destiny);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		return trips.stream().filter(t -> t.hasATerminal(getTerminal())
				&& this.searchByCriteriaTo(t.calculateArrivalDateToTerminal(getTerminal()))).toList();
	}

}
