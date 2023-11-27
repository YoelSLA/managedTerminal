package stretch;

import java.time.Duration;

import terminal.Terminal;

public class Stretch {

	private Terminal destiny;
	private Terminal origin;
	private Double price;
	private Duration time;

	public Stretch(Terminal origin, Terminal destiny, Double price, Duration time) {
		this.origin = origin;
		this.destiny = destiny;
		this.price = price;
		this.time = time;
	}

	public Terminal getDestiny() {
		return destiny;
	}

	public Terminal getOrigin() {
		return origin;
	}

	public Double getPrice() {
		return price;
	}

	public Duration getTime() {
		return time;
	}

}
