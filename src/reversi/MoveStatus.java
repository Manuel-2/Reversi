package reversi;

/**
 * This class represents the result of a playerMove in the game, is use as a
 * medium to communicate to the front-end
 */
public class MoveStatus {
	final public boolean wasALegalMove;
	final public Disc boardGridCurrentState[][];
	final public DiscColors nextTurn;
	final public boolean gameIsOver;

	/**
	 * 
	 * @param wasLegal boolean to represent if the movement takes effect
	 * @param board    a reference to the GameBoard, it copies the values in case it
	 *                 was a legal move
	 */
	public MoveStatus(boolean wasLegal, GameBoard board, DiscColors nextTurn,boolean gameIsOver) {
		this.wasALegalMove = wasLegal;
		this.gameIsOver = gameIsOver;
		if (wasLegal) {
			this.boardGridCurrentState = ReversiUtils.copyGameBoardGrid(board);
		} else {
			this.boardGridCurrentState = null;
		}
		this.nextTurn = nextTurn;
	}

	/**
	 * This method makes sure to copy every value of the boardGrid inside a
	 * gameBoard, and not the references to the original values
	 * 
	 * @param gameBoard a gameBoard object
	 * @return Disc[][] a 2d Disc array that contains a copy off the original stored
	 *         in gameBoard
	 */
	
}
