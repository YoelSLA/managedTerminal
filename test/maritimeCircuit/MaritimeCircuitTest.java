package maritimeCircuit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stretch.Stretch;
import terminal.ManagedTerminal;
import terminal.Terminal;

class MaritimeCircuitTest {

	ManagedTerminal buenosAires = mock(ManagedTerminal.class);
	Terminal quito = mock(Terminal.class);
	private Stretch buenosAiresSantiago;
	private Stretch santiagoQuito;
	private Stretch quitoLima;
	private Stretch limaCaracas;
	private Stretch caracasBuenosAires;
	private MaritimeCircuit maritimeCircuit;

	@BeforeEach
	void setUp() {
		// TERMINAL
		Terminal santiago = mock(Terminal.class);
		Terminal lima = mock(Terminal.class);
		Terminal caracas = mock(Terminal.class);

		when(buenosAires.getName()).thenReturn("Puerto de Buenos Aires");
		when(santiago.getName()).thenReturn("Puerto de Santiago");
		when(quito.getName()).thenReturn("Puerto de Quito");
		when(lima.getName()).thenReturn("Puerto de Lima");
		when(caracas.getName()).thenReturn("Puerto de Caracas");

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

		when(limaCaracas.getOrigin()).thenReturn(lima);
		when(limaCaracas.getDestiny()).thenReturn(caracas);

		when(caracasBuenosAires.getOrigin()).thenReturn(caracas);
		when(caracasBuenosAires.getDestiny()).thenReturn(buenosAires);

		// MARITIME CIRCUIT
		maritimeCircuit = new MaritimeCircuit(
				List.of(buenosAiresSantiago, santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires));
	}

	@Test
	void testAMaritimeCircuitIsCreated() {
		assertEquals(List.of(buenosAiresSantiago, santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires),
				maritimeCircuit.getStretchs());
	}

	@Test
	void testAMaritimeCircuitHasACertainTerminal() {

		assertTrue(maritimeCircuit.hasADestinyTerminal(quito));
	}

	@Test
	void testNumberOfHoursBetweenTwoTerminals() {
		assertEquals(8, maritimeCircuit.calculateTimeBetween(buenosAires, quito));
	}

	@Test
	void testOriginTerminal() {
		assertEquals(buenosAires, maritimeCircuit.originTerminal());
	}
}
