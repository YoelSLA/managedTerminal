package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import load.Load;
import service.ExcessStorage;

class ExcessStorageTest {

	private Load load;
	// ------------------------------------------------------------
	private Double price;
	private Integer hoursOfStay;
	private ExcessStorage excessStorage;

	@BeforeEach
	void setUp() {
		// LOAD
		load = mock(Load.class);
		// ------------------------------------------------------------------------------------------
		price = 100.0;
		hoursOfStay = 10;
		excessStorage = new ExcessStorage(price, hoursOfStay);
	}

	@Test
	void testGetPrice_ShouldReturnExpectedPrice() {
		assertEquals(price, excessStorage.getPrice());
	}

	@Test
	void testGetHoursOfStay_ShouldReturnExpectedHours() {
		assertEquals(hoursOfStay, excessStorage.getHoursOfStay());
	}

	@Test
	void testGetPriceFor_ShouldCalculateCorrectPriceForLoad() {
		assertEquals(1000.0, excessStorage.getPriceFor(load));
	}

}
