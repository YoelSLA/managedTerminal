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

class Trip2Test {

	private Terminal quito = mock(Terminal.class);
	private Stretch buenosAiresSantiago;
	private Stretch santiagoQuito;
	private Stretch quitoLima;
	private Stretch limaCaracas;
	private Stretch caracasBuenosAires;
	private MaritimeCircuit maritimeCircuit;
	private Ship ship;
	private LocalDateTime startDate;
	private Trip trip;

	@BeforeEach
	void setUp() {
		// TERMINAL
		ManagedTerminal buenosAires = mock(ManagedTerminal.class);
		Terminal santiago = mock(Terminal.class);
		Terminal lima = mock(Terminal.class);
		Terminal caracas = mock(Terminal.class);

		// STRETCH
		buenosAiresSantiago = mock(Stretch.class);
		santiagoQuito = mock(Stretch.class);
		quitoLima = mock(Stretch.class);
		limaCaracas = mock(Stretch.class);
		caracasBuenosAires = mock(Stretch.class);

		when(buenosAiresSantiago.getOrigin()).thenReturn(buenosAires);
		when(buenosAiresSantiago.getDestiny()).thenReturn(santiago);
		when(buenosAiresSantiago.getTime()).thenReturn(Duration.ofHours(3));

		when(santiagoQuito.getOrigin()).thenReturn(santiago);
		when(santiagoQuito.getDestiny()).thenReturn(quito);
		when(santiagoQuito.getTime()).thenReturn(Duration.ofHours(5));

		when(quitoLima.getOrigin()).thenReturn(quito);
		when(quitoLima.getDestiny()).thenReturn(lima);
		when(quitoLima.getTime()).thenReturn(Duration.ofHours(7));

		when(limaCaracas.getOrigin()).thenReturn(lima);
		when(limaCaracas.getDestiny()).thenReturn(caracas);
		when(limaCaracas.getTime()).thenReturn(Duration.ofHours(9));

		when(caracasBuenosAires.getOrigin()).thenReturn(caracas);
		when(caracasBuenosAires.getDestiny()).thenReturn(buenosAires);
		when(caracasBuenosAires.getTime()).thenReturn(Duration.ofHours(7));

		// MARITIME CIRCUIT
		maritimeCircuit = mock(MaritimeCircuit.class);

		when(maritimeCircuit.getStretchs()).thenReturn(
				Arrays.asList(buenosAiresSantiago, santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires));

		when(maritimeCircuit.hasADestinyTerminal(Mockito.eq(quito))).thenReturn(true);

		// SHIP
		ship = mock(Ship.class);

		// TRIP
		startDate = LocalDateTime.of(2023, 11, 26, 10, 0);
		trip = new Trip(maritimeCircuit, ship, startDate);
	}

	@Test
	void testATripIsCreated() {
		// Assert
		assertEquals(maritimeCircuit, trip.getMaritimeCircuit());
		assertEquals(ship, trip.getShip());
		assertEquals(startDate, trip.getStartDate());

	}

	@Test
	void testATripHasACertainTerminal() {
		System.out.println("Destiny: " + quito); // Verifica que el terminal de destino sea el correcto
		assertTrue(trip.hasADestinyTerminal(quito));

	}

	@Test
	void x() {
		LocalDateTime fecha = LocalDateTime.of(2023, 11, 26, 18, 0);
		System.out.println("Destiny: " + quito); // Verifica que el terminal de destino sea el correcto
		System.out.println("FECHA: " + fecha); // Verifica que el terminal de destino sea el correcto
		LocalDateTime fechaOBTENIDA = trip.dateArrivedToDestiny(quito);
		System.out.println("FECHA_OBTENIDA: " + fechaOBTENIDA); // Verifica que el terminal de destino sea el correcto
		assertEquals(fecha, fechaOBTENIDA);

	}

}
