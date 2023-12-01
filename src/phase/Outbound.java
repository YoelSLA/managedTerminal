package phase;

import ship.Ship;

public class Outbound implements Phase {
	
	@Override
	public Boolean canItMoveOnToTheNext(Ship ship) {
		return ship.getPosition().distanceInKilometersBetween(ship.getPosition(), ship.getTerminal().getPosition()) < 50;
	}
	
	@Override
	public Inbound nextPhase() {
		return new Inbound();
	}

	@Override
	public void notifyToTerminal(Ship ship) {		
	}
}

