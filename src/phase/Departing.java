package phase;

import ship.Ship;

public class Departing extends Phase {

	@Override
	protected Boolean canItGoToTheNextPhase(Ship ship) {
		return ship.calculateDistanceToTerminal() > 1;
	}

	@Override
	protected void communicateWithTerminal(Ship ship) {
		ship.nextTerminal();
		ship.notifyDeparture();
	}

	@Override
	protected Phase nextPhase() {
		return new Outbound();
	}

}
