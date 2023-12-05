package phase;

import ship.Ship;

public class Arrived extends Phase {

	@Override
	protected Boolean canItGoToTheNextPhase(Ship ship) {
		return false;
	}

	@Override
	protected void communicateWithTerminal(Ship ship) {
		ship.notifyArrival();
	}

	@Override
	protected Phase nextPhase() {
		return new Working();
	}

}
