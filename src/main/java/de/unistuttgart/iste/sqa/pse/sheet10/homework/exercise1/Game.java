package de.unistuttgart.iste.sqa.pse.sheet10.homework.exercise1;

import java.util.LinkedList;
import java.util.List;

/**
 * This an implementation of the popular boardgame MenschaergereDichNicht
 */
public class Game {

	protected Boolean rolled6;
	protected List<Player> players = new LinkedList<Player>();

	public Game() {

		this.players.add(new Player(this, Colour.RED));
		this.players.add(new Player(this, Colour.BLUE));
		this.players.add(new Player(this, Colour.YELLOW));
		this.players.add(new Player(this, Colour.GREEN));
		this.rolled6 = false;
	}

	/*@
	  @ ensures iterates over players taking turns while no winner is found
	  @ ensures if a 6 was rolled the Player gets an extra turn
	  @ ensures checks for win condition after each turn and terminates game if it is met
	  @ ensures turns printed to console
	  @*/
	/**
	 * Starts a Game of Mensch�rgereDichNicht
	 */
	public void run() {
		
		Player winner = null;
		while (winner == null) {
			for (Player player : players) {
				int piecesInGoal = 0;
				do {
					this.show();
					player.takeTurn();
					piecesInGoal = 0;
					for (Piece piece : player.pieces) {
						if (piece.getPosition().getZone() == Zone.GOAL) {
							piecesInGoal += 1;
						}
					}
					if (piecesInGoal == 4) {
						break;
					}
				} while (rolled6);
				if (piecesInGoal == 4) {
					winner = player;
					this.show();
					System.out.println(player.playerColour + " won!");
					break;
				}
			}
		}
	}

	/**
	 * Returns the right letter for a colour
	 *
	 * @param colour the colour of the player (1-4 RED; 5-8 BLUE; 9-12 YELLOW; 13-16 GREEN)
	 * @returns letter for colour
	 */
	private String colourHelper(Colour colour) {
		if (colour == Colour.RED) {
			return "R";
		} else if (colour == Colour.BLUE) {
			return "B";
		} else if (colour == Colour.YELLOW) {
			return "Y";
		} else {
			return "G";
		}
	}

	/**
	 * Prints the state of the game to console
	 *
	 * @ensures state of the game is printed to console
	 */
	public void show() {
		String[] field = new String[40];
		String[] goal = new String[16];
		for (int i = 0; i < 40; i++) {
			field[i] = "X";
		}
		for (int i = 0; i < 16; i++) {
			goal[i] = "O";
		}
		int startRed = 0;
		int startBlue = 0;
		int startYellow = 0;
		int startGreen = 0;
		for (Player player : this.players) {
			for (Piece piece : player.pieces) {
				if (piece.getPosition().getZone() == Zone.FIELD) {
					field[piece.getPosition().coordinate] = colourHelper(piece.owner.playerColour);
				}
				if (piece.getPosition().getZone() == Zone.GOAL) {
					int goalNumber = piece.getPosition().coordinate;
					goalNumber += piece.owner.getStartIdByColour(piece.owner.playerColour);
					goalNumber -= 1;
					goal[goalNumber] = colourHelper(piece.owner.playerColour);
				}
				if (piece.getPosition().getZone() == Zone.START) {
					if (piece.owner.playerColour == Colour.RED) {
						startRed += 1;
					} else if (piece.owner.playerColour == Colour.BLUE) {
						startBlue += 1;
					} else if (piece.owner.playerColour == Colour.YELLOW) {
						startYellow += 1;
					} else {
						startGreen += 1;
					}
				}
			}
		}
		printToMonitor(startRed, startBlue, startYellow, startGreen, field, goal);
	}
	
	private void printToMonitor(int startRed, int startBlue, int startYellow, int startGreen, String[] field, String[] goal) {

		System.out.println("");
		System.out.println("Start RED " + startRed + " Pieces");
		System.out.println("Start BLUE " + startBlue + " Pieces");
		System.out.println("Start YELLOW " + startYellow + " Pieces");
		System.out.println("Start GREEN " + startGreen + " Pieces");
		System.out.println("");
		System.out.println("        " + field[18] + " " + field[19] + " " + field[20] + "        ");
		System.out.println("        " + field[17] + " " + goal[8] + " " + field[21] + "        ");
		System.out.println("        " + field[16] + " " + goal[9] + " " + field[22] + "        ");
		System.out.println("        " + field[15] + " " + goal[10] + " " + field[23] + "        ");
		System.out.println(
				"" + field[10] + " " + field[11] + " " + field[12] + " " + field[13] + " " + field[14] + " " + goal[11]
						+ " " + field[24] + " " + field[25] + " " + field[26] + " " + field[27] + " " + field[28] + "");
		System.out.println("" + field[9] + " " + goal[4] + " " + goal[5] + " " + goal[6] + " " + goal[7] + "   "
				+ goal[15] + " " + goal[14] + " " + goal[13] + " " + goal[12] + " " + field[29] + "");
		System.out.println(
				"" + field[8] + " " + field[7] + " " + field[6] + " " + field[5] + " " + field[4] + " " + goal[3] + " "
						+ field[34] + " " + field[33] + " " + field[32] + " " + field[31] + " " + field[30] + "");
		System.out.println("        " + field[3] + " " + goal[2] + " " + field[35] + "        ");
		System.out.println("        " + field[2] + " " + goal[1] + " " + field[36] + "        ");
		System.out.println("        " + field[1] + " " + goal[0] + " " + field[37] + "        ");
		System.out.println("        " + field[0] + " " + field[39] + " " + field[38] + "        ");
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {
		Game testgame1 = new Game();
		testgame1.run();
	}
}
