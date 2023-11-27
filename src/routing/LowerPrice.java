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
				.filter(circuit -> calculateRoutingBetween(origin, destiny, circuit) < circuit.getPrice())
				.min(Comparator.comparingDouble(circuit -> calculateRoutingBetween(origin, destiny, circuit)))
				.orElse(maritimeCircuits.get(0));
	}

	protected Double calculateRoutingBetween(ManagedTerminal origin, Terminal destiny,
			MaritimeCircuit maritimeCircuit) {
		return maritimeCircuit.getStretchs().subList(super.getPositionOfOriginInCircuit(
				origin, maritimeCircuit), super.getPositionOfDestinyInCircuit(destiny, maritimeCircuit)).stream()
				.mapToDouble(Stretch::getPrice).sum();

	}

}