package search.selection;

import java.util.List;

import terminal.Terminal;
import trip.Trip;

public class DestinationTerminal extends Selection {

	public DestinationTerminal(Terminal destiny) {
		super(destiny);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		return trips.stream().filter(t -> t.hasATerminal(getTerminal())).toList();
	}

}
