package reversi;

/**
 * This class represents the result of a playerMove in the game, is use as a
 * medium to communicate to the front-end
 */
public class MoveStatus {
	final public boolean wasALegalMove;
	final public Disc boardGridCurrentState[][];

	/**
	 * 
	 * @param wasLegal boolean to represent if the movement takes effect
	 * @param board    a reference to the GameBoard, it copies the values in case it
	 *                 was a legal move
	 */
	public MoveStatus(boolean wasLegal, GameBoard board) {
		this.wasALegalMove = wasLegal;
		if (wasLegal) {
			this.boardGridCurrentState = copyGameBoardGrid(board);
		} else {
			this.boardGridCurrentState = null;
		}
	}

	/**
	 * This method makes sure to copy every value of the boardGrid inside a
	 * gameBoard, and not the references to the original values
	 * 
	 * @param gameBoard a gameBoard object
	 * @return Disc[][] a 2d Disc array that contains a copy off the original stored
	 *         in gameBoard
	 */
	private Disc[][] copyGameBoardGrid(GameBoard gameBoard) {
		int width = gameBoard.boardXWidth;
		int height = gameBoard.boardYHeight;
		Disc[][] copy = new Disc[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Disc value = new Disc(gameBoard.getDiscAtPosition(x, y));
				copy[x][y] = value;
			}
		}

		return copy;
	}
}
