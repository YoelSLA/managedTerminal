package phase;

import ship.Ship;

public class Inbound extends Phase {

	@Override
	protected Boolean canItGoToTheNextPhase(Ship ship) {
		return ship.calculateDistanceToTerminal() == 0;
	}

	@Override
	protected void communicateWithTerminal(Ship ship) {
		ship.notifyInminentArrival();
	}

	@Override
	protected Phase nextPhase() {
		return new Arrived();
	}

}
