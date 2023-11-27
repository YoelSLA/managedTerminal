package terminal;

import geographicalPosition.GeographicalPosition;

public abstract class Terminal {

	private GeographicalPosition geographicalPosition;

	public Terminal(GeographicalPosition geographicalPosition) {
		this.geographicalPosition = geographicalPosition;
	}

	public GeographicalPosition getGeographicalPosition() {
		return geographicalPosition;
	}
}
