package load;

public class Dry extends Load {

	public Dry(Double height, Double length, Double width, Double weight) {
		super(height, length, width, weight, "Dry");
	}

	@Override
	public Integer getCode() {
		return super.code + 1;
	}

}
