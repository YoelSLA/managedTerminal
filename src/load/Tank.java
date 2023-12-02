package load;

public class Tank extends Load {

	public Tank(Double height, Double length, Double width, Double weight) {
		super(height, length, width, weight, "Tank");
	}

	@Override
	public Integer getCode() {
		return super.code + 3;
	}
}
