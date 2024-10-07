package reversi;

class Disc {
	DiscColors color;
	public final Position2D position;

	public Disc(DiscColors color, Position2D position) {
		this.color = color;
		this.position = position;
	}

	/**
	 * Makes a new Disc as a copy of an already existing Disc
	 * 
	 * @param existingDisc the disc you wan't to copy
	 */
	public Disc(Disc existingDisc) {
		if (existingDisc == null) {
			throw new IllegalArgumentException("Trying to create a new Disc based on a null pointer");
		}
		// Note: records are passed by reference, but all of its properties are final, and immutable
		this.position = existingDisc.position;
		this.color = existingDisc.color;
	}

	public DiscColors getColor() {
		return color;
	}

	public void setColor(DiscColors color) {
		// todo: update some static variable that stores the totality of disc
		this.color = color;
	}
}
