package search.selection;

import java.util.List;

import search.Search;
import trip.Trip;

public interface Selection extends Search {

	@Override
	public List<Trip> filterTrips(List<Trip> trips);

}
