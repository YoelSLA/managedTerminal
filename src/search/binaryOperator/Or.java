package search.binaryOperator;

import java.util.ArrayList;
import java.util.List;

import trip.Trip;

public class Or extends BinaryOperator {

	public Or(BinaryOperator leftClause, BinaryOperator rightClause) {
		super(leftClause, rightClause);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		List<Trip> newTripsFiltered = new ArrayList<>();

		List<Trip> firstFiltered = this.getLeftClause().filterTrips(trips);
		newTripsFiltered.addAll(firstFiltered);

		List<Trip> secondFiltered = this.getRightClause().filterTrips(trips);
		newTripsFiltered.addAll(secondFiltered);

		return newTripsFiltered;
	}

}
