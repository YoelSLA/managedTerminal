package terminal;

import position.Position;

public abstract class Terminal {
	private Position position;
	private String name;

	public Terminal(Position position, String name) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}
}
