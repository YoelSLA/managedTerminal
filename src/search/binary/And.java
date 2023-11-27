package search.binary;

import java.util.ArrayList;
import java.util.List;

import trip.Trip;

public class And extends BinaryOperator {

	public And(BinaryOperator leftClause, BinaryOperator rightClause) {
		super(leftClause, rightClause);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> tripsToFilter) {
		List<Trip> newTripsFiltered = new ArrayList<>();

		List<Trip> firstFiltered = this.getLeftClause().filterTrips(tripsToFilter);
		newTripsFiltered.addAll(firstFiltered);

		List<Trip> secondFiltered = this.getRightClause().filterTrips(tripsToFilter);
		newTripsFiltered.retainAll(secondFiltered);

		return newTripsFiltered;
	}

}
