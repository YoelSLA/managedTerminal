package phase;

import ship.Ship;

public abstract class Phase {

	public void changePhase(Ship ship) {
		ship.setPhase(ship.getPhase().nextPhase());
	}

	public void proceedToNextPhase(Ship ship) {
		if (canItGoToTheNextPhase(ship)) {
			communicateWithTerminal(ship);
			changePhase(ship);
		}
	}

	protected void communicateWithTerminal(Ship ship) {
		// METODO HOOK
	}

	protected abstract Phase nextPhase();

	protected abstract Boolean canItGoToTheNextPhase(Ship ship);
}
