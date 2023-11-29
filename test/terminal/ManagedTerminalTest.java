package terminal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.Consignee;
import client.Shipper;
import driver.Driver;
import maritimeCircuit.MaritimeCircuit;
import order.ExportOrder;
import routing.Routing;
import shippingLine.ShippingLine;
import stretch.Stretch;
import tracker.Tracker;
import trip.Trip;
import truck.Truck;
import truckTransportCompany.TruckTransportCompany;

class ManagedTerminalTest {

	private Terminal montevideo;
	private Terminal rioDeJaneiro;
	// ------------------------------------------------------------
	private Stretch buenosAiresMontevideo;
	private Stretch montevideoRioDeJaneiro;
	private Stretch rioDeJaneiroBuenosAires;
	// ------------------------------------------------------------
	private MaritimeCircuit circuitBuenosAiresRioDeJaneiro;
	// ------------------------------------------------------------
	private ShippingLine apmMaersk;
	// ------------------------------------------------------------
	private Driver driverAlberto;
	// ------------------------------------------------------------
	private Truck volvoTruck;
	// ------------------------------------------------------------
	private TruckTransportCompany transportVesprini;
	// ------------------------------------------------------------
	private Consignee consigneeGabriela;
	// ------------------------------------------------------------
	private Shipper shipperAlejandra;
	// ------------------------------------------------------------
	private ExportOrder exportOrderOfAlejandra;
	// ------------------------------------------------------------
	private Trip tripBuenosAiresRioDeJaneiro;
	// ------------------------------------------------------------
	private Routing ferFewerIntermediateTerminals;
	// ------------------------------------------------------------
	private ManagedTerminal buenosAires; // SUT

	@BeforeEach
	void setUp() {
		// TERMINAL
		montevideo = mock(Terminal.class);
		rioDeJaneiro = mock(Terminal.class);
		// ------------------------------------------------------------------------------------------
		// STRETCH
		buenosAiresMontevideo = mock(Stretch.class);
		when(buenosAiresMontevideo.getOrigin()).thenReturn(buenosAires);
		when(buenosAiresMontevideo.getDestiny()).thenReturn(montevideo);
		when(buenosAiresMontevideo.getTime()).thenReturn(Duration.ofHours(3));

		montevideoRioDeJaneiro = mock(Stretch.class);
		when(montevideoRioDeJaneiro.getOrigin()).thenReturn(montevideo);
		when(montevideoRioDeJaneiro.getDestiny()).thenReturn(rioDeJaneiro);
		when(montevideoRioDeJaneiro.getTime()).thenReturn(Duration.ofHours(8));

		rioDeJaneiroBuenosAires = mock(Stretch.class);
		when(rioDeJaneiroBuenosAires.getOrigin()).thenReturn(rioDeJaneiro);
		when(rioDeJaneiroBuenosAires.getDestiny()).thenReturn(buenosAires);
		when(rioDeJaneiroBuenosAires.getTime()).thenReturn(Duration.ofHours(16));
		// ------------------------------------------------------------------------------------------
		// MARITIME CIRCUIT
		circuitBuenosAiresRioDeJaneiro = mock(MaritimeCircuit.class);
		when(circuitBuenosAiresRioDeJaneiro.getStretches())
				.thenReturn(List.of(buenosAiresMontevideo, montevideoRioDeJaneiro, rioDeJaneiroBuenosAires));
		// ------------------------------------------------------------------------------------------
		// SHIPPING LINE
		apmMaersk = spy(new ShippingLine("30234051497", "APM Maersk"));
		when(apmMaersk.getMaritimeCircuits()).thenReturn(List.of(circuitBuenosAiresRioDeJaneiro));
		// ------------------------------------------------------------------------------------------
		// DRIVER
		driverAlberto = mock(Driver.class);
		// ------------------------------------------------------------------------------------------
		// TRUCK
		volvoTruck = mock(Truck.class);
		// ------------------------------------------------------------------------------------------
		// TRUCK TRANSPORT COMPANY
		transportVesprini = mock(TruckTransportCompany.class);
		when(transportVesprini.getDrivers()).thenReturn(Arrays.asList(driverAlberto));
		when(transportVesprini.getTrucks()).thenReturn(Arrays.asList(volvoTruck));
		// ------------------------------------------------------------------------------------------
		// CONSIGNEE
		consigneeGabriela = mock(Consignee.class);
		// ------------------------------------------------------------------------------------------
		// SHIPPER
		shipperAlejandra = mock(Shipper.class);
		// ------------------------------------------------------------------------------------------
		// EXPORT ORDER
		exportOrderOfAlejandra = mock(ExportOrder.class);
		when(exportOrderOfAlejandra.getShipper()).thenReturn(shipperAlejandra);
		when(exportOrderOfAlejandra.getDriver()).thenReturn(driverAlberto);
		when(exportOrderOfAlejandra.getTruck()).thenReturn(volvoTruck);
		when(exportOrderOfAlejandra.getTrip()).thenReturn(tripBuenosAiresRioDeJaneiro);
		// ------------------------------------------------------------------------------------------
		// TRIP
		tripBuenosAiresRioDeJaneiro = mock(Trip.class);
		when(tripBuenosAiresRioDeJaneiro.getStartDate()).thenReturn(LocalDateTime.of(2023, 11, 26, 10, 0));
		when(tripBuenosAiresRioDeJaneiro.getMaritimeCircuit()).thenReturn(circuitBuenosAiresRioDeJaneiro);
		// ------------------------------------------------------------------------------------------
		// ROUTING
		ferFewerIntermediateTerminals = mock(Routing.class);
		// ------------------------------------------------------------------------------------------
		// MANAGED TERMINAL
		buenosAires = new ManagedTerminal(ferFewerIntermediateTerminals);
	}

	// ------------------------------------------------------------
	// INITIALIZE MANAGED TERMINAL
	// ------------------------------------------------------------
	@Test
	void testInitializeTerminal_ConsignedEntities_ShouldHaveEmptyConsigneesList() {
		assertEquals(0, buenosAires.getConsignees().size());
	}

	@Test
	void testInitializeTerminal_ConsignedEntities_ShouldHaveEmptyExportOrdersList() {
		assertEquals(0, buenosAires.getExportOrders().size());
	}

	@Test
	void tesIinitializeTerminal_ConsignedEntities_ShouldHaveEmptyShippersList() {
		assertEquals(0, buenosAires.getShippers().size());
	}

	@Test
	void testInitializeTerminal_ConsignedEntities_ShouldHaveEmptyShippingLinesList() {
		assertEquals(0, buenosAires.getShippingLines().size());
	}

	@Test
	void testInitializeTerminal_ConsignedEntities_ShouldHaveEmptyTruckTransportCompaniesList() {
		assertEquals(0, buenosAires.getTruckTransportCompanies().size());
	}

	@Test
	void testInitializeTerminal_ConsignedEntities_ShouldHaveEmptyTurnsList() {
		assertEquals(0, buenosAires.getTurns().size());
	}

	@Test
	void testInitializeTerminal_Name_ShouldBeExpectedName() {
		assertEquals("Puerto de Buenos Aires", buenosAires.getName());
	}

	@Test
	void testInitializeTerminal_Position_ShouldHaveExpectedAltitudeAndLatitude() {
		assertEquals(-58.373877081937, buenosAires.getPosition().getAltitude());
		assertEquals(-34.5795823299825, buenosAires.getPosition().getLatitude());
	}

	void testInitializeTerminal_Details_ShouldHaveExpectedRouting() {
		assertEquals(ferFewerIntermediateTerminals, buenosAires.getRouting());
	}

	void testInitializeTerminal_Details_ShouldHaveTrackerOfExpectedType() {
		assertEquals(Tracker.class, buenosAires.getTracker().getClass());
	}
}
