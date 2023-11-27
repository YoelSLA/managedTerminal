package routing;

import java.util.List;
import java.util.Optional;

import maritimeCircuit.MaritimeCircuit;
import terminal.ManagedTerminal;
import terminal.Terminal;

public class FewerIntermediateTerminals extends Routing {

	@Override
	public MaritimeCircuit bestCircuitBetween(ManagedTerminal origin, Terminal destiny,
			List<MaritimeCircuit> maritimeCircuits) {

		validateMaritimeCircuits(maritimeCircuits);
		validateTerminalDestinyIn(destiny, maritimeCircuits);
		Optional<MaritimeCircuit> circuitWithDestiny = maritimeCircuits.stream()
				.min((circuit1, circuit2) -> Integer.compare(
						calculateRoutingBetween(origin, destiny, circuit1).intValue(),
						calculateRoutingBetween(origin, destiny, circuit2).intValue()));
		return circuitWithDestiny.get();

	}

	protected Double calculateRoutingBetween(ManagedTerminal origin, Terminal destiny, MaritimeCircuit maritimeCircuit) {
		return (double) Math.abs(super.getPositionOfDestinyInCircuit(origin, maritimeCircuit)
				- super.getPositionOfDestinyInCircuit(destiny, maritimeCircuit));
	}

}
