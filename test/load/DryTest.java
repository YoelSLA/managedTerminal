package load;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DryTest {

	private Double height;
	private Double length;
	private Double width;
	private Double weight;
	private Dry dry;

	@BeforeEach
	void setUp() {
		height = 100.0;
		length = 300.0;
		width = 200.0;
		weight = 20.000;
		dry = new Dry(height, length, width, weight);
	}

	@Test
	void testHeight() {
		assertEquals(height, dry.getHeight());
	}

	@Test
	void testLength() {
		assertEquals(length, dry.getLength());
	}

	@Test
	void testVolumeCalculation() {
		assertEquals(height * length * width, dry.getVolume());
	}

	@Test
	void testWeight() {
		assertEquals(weight, dry.getWeight());
	}

	@Test
	void testWidth() {
		assertEquals(width, dry.getWidth());
	}
}
