package phase;

import ship.Ship;

public class Outbound extends Phase {

	@Override
	protected Boolean canItGoToTheNextPhase(Ship ship) {
		return ship.calculateDistanceToTerminal() < 50;
	}

	@Override
	public Phase nextPhase() {
		return new Inbound();
	}

}
