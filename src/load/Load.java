package load;

public abstract class Load {

	protected Integer code;
	private Double length;
	private String name;
	private Double height;
	private Double width;
	private Double weight;

	public Load(Double height, Double length, Double width, Double weight, String name) {
		this.code = 0; // TODO: EXPLICAR ESTO DESPUES
		this.height = height;
		this.name = name;
		this.length = length;
		this.width = width;
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public Double getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public final Double getVolume() { // TODO: TEMPLED METHOD
		return height * length * width;
	}

	public Double getWidth() {
		return width;
	}

	public Double getWeight() {
		return weight;
	}

	public abstract Integer getCode();

}
