package reversi;

import java.util.ArrayList;

public class Reversi {
	GameBoard gameBoard;
	DiscColors activeTurn;
	int whiteDiscCount, blackDiscCount;
	ArrayList<Disc> whiteDiscs;
	ArrayList<Disc> blackDiscs;

	public Reversi(int boardXWidth, int boardYHeight) {
		this.gameBoard = new GameBoard(boardXWidth, boardYHeight);
		activeTurn = DiscColors.black;
		// find the center and put the 4 discs
		// handle odd numbers
	}

	public MoveStatus playerMove(int x, int y) {
		// check if is legal
		if (gameBoard.positionIsOutsideBoardGrid(x, y)) {
			// check if is a posible move
		}
		// create a new piece, based on who's turn is and put it in the board
		// return a moveStatus
	}

	public Position2D[] getPosibleMoves(DiscColors playerColor) {
		Position2D posibleMoves[] = null;
		// check the 8 directions

		return posibleMoves;
	}

	private Position2D checkDirecion(Position2D startPosition, int xIncrement, int yIncrement) {
		Position2D posibleMove = null;
		Disc iterationDisc = null;
		do{
			int nextX = startPosition.x() + xIncrement;
			int nextY = startPosition.y() + yIncrement;
			Position2D nextPosition = new Position2D(nextX, nextY);
			iterationDisc = gameBoard.getDiscAtPosition(nextPosition);
			if(iterationDisc == null) {
				posibleMove = nextPosition;
				continue;
			}else if(iterationDisc.color == activeTurn) {
				posibleMove = null;
				break;
			}
		}while(iterationDisc != null || iterationDisc.color != activeTurn);
		return posibleMove;
	}

	public static void main(String[] args) {
		// usage example
		Reversi game = new Reversi(8,8);
		
		//making moves, the interaction methods deduces which player made the move, checks if is legal, make the move update the state
		// and return a response with the information
		MoveStatus response = game.playerMove(1,5);
		if(response.wasALegalMove) {
			response.boardGridCurrentState; // Disc matrix array
			// update the display 
		}
		
        Position2D avaibleMoves[] = game.getPosibleMoves();
	}
}
