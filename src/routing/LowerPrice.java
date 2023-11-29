package routing;

import java.util.Comparator;
import java.util.List;

import maritimeCircuit.MaritimeCircuit;
import stretch.Stretch;
import terminal.ManagedTerminal;
import terminal.Terminal;

public class LowerPrice extends Routing {

	@Override
	public MaritimeCircuit bestCircuitBetween(ManagedTerminal origin, Terminal destiny,
			List<MaritimeCircuit> maritimeCircuits) {

		validateMaritimeCircuits(maritimeCircuits);
		validateTerminalDestinyIn(destiny, maritimeCircuits);
		return maritimeCircuits.stream()
				.filter(circuit -> calculateRouting(origin, destiny, circuit) < circuit.getPrice())
				.min(Comparator.comparingDouble(circuit -> calculateRouting(origin, destiny, circuit)))
				.orElse(maritimeCircuits.get(0));
	}

	protected Double calculateRouting(ManagedTerminal origin, Terminal destiny, MaritimeCircuit maritimeCircuit) {
		return maritimeCircuit.getStretches()
				.subList(maritimeCircuit.getPositionOf(origin), maritimeCircuit.getPositionOf(destiny)).stream()
				.mapToDouble(Stretch::getPrice).sum();

	}

}