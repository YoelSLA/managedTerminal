package load;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TankTest {

	private Double height;
	private Double length;
	private Double width;
	private Double weight;
	private Tank tank;

	@BeforeEach
	void setUp() {
		height = 100.0;
		length = 300.0;
		width = 200.0;
		weight = 20.000;
		tank = new Tank(height, length, width, weight);
	}

	@Test
	void testHeight() {
		assertEquals(height, tank.getHeight());
	}

	@Test
	void testLength() {
		assertEquals(length, tank.getLength());
	}

	@Test
	void testVolumeCalculation() {
		assertEquals(height * length * width, tank.getVolume());
	}

	@Test
	void testWeight() {
		assertEquals(weight, tank.getWeight());
	}

	@Test
	void testWidth() {
		assertEquals(width, tank.getWidth());
	}
}
