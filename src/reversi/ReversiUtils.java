package reversi;

class ReversiUtils {
	public static Disc[][] copyGameBoardGrid(GameBoard gameBoard) {
		int width = gameBoard.boardXWidth;
		int height = gameBoard.boardYHeight;
		Disc[][] copy = new Disc[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(gameBoard.getDiscAtPosition(x, y) != null) {
					Disc value = new Disc(gameBoard.getDiscAtPosition(x, y));
					copy[x][y] = value;
				}else {
					copy[x][y] = null;
				}
			}
		}

		return copy;
	}
}
