package maritimeCircuit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stretch.Stretch;
import terminal.ManagedTerminal;
import terminal.Terminal;

class MaritimeCircuit2 {

	Terminal quito = mock(Terminal.class);
	ManagedTerminal buenosAires = mock(ManagedTerminal.class);
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

		// STRETCH
		buenosAiresSantiago = mock(Stretch.class);
		santiagoQuito = mock(Stretch.class);
		quitoLima = mock(Stretch.class);
		limaCaracas = mock(Stretch.class);
		caracasBuenosAires = mock(Stretch.class);

		when(buenosAiresSantiago.getOrigin()).thenReturn(buenosAires);
		when(buenosAiresSantiago.getDestiny()).thenReturn(santiago);

		when(santiagoQuito.getOrigin()).thenReturn(santiago);
		when(santiagoQuito.getDestiny()).thenReturn(quito);

		when(quitoLima.getOrigin()).thenReturn(quito);
		when(quitoLima.getDestiny()).thenReturn(lima);

		when(limaCaracas.getOrigin()).thenReturn(lima);
		when(limaCaracas.getDestiny()).thenReturn(caracas);

		when(caracasBuenosAires.getOrigin()).thenReturn(caracas);
		when(caracasBuenosAires.getDestiny()).thenReturn(buenosAires);

		// MARITIME CIRCUIT
		maritimeCircuit = new MaritimeCircuit(List.of(santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires));

	}

	@Test
	void testAMaritimeCircuitIsCreated() {
		assertEquals(List.of(santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires), maritimeCircuit.getStretchs());

	}

	@Test
	void testAMaritimeCircuitHasACertainTerminal() {

//		System.out.println("Destiny: " + quito); // Verifica que el terminal de destino sea el correcto
//		System.out.println("Circuit stretches: " + maritimeCircuit.getStretchs()); // Verifica los tramos del circuito

		assertTrue(maritimeCircuit.hasADestinyTerminal(quito));
	}

	@Test
	void x() {

		System.out.println("Destiny: " + quito); // Verifica que el terminal de destino sea el correcto
		System.out.println("origin: " + buenosAires); // Verifica que el terminal de destino sea el correcto
		System.out.println("RESULTADO: " + maritimeCircuit.calculateTimeBetween(buenosAires, quito)); // Verifica los
																										// tramos del
																										// circuito

		assertEquals(2, maritimeCircuit.calculateTimeBetween(buenosAires, quito));
	}

}
