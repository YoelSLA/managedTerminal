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

class ArrivalDateTest {

	private Trip tripOne;
	private Trip tripTwo;
	private Stretch buenosAiresSantiago;
	private Stretch santiagoQuito;
	private Stretch quitoLima;
	private Stretch limaCaracas;
	private Stretch caracasBuenosAires;
	private Stretch santiagoLima;
	private ManagedTerminal buenosAires;
	private Terminal santiago;
	private Terminal quito;
	private Terminal lima;
	private Terminal caracas;
	private MaritimeCircuit maritimeCircuitOne;
	private MaritimeCircuit maritimeCircuitTwo;
	private ArrivalDate arrivalDate;

	@BeforeEach
	void setUp() {
		// TERMINAL
		buenosAires = mock(ManagedTerminal.class);
		santiago = mock(Terminal.class);
		quito = mock(Terminal.class);
		lima = mock(Terminal.class);
		caracas = mock(Terminal.class);

		// STRETCH
		buenosAiresSantiago = mock(Stretch.class);
		santiagoQuito = mock(Stretch.class);
		quitoLima = mock(Stretch.class);
		limaCaracas = mock(Stretch.class);
		caracasBuenosAires = mock(Stretch.class);
		santiagoLima = mock(Stretch.class);

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

		when(santiagoLima.getOrigin()).thenReturn(buenosAires);
		when(santiagoLima.getDestiny()).thenReturn(santiago);
		when(santiagoLima.getTime()).thenReturn(Duration.ofHours(3));

		// MARITIME CIRCUIT
		maritimeCircuitOne = mock(MaritimeCircuit.class);
		maritimeCircuitTwo = mock(MaritimeCircuit.class);

		when(maritimeCircuitOne.getStretchs()).thenReturn(
				Arrays.asList(buenosAiresSantiago, santiagoQuito, quitoLima, limaCaracas, caracasBuenosAires));
		when(maritimeCircuitTwo.getStretchs())
				.thenReturn(Arrays.asList(buenosAiresSantiago, santiagoLima, limaCaracas, caracasBuenosAires));

		// TRIP
		tripOne = mock(Trip.class);
		when(tripOne.getMaritimeCircuit()).thenReturn(maritimeCircuitOne);
		when(tripOne.getStartDate()).thenReturn(LocalDateTime.of(2023, 11, 26, 12, 0));

		tripTwo = mock(Trip.class);
		when(tripTwo.getMaritimeCircuit()).thenReturn(maritimeCircuitTwo);
		when(tripTwo.getStartDate()).thenReturn(LocalDateTime.of(2023, 12, 1, 12, 0));

		when(maritimeCircuitOne.calculateTimeBetween(buenosAires, quito)).thenReturn(8);

		arrivalDate = new ArrivalDate(LocalDateTime.of(2023, 11, 26, 20, 0), quito);
	}

	@Test
	void x() {
		System.out.println("TRIP_ONE: " + tripOne);
		System.out.println("TRIP_TWO: " + tripTwo);
		System.out.println("QUITO" + quito);

		assertEquals(List.of(tripOne), arrivalDate.filterTrips(List.of(tripOne, tripTwo)));
	}

}
