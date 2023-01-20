package de.unistuttgart.iste.sqa.pse.sheet10.homework.exercise1;

/**
 * This is the implementation of a Piece in Mensch�rgereDichNicht
 *
 * @author Sven Naber
 * @version 1.0
 */
public class Piece {
	Player owner;
	int id;
	Location position;

	/**
	 * Class constructor
	 * <p>
	 * position is default Location(Zone.START,0)
	 *
	 * @param owner the player owning the piece
	 * @param id    the id of the piece (1-4 RED; 5-8 BLUE; 9-12 YELLOW; 13-16 GREEN)
	 */
	public Piece(Player owner, int id) {
		this.owner = owner;
		this.id = id;
		this.position = new Location(Zone.START, 0);
	}

	/**
	 * Getter for position
	 *
	 * @returns position
	 */
	public Location getPosition() {
		return position;
	}

	public void setPosition(Location position) {
		this.position = position;
	}

	/**
	 * Checks whether Location of the piece and a given Location are the same
	 *
	 * @param otherLocation given Location
	 * @returns true if otherLocation == this.position
	 */
	public Boolean checkForCollision(Location otherLocation) {
		if (this.position.getZone() == otherLocation.getZone()) {
			if (this.position.getCoordinate( ) == otherLocation.getCoordinate()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Generates a new legal Location for a piece given a roll
	 * if there is none returns old Location
	 *
	 * @param roll Diceroll (1-6)
	 * @returns new legal Location if there is one this.position otherwise
	 */
	public Location newRolledLocation(int roll) {
		
		int Coordinate = this.position.getCoordinate();
		
		/*
		 * Goal: can move up 1 to 3 steps depending on position
		 */
		if (this.position.getZone() == Zone.GOAL) {
			if (roll <= 3 - this.position.getCoordinate( )) {
				return new Location(Zone.GOAL, this.position.getCoordinate( ) + roll);
			}
		}
		/*
		 * Start: if a 6 is rolled a piece can enter the field at its first position
		 */
		if (this.position.getZone() == Zone.START) {
			if (roll == 6) {
				return getStartPosition();
			}
		}
		/*
		 *Field: will enter goal depending on position, may also have no legal location
		 */
		if (this.position.getZone() == Zone.FIELD) {
			if (this.id <= 4) {
				if (Coordinate + roll <= 39) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate + roll <= 43) {
					return new Location(Zone.GOAL, Coordinate + roll - 40);
				}
			} else if (this.id <= 8) {
				if (Coordinate >= 10 && Coordinate + roll <= 39) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate >= 10 && Coordinate + roll > 39) {
					return new Location(Zone.FIELD, (Coordinate + roll) % 40);
				} else if (Coordinate < 10 && Coordinate + roll < 10) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate < 10 && Coordinate + roll <= 13) {
					return new Location(Zone.GOAL, Coordinate + roll - 10);
				}
			} else if (this.id <= 12) {
				if (Coordinate >= 20 && Coordinate + roll <= 39) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate >= 20 && Coordinate + roll > 39) {
					return new Location(Zone.FIELD, (Coordinate + roll) % 40);
				} else if (Coordinate < 20 && Coordinate + roll < 20) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate < 20 && Coordinate + roll <= 23) {
					return new Location(Zone.GOAL, Coordinate + roll - 20);
				}
			} else if (this.id <= 16) {
				if (Coordinate >= 30 && Coordinate + roll <= 39) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate >= 30 && Coordinate + roll > 39) {
					return new Location(Zone.FIELD, (Coordinate + roll) % 40);
				} else if (Coordinate < 30 && Coordinate + roll < 30) {
					return new Location(Zone.FIELD, Coordinate + roll);
				} else if (Coordinate < 30 && Coordinate + roll <= 33) {
					return new Location(Zone.GOAL, Coordinate + roll - 30);
				}
			}
		}
		return this.position;
	}
	
	/**
	 * returns the start position for its specific id.
	 */
	private Location getStartPosition() {
			if (this.id <= 4)
				return new Location(Zone.FIELD, 0);
			else if (this.id <= 8)
				return new Location(Zone.FIELD, 10);
			else if (this.id <= 12)
				return new Location(Zone.FIELD, 20);
			return new Location(Zone.FIELD, 30);
	}
}
