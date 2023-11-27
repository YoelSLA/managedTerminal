package tracker;

import java.util.List;

import search.Search;
import trip.Trip;

public class Tracker {

	private Search search;

	public Tracker() {

	}

	public Tracker(Search search) {
		this.search = search;
	}

	public List<Trip> searchTrips(List<Trip> trips) {
		return search.filterTrips(trips);
	}

	public Search getSearch() {
		return search;
	}

}
