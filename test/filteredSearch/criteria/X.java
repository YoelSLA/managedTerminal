package filteredSearch.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import maritimeCircuit.MaritimeCircuit;
import search.criteria.ArrivalDate;
import stretch.Stretch;
import terminal.ManagedTerminal;
import terminal.Terminal;
import trip.Trip;

class X {
	private ArrivalDate arrivalDate; // SUT
	private Trip tripOne;
	private Trip tripTwo;
	protected Stretch buenosAiresSantiago = mock(Stretch.class);
	protected Stretch santiagoQuito = mock(Stretch.class);
	protected Stretch quitoLima = mock(Stretch.class);
	protected Stretch limaCaracas = mock(Stretch.class);
	protected Stretch caracasBuenosAires = mock(Stretch.class);
	protected Stretch santiagoLima = mock(Stretch.class);
	private MaritimeCircuit maritimeCircuitOne;
	private MaritimeCircuit maritimeCircuitTwo;

	@BeforeEach
	void setUp() {
		tripOne = mock(Trip.class);
		tripTwo = mock(Trip.class);
		maritimeCircuitOne = mock(MaritimeCircuit.class);
		maritimeCircuitTwo = mock(MaritimeCircuit.class);
		ManagedTerminal buenosAires = mock(ManagedTerminal.class);
		Terminal santiago = mock(Terminal.class);
		Terminal quito = mock(Terminal.class);
		Terminal lima = mock(Terminal.class);
		Terminal caracas = mock(Terminal.class);
		maritimeCircuitOne = mock(MaritimeCircuit.class);
		maritimeCircuitTwo = mock(MaritimeCircuit.class);

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
		when(santiagoLima.getOrigin()).thenReturn(buenosAires);
		when(santiagoLima.getDestiny()).thenReturn(buenosAires);
		when(buenosAiresSantiago.getTime()).thenReturn(Duration.ofHours(3));
		when(santiagoQuito.getTime()).thenReturn(Duration.ofHours(5));
		when(quitoLima.getTime()).thenReturn(Duration.ofHours(7));
		when(limaCaracas.getTime()).thenReturn(Duration.ofHours(9));
		when(caracasBuenosAires.getTime()).thenReturn(Duration.ofHours(7));
		when(santiagoLima.getTime()).thenReturn(Duration.ofHours(3));
		when(maritimeCircuitOne.getStretchs()).thenReturn(
				Arrays.asList(buenosAiresSantiago, santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires));
		when(maritimeCircuitTwo.getStretchs())
				.thenReturn(Arrays.asList(buenosAiresSantiago, santiagoLima, limaCaracas, caracasBuenosAires));

		when(tripOne.getMaritimeCircuit()).thenReturn(maritimeCircuitOne);
		when(tripOne.getStartDate()).thenReturn(LocalDateTime.of(2023, 11, 26, 12, 0));
		when(tripTwo.getMaritimeCircuit()).thenReturn(maritimeCircuitTwo);
		when(tripTwo.getStartDate()).thenReturn(LocalDateTime.of(2023, 12, 1, 12, 0));

		arrivalDate = new ArrivalDate(LocalDateTime.of(2023, 11, 26, 20, 0), quito);
	}

	@Test
	void x() {

		assertEquals(List.of(tripOne), arrivalDate.filterTrips(List.of(tripOne, tripTwo)));
	}

}
