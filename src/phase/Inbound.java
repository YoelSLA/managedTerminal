package phase;

import ship.Ship;

public class Inbound implements Phase {

	@Override
	public Boolean canItMoveOnToTheNext(Ship ship) {
		return ship.getPosition().distanceInKilometersBetween(ship.getPosition(), ship.getTerminal().getPosition()) == 0;
	}
	
	@Override
	public Arrived nextPhase() {
		return new Arrived();
	}

	@Override
	public void notifyToTerminal(Ship ship) {
		ship.notifyInminentArrival();
		
	}
}

