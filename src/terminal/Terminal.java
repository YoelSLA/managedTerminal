package terminal;

import geographicalPosition.GeographicalPosition;

public abstract class Terminal {

	private GeographicalPosition geographicalPosition;
	private String name;

	public Terminal(GeographicalPosition geographicalPosition, String name) {
		this.geographicalPosition = geographicalPosition;
	}

	public GeographicalPosition getGeographicalPosition() {
		return geographicalPosition;
	}

	public String getName() {
		return name;
	}
}
