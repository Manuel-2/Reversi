package reversi;

import java.util.ArrayList;

public class Reversi {
	GameBoard gameBoard;
	DiscColors activePlayerTurnColor;
	ArrayList<MovementTrace> activePlayerPosibleMoves;
	private int whiteDiscCount, blackDiscCount;

	boolean gameOver;

	public Reversi() {
		this.gameBoard = new GameBoard(8, 8);
		activePlayerTurnColor = DiscColors.black;
		gameOver = false;
		whiteDiscCount = gameBoard.placedWhiteDiscs.size();
		blackDiscCount = gameBoard.placedBlackDiscs.size();
	}

	public Disc[][] getGameBoardGridCurrentState() {
		return ReversiUtils.copyGameBoardGrid(gameBoard);
	}

	public MoveStatus playerPutADisc(int x, int y) {
		boolean isAPossibleMove = false;
		Position2D playerNewDiscPosition = new Position2D(x, y);

		DiscColors enemyColor = DiscColors.invert(activePlayerTurnColor);

		// main case
		activePlayerPosibleMoves = calculatePosibleMoves(activePlayerTurnColor);
		for (MovementTrace trace : activePlayerPosibleMoves) {
			if (trace.finish.equals(playerNewDiscPosition)) {
				if (!isAPossibleMove) {
					isAPossibleMove = true;
					gameBoard.insertNewDisc(new Disc(activePlayerTurnColor, trace.finish), trace.finish);
				}
				for (Disc enemyDisc : trace.discTrace) {
					enemyDisc.color = activePlayerTurnColor;
					if (enemyColor == DiscColors.white) {
						gameBoard.placedWhiteDiscs.remove(enemyDisc);
						gameBoard.placedBlackDiscs.add(enemyDisc);
					} else if (enemyColor == DiscColors.black) {
						gameBoard.placedBlackDiscs.remove(enemyDisc);
						gameBoard.placedWhiteDiscs.add(enemyDisc);
					}

				}
			}
		}

		if (isAPossibleMove) {
			ArrayList<MovementTrace> enemyPlayerPossibleMoves = calculatePosibleMoves(enemyColor);
			if (enemyPlayerPossibleMoves.size() == 0) {
				activePlayerPosibleMoves = calculatePosibleMoves(activePlayerTurnColor);
				if (activePlayerPosibleMoves.size() == 0) {
					this.gameOver = true;
				} else {
					activePlayerTurnColor = DiscColors.invert(activePlayerTurnColor);
				}
			} else {
				activePlayerTurnColor = enemyColor;
			}

		}

		this.blackDiscCount = gameBoard.placedBlackDiscs.size();
		this.whiteDiscCount = gameBoard.placedWhiteDiscs.size();
		return new MoveStatus(isAPossibleMove, gameBoard, activePlayerTurnColor, this.gameOver);
	}

	public Position2D[] getPosibleMoves() {
		ArrayList<MovementTrace> posibleMoves = calculatePosibleMoves(this.activePlayerTurnColor);
		Position2D userResult[] = new Position2D[posibleMoves.size()];
		for (int i = 0; i < posibleMoves.size(); i++) {
			userResult[i] = posibleMoves.get(i).finish;
		}
		return userResult;
	}

	private ArrayList<MovementTrace> calculatePosibleMoves(DiscColors playerColor) {
		ArrayList<MovementTrace> posibleMoves = new ArrayList<MovementTrace>();

		ArrayList<Disc> activePlayerDiscs = null;
		if (playerColor == DiscColors.black) {
			activePlayerDiscs = gameBoard.placedBlackDiscs;
		} else if (playerColor == DiscColors.white) {
			activePlayerDiscs = gameBoard.placedWhiteDiscs;
		}

		for (Disc placedDisc : activePlayerDiscs) {
			for (int y = -1; y <= 1; y++) {
				for (int x = -1; x <= 1; x++) {
					if (x == 0 && y == 0)
						continue;
					MovementTrace posibleMove = checkDirecion(placedDisc.position, x, y, playerColor);
					if (posibleMove != null && gameBoard.isPositionInsideBoardGrid(posibleMove.finish)) {
						posibleMoves.add(posibleMove);
					}
				}
			}
		}

		return posibleMoves;
	}

	/**
	 * This methods finds a free spot where is possible to place a disc for an
	 * existing disc and direction, taking in consideration who's turn it is,
	 * returns null if is not possible to place a disc in the specified direction
	 * 
	 * @param startPosition the position of a disc to check
	 * @param xIncrement    this value is added in every step
	 * @param yIncrement    this value is added in every step
	 * @return Returns a MovementTrace if it finds a valid move, null if is not
	 *         possible at the specified position and direction
	 */
	private MovementTrace checkDirecion(Position2D startPosition, int xIncrement, int yIncrement,
			DiscColors playerColor) {
		Disc previusIterationDisc = gameBoard.getDiscAtPosition(startPosition);

		MovementTrace posibleMove = new MovementTrace(startPosition);

		int iterationX = startPosition.x() + xIncrement;
		int iterationY = startPosition.y() + yIncrement;
		Position2D iterationDiscPosition = new Position2D(iterationX, iterationY);
		// starts as the adjacent disc of the startPostion

		if (gameBoard.isPositionInsideBoardGrid(iterationDiscPosition)) {
			Disc iterationDisc = gameBoard.getDiscAtPosition(iterationDiscPosition);
			while (true) {
				if (iterationDisc == null) {
					if (previusIterationDisc.color != playerColor) {
						posibleMove.setFinish(iterationDiscPosition);
						return posibleMove;
					}
					break;
				} else if (iterationDisc.color == playerColor) {
					// Discards the move as possible or legal, and stops
					posibleMove = null;
					return posibleMove;
				}

				posibleMove.discTrace.add(iterationDisc);
				previusIterationDisc = iterationDisc;
				iterationX = iterationX + xIncrement;
				iterationY = iterationY + yIncrement;
				iterationDiscPosition = new Position2D(iterationX, iterationY);
				iterationDisc = gameBoard.getDiscAtPosition(iterationDiscPosition);
			}
		}

		return null;
	}

	public int getWhiteDiscCount() {
		return whiteDiscCount;
	}

	public int getBlackDiscCount() {
		return blackDiscCount;
	}
}
