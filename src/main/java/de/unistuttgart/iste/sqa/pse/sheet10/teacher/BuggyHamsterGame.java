package de.unistuttgart.iste.sqa.pse.sheet10.teacher;

import de.hamstersimulator.objectsfirst.external.simple.game.SimpleHamsterGame;

/**
 * Hamster game which contains a bug. It's being used to demonstrate the debugging tools provided by the IDE.
 */
public class BuggyHamsterGame extends SimpleHamsterGame {

	public BuggyHamsterGame() {
		this.loadTerritoryFromResourceFile("/territories/teacher-territory.ter");
		this.displayInNewGameWindow();
		game.startGame();
	}

	@Override
	protected void run() {
		// TODO: Place breakpoint marker in NEXT line.
		paule.move();
		paule.move();
		paule.pickGrain();
		paule.pickGrain();
		paule.move();
	}
}
