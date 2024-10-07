package reversi;

class GameBoard {
	public final int boardXWidth, boardYHeight;
	private final Disc boardGrid[][];

	public GameBoard(int boardXWidth, int boardYHeight) {
		this.boardXWidth = boardXWidth;
		this.boardYHeight = boardYHeight;
		boardGrid = new Disc[boardXWidth][boardYHeight];
	}

	public void insertNewDisc(Disc disc, int x, int y) {
		if (isPositionOutsideBoardGrid(x, y)) {
			throw new IndexOutOfBoundsException("Out of bounds position, can't write outside of the boardGrid");
		}
		if (getDiscAtPosition(x, y) != null) {
			throw new IllegalArgumentException(
					"This method is to insert a new disc only, if you want to modify a disc use the getDiscMethod that returns a reference to the disc and then edit it's properties");
		}
		if (disc == null) {
			throw new IllegalArgumentException("You can't insert a null pointer to the Disc boardGrid");
		}
		this.boardGrid[x][y] = disc;
	}

	public void insertNewDisc(Disc disc, Position2D position) {
		if (isPositionOutsideBoardGrid(position)) {
			throw new IndexOutOfBoundsException("Out of bounds position, can't write outside of the boardGrid");
		}
		if (getDiscAtPosition(position) != null) {
			throw new IllegalArgumentException(
					"This method is to insert a new disc only, if you want to modify a disc use the getDiscMethod that returns a reference to the disc and then edit it's properties");
		}
		if (disc == null) {
			throw new IllegalArgumentException("You can't insert a null pointer to the Disc boardGrid");
		}
		this.boardGrid[position.x()][position.y()] = disc;

	}

	public Disc getDiscAtPosition(int x, int y) {
		if (isPositionOutsideBoardGrid(x, y)) {
			throw new IndexOutOfBoundsException("Out of bounds position");
		}
		return boardGrid[x][y];
	}

	public Disc getDiscAtPosition(Position2D position) {
		int x = position.x();
		int y = position.y();
		if (isPositionOutsideBoardGrid(position)) {
			throw new IndexOutOfBoundsException("Out of bounds position");
		}
		return boardGrid[x][y];
	}

	public boolean isPositionOutsideBoardGrid(int x, int y) {
		return (x >= boardXWidth || x < 0 || y >= boardYHeight || y < 0);
	}

	public boolean isPositionOutsideBoardGrid(Position2D position) {
		int x = position.x();
		int y = position.y();
		return (x >= boardXWidth || x < 0 || y >= boardYHeight || y < 0);
	}
}
