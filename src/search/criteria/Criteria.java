package search.criteria;

import java.util.List;

import search.Search;
import trip.Trip;

public interface Criteria extends Search {

	@Override
	public List<Trip> filterTrips(List<Trip> trips);

}
