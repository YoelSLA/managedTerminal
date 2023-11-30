package position;

public class Position {

	private double latitude;
	private double longitude;

	public Position(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setPosition(Double latitude, Double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

}
