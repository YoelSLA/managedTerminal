package load;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReeferTest {

	private Double height;
	private Double length;
	private Double width;
	private Double weight;
	private Double consumptionkWh;
	private Reefer reefer;

	@BeforeEach
	void setUp() {
		height = 100.0;
		length = 300.0;
		width = 200.0;
		weight = 20.000;
		consumptionkWh = 40.0;
		reefer = new Reefer(height, length, width, weight, consumptionkWh);
	}

	@Test
	void testHeight() {
		assertEquals(height, reefer.getHeight());
	}

	@Test
	void testLength() {
		assertEquals(length, reefer.getLength());
	}

	@Test
	void testVolumeCalculation() {
		assertEquals(height * length * width, reefer.getVolume());
	}

	@Test
	void testWeight() {
		assertEquals(weight, reefer.getWeight());
	}

	@Test
	void testWidth() {
		assertEquals(width, reefer.getWidth());
	}

	@Test
	void testCompustionkWh() {
		assertEquals(consumptionkWh, reefer.getConsumptionkWh());
	}

	@Test
	void setConsumptionAndGetConsumptionkWh() {
		// Exercise
		reefer.setConsumption(50.0);
		// Assert
		assertEquals(50.0, reefer.getConsumptionkWh());
	}

}
