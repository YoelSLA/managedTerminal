package phase;

import ship.Ship;

public class Working extends Phase {

	@Override
	protected Boolean canItGoToTheNextPhase(Ship ship) {
		return false;
	}

	protected void communicateWithTerminal(Ship ship) {
		ship.startWorking();
	}

	@Override
	protected Phase nextPhase() {
		return new Departing();
	}

}
