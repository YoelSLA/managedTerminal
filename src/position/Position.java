package position;

public class Position {

	private double altitude;
	private double latitude;

	public Position(Double altitude, Double latitude) {
		this.altitude = altitude;
		this.latitude = latitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setPosition(Double altitude, Double latitude) {
		setAltitude(altitude);
		setAltitude(latitude);
	}

}
