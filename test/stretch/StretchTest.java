package stretch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import terminal.ManagedTerminal;
import terminal.Terminal;

class StretchTest {

	private ManagedTerminal buenosAires;
//-------------------------------------------------------------
	private Terminal santiago;
//-------------------------------------------------------------
	private Stretch buenosAiresSantiago;

	@BeforeEach
	void setUp() {
		// MANAGED TERMINAL
		buenosAires = mock(ManagedTerminal.class);
//-------------------------------------------------------------
		// TERMINAL
		santiago = mock(Terminal.class);
//-------------------------------------------------------------
		buenosAiresSantiago = new Stretch(buenosAires, santiago, 50.0, Duration.ofHours(6));

	}

	@Test
	void testAStrecthIsCreated() {
		assertEquals(santiago, buenosAiresSantiago.getDestiny());
		assertEquals(buenosAires, buenosAiresSantiago.getOrigin());
		assertEquals(50.0, buenosAiresSantiago.getPrice());
		assertEquals(Duration.ofHours(6), buenosAiresSantiago.getTime());
	}

}