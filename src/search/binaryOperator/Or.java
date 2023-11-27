package search.binaryOperator;

import java.util.List;
import java.util.stream.Stream;

import search.Search;
import trip.Trip;

public class Or extends BinaryOperator {

	public Or(Search leftClause, Search rightClause) {
		super(leftClause, rightClause);
	}

	@Override
	public List<Trip> filterTrips(List<Trip> trips) {
		List<Trip> leftClause = this.getLeftClause().filterTrips(trips);
		List<Trip> rightClause = this.getRightClause().filterTrips(trips);

		return Stream.concat(leftClause.stream(), rightClause.stream()).distinct().toList();

	}

}
