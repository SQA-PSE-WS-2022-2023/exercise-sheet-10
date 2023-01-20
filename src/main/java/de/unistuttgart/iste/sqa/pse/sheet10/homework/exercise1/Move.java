package de.unistuttgart.iste.sqa.pse.sheet10.homework.exercise1;

/**
 * This is the implementation of a Move in Mensch�rgereDichNicht
 *
 * @author Sven Naber
 * @version 1.0
 */
public class Move {
	Piece movedPiece;
	Piece displacedPiece;
	Location newLocation;

	/**
	 * Class constructor
	 *
	 * @param movedPiece  the piece that is to be moved
	 * @param newLocation the new location of the moved piece
	 */
	public Move(Piece movedPiece, Location newLocation) {
		this(movedPiece, newLocation, null);
	}

	/**
	 * Class constructor specifying a displaced piece
	 *
	 * @param movedPiece     the piece that is to be moved
	 * @param newLocation    the new location of the moved piece
	 * @param displacedPiece the enemy piece displaced by the move
	 */
	public Move(Piece movedPiece, Location newLocation, Piece displacedPiece) {
		this.movedPiece = movedPiece;
		this.newLocation = newLocation;
		this.displacedPiece = displacedPiece;
	}

	/*@
	  @ ensures movedPiece.position == newLocation
	  @ ensures displacedPiece != null => displacedPiece.position == new Location(Zone.START,0)
	  @*/
	/**
	 * Executes the move by updating positions of participating pieces
	 * and prints move to the console
	 */
	public void executeMove() {
		System.out.println("Old Position: " + this.movedPiece.getPosition() + " -> New Position: " + newLocation);
		if (this.displacedPiece != null) {
			displacedPiece.setPosition(new Location(Zone.START, 0));
			System.out.println(displacedPiece.owner.playerColour + "'s piece was removed!");
		}
		this.movedPiece.setPosition(newLocation);
	}
}
