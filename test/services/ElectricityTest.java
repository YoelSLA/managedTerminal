package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import load.Reefer;
import service.Electricity;

class ElectricityTest {

	private Reefer reefer;
	// ------------------------------------------------------------
	private Double price;
	private LocalDateTime startDate;
	private Electricity electricity; // SUT

	Double priceB = 300.0;
	LocalDateTime startDateB = LocalDateTime.of(2023, 11, 10, 10, 40);
	LocalDateTime endDateB = startDateB.plusMinutes(40);

	@BeforeEach
	void setUp() {
		// REEFER
		reefer = mock(Reefer.class);
		when(reefer.getConsumptionkWh()).thenReturn(50.0);
		// ------------------------------------------------------------------------------------------
		price = 100.0;
		startDate = LocalDateTime.of(2023, Month.DECEMBER, 01, 13, 00); // 01-12-23 | 13:00 Hs.
		electricity = new Electricity(price, startDate);
	}

	@Test
	void x() {
		assertEquals(startDate, electricity.getStartConnection());
	}

	@Test
	void x1() {
		assertEquals(price, electricity.getPrice());
	}

	@Test
	void x3() {
		// Exercise
		electricity.setEndConnection(LocalDateTime.of(2023, Month.DECEMBER, 02, 20, 00)); // 02-12-23 | 20:00 Hs.;
		// Assert
		assertEquals(LocalDateTime.of(2023, Month.DECEMBER, 02, 20, 00), electricity.getEndConnection());
	}

	@Test
	void x4() {
		// Exercise
		electricity.setEndConnection(LocalDateTime.of(2023, Month.DECEMBER, 02, 20, 00)); // 02-12-23 | 20:00 Hs.;
		// Assert
		assertEquals(155000.0, electricity.getPriceFor(reefer));
	}

}
