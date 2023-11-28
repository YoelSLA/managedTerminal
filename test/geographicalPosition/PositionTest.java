package geographicalPosition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import position.Position;

class PositionTest {
	private Position position;

	@BeforeEach
	void setUp() {
		position = new Position(-34.61315, -58.37723);
	}

	@Test
	void getShipGeographicalPosition() {
		assertEquals(-34.61315, position.getAltitude());
		assertEquals(-58.37723, position.getLatitude());

	}
}
