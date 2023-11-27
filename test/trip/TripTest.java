package trip;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import maritimeCircuit.MaritimeCircuit;
import ship.Ship;
import stretch.Stretch;
import terminal.ManagedTerminal;
import terminal.Terminal;

class TripTest {

	private Terminal quito = mock(Terminal.class);
	private ManagedTerminal buenosAires = mock(ManagedTerminal.class);
	private Stretch buenosAiresSantiago;
	private Stretch santiagoQuito;
	private Stretch quitoLima;
	private Stretch limaBuenosAires;
	private MaritimeCircuit maritimeCircuit;
	private Ship ship;
	private LocalDateTime startDate;
	private Trip trip;

	@BeforeEach
	void setUp() {
		// TERMINAL
		Terminal santiago = mock(Terminal.class);
		Terminal lima = mock(Terminal.class);

		when(buenosAires.getName()).thenReturn("Puerto de Buenos Aires");
		when(santiago.getName()).thenReturn("Puerto de Santiago");
		when(quito.getName()).thenReturn("Puerto de Quito");
		when(lima.getName()).thenReturn("Puerto de Lima");

		// STRETCH
		buenosAiresSantiago = mock(Stretch.class);
		santiagoQuito = mock(Stretch.class);
		quitoLima = mock(Stretch.class);
		limaBuenosAires = mock(Stretch.class);

		when(buenosAiresSantiago.getOrigin()).thenReturn(buenosAires);
		when(buenosAiresSantiago.getDestiny()).thenReturn(santiago);
		when(buenosAiresSantiago.getTime()).thenReturn(Duration.ofHours(3));

		when(santiagoQuito.getOrigin()).thenReturn(santiago);
		when(santiagoQuito.getDestiny()).thenReturn(quito);
		when(santiagoQuito.getTime()).thenReturn(Duration.ofHours(5));

		when(quitoLima.getOrigin()).thenReturn(quito);
		when(quitoLima.getDestiny()).thenReturn(lima);
		when(quitoLima.getTime()).thenReturn(Duration.ofHours(7));

		when(limaBuenosAires.getOrigin()).thenReturn(lima);
		when(limaBuenosAires.getDestiny()).thenReturn(buenosAires);
		when(limaBuenosAires.getTime()).thenReturn(Duration.ofHours(15));

		// MARITIME CIRCUIT
		maritimeCircuit = mock(MaritimeCircuit.class);

		when(maritimeCircuit.getStretchs())
				.thenReturn(Arrays.asList(buenosAiresSantiago, santiagoQuito, quitoLima, limaBuenosAires));

		// SHIP
		ship = mock(Ship.class);

		// TRIP
		startDate = LocalDateTime.of(2023, 11, 26, 10, 0);
		trip = new Trip(maritimeCircuit, ship, startDate);
	}

	@Test
	void testATripIsCreated() {
		assertEquals(maritimeCircuit, trip.getMaritimeCircuit());
		assertEquals(ship, trip.getShip());
		assertEquals(startDate, trip.getStartDate());

	}

	@Test
	void testATripHasACertainTerminal() {
		// SetUp
		when(maritimeCircuit.hasADestinyTerminal(Mockito.eq(quito))).thenReturn(true);
		// Assert
		assertTrue(trip.hasADestinyTerminal(quito));
	}

	@Test
	void x() {
		// SetUp
		LocalDateTime arrivalDate = LocalDateTime.of(2023, 11, 26, 18, 0);
		when(maritimeCircuit.originTerminal()).thenReturn(buenosAires);
		when(maritimeCircuit.calculateTimeBetween(buenosAires, quito)).thenReturn(8);
		// Assert
		assertEquals(arrivalDate, trip.dateArrivedToDestiny(quito));

	}

}
