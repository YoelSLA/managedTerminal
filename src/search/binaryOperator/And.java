package search.binaryOperator;

import java.util.ArrayList;
import java.util.List;

import search.Search;
import trip.Trip;

public class And extends BinaryOperator {

	public And(Search leftClause, Search rightClause) {
		super(leftClause, rightClause);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		List<Trip> newTripsFiltered = new ArrayList<>();

		List<Trip> firstFiltered = this.getLeftClause().filterTrips(trips);
		newTripsFiltered.addAll(firstFiltered);

		List<Trip> secondFiltered = this.getRightClause().filterTrips(trips);

		// Crear una nueva lista para almacenar la intersección
		List<Trip> intersection = new ArrayList<>(newTripsFiltered);
		intersection.retainAll(secondFiltered);

		return intersection;
	}
}
