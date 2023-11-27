package terminal;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.Consignee;
import client.Shipper;
import driver.Driver;
import geographicalPosition.GeographicalPosition;
import order.ExportOrder;
import routing.Routing;
import shippingLine.ShippingLine;
import truck.Truck;
import truckTransportCompany.TruckTransportCompany;

class ManagedTerminalTest {

	private ExportOrder exportOrderOfAlejandra;
	private Driver driverAlberto; // DOC
	private Consignee consigneeGabriela; // DOC
	private Shipper shipperAlejandra; // DOC
	private ShippingLine apmMaersk; // DOC
	private Truck volvoTruck;
	private TruckTransportCompany transporteVesprini; // DOC
	private Routing ferFewerIntermediateTerminals; // DOC
	private Routing lowerPrice; // DOC
	private Routing shorterTime; // DOC
	private GeographicalPosition positionBuenosAires; // DOC
	private ManagedTerminal buenosAires; // SUT

	@BeforeEach
	void setUp() {
		// DRIVER
		driverAlberto = mock(Driver.class);

		// CONSIGNEE
		consigneeGabriela = mock(Consignee.class);

		// SHIPPER
		shipperAlejandra = mock(Shipper.class);

		// SHIPPING LINE
		apmMaersk = mock(ShippingLine.class);

		// TRUCK TRANSPORT COMPANY
		volvoTruck = mock(Truck.class);

		// TRUCK TRANSPORT COMPANY
		transporteVesprini = mock(TruckTransportCompany.class);

		when(transporteVesprini.getDrivers()).thenReturn(Arrays.asList(driverAlberto));
		when(transporteVesprini.getTrucks()).thenReturn(Arrays.asList(volvoTruck));

		// EXPORT ORDER
		exportOrderOfAlejandra = mock(ExportOrder.class);

		when(exportOrderOfAlejandra.getShipper()).thenReturn(shipperAlejandra);
		when(exportOrderOfAlejandra.getDriver()).thenReturn(driverAlberto);
		when(exportOrderOfAlejandra.getTruck()).thenReturn(volvoTruck);

		// ROUTING
		ferFewerIntermediateTerminals = mock(Routing.class);
		lowerPrice = mock(Routing.class);
		shorterTime = mock(Routing.class);

		// GEOGRAPHICAL POSITION
		positionBuenosAires = mock(GeographicalPosition.class);

		when(positionBuenosAires.getLatitude()).thenReturn(-34.5795823299825);
		when(positionBuenosAires.getLongitude()).thenReturn(-58.373877081937);

		// MANAGED TERMINAL

		buenosAires = new ManagedTerminal(positionBuenosAires, ferFewerIntermediateTerminals);
	}

// ----------------------------------
// CREATION
// ----------------------------------
	@Test
	void testAManagedTerminalIsCreated() {

		// Assert
		assertEquals(0, buenosAires.getConsignees().size());
		assertEquals(0, buenosAires.getExportOrders().size());
		assertEquals(positionBuenosAires, buenosAires.getGeographicalPosition());
		assertEquals(0, buenosAires.getImportOrders().size());
		assertEquals(ferFewerIntermediateTerminals, buenosAires.getRouting());
		assertEquals(0, buenosAires.getShippers().size());
		assertEquals(0, buenosAires.getShippingCompanies().size());
		assertEquals(0, buenosAires.getTruckTransportCompanies().size());
		assertEquals(0, buenosAires.getTurns().size());
	}

// ----------------------------------
// METHODS TO ADD TO MANAGED TERMINAL
// ----------------------------------
	@Test
	void testAManagedTerminalRegistersAConsignee() {
		// Exercise
		buenosAires.registerConsignee(consigneeGabriela);
		// Assert
		assertEquals(1, buenosAires.getConsignees().size());
	}

	@Test
	void testAManagedTerminalRegistersAShipper() {
		// Exercise
		buenosAires.registerShipper(shipperAlejandra);
		// Assert
		assertEquals(1, buenosAires.getShippers().size());
	}

	@Test
	void testAManagedTerminalRegistersAShippingCompany() {
		// Exercise
		buenosAires.registerShippingCompany(apmMaersk);
		// Assert
		assertEquals(1, buenosAires.getShippingCompanies().size());
	}

	@Test
	void testAManagedTerminalRegistersATruckTransportCompany() {
		// Exercise
		buenosAires.registerTruckTransportCompany(transporteVesprini);
		// Assert
		assertEquals(1, buenosAires.getTruckTransportCompanies().size());
	}

// ----------------------------------
// EXPORT
// ----------------------------------
	@Test
	void testTheExportOrderIsNotRegisteredBecauseTheDriverIsNotRegistered() {
		// Set Up
		when(transporteVesprini.getDrivers()).thenReturn(List.of());
		// Assert
		assertThrows(RuntimeException.class, () -> {
			buenosAires.hireExportService(exportOrderOfAlejandra);
		});
	}

	@Test
	void testTheExportOrderIsNotRegisteredBecauseTheTruckIsNotRegistered() {
		// Set Up
		when(transporteVesprini.getDrivers()).thenReturn(List.of());
		// Assert
		assertThrows(RuntimeException.class, () -> {
			buenosAires.hireExportService(exportOrderOfAlejandra);
		});
	}

	@Test
	void testTheShipperIsNotRegisteredInTheManagedTerminaThereforeItIsRegistered() {
		// Excerise
		buenosAires.registerTruckTransportCompany(transporteVesprini);
		buenosAires.hireExportService(exportOrderOfAlejandra);
		// Assert
		assertTrue(buenosAires.getShippers().contains(shipperAlejandra));
	}

	@Test
	void testTheExportOrderIsValidAndAShiftIsAssigned() {
		// Excerise
		buenosAires.hireExportService(exportOrderOfAlejandra);
		// Assert
		assertEquals(LocalDateTime.of(2023, 12, 10, 10, 10), buenosAires.getTurns().get(0).getDateTurn());
		assertEquals(1, buenosAires.getTurns().size());

	}

// ----------------------------------
// IMPORT
// ----------------------------------

// ----------------------------------
// WHEN TRUCK ARRIVED WITH LOAD
// ----------------------------------

// ----------------------------------
// WHEN TRUCK LEAVE WITH LOAD
// ----------------------------------
}
