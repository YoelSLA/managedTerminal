package search.selection;

import java.util.List;

import terminal.Terminal;
import trip.Trip;

public class DestinationTerminal implements Selection {

	private Terminal destiny;

	public DestinationTerminal(Terminal destiny) {
		this.destiny = destiny;
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		return trips.stream().filter(t -> t.hasATerminal(destiny)).toList();
	}

	public Terminal getDestiny() {
		return destiny;
	}

	public void setDestiny(Terminal destiny) {
		this.destiny = destiny;
	}

}
