package reversi;

public class Disc {
	DiscColors color;
	
	public Disc(DiscColors color) {
		this.color = color;
	}
	
	/**
	 * Makes a new Disc as a copy of an already existing Disc
	 * @param existingDisc the disc you wan't to copy
	 */
	public Disc(Disc existingDisc) {
		this.color = existingDisc.color;
	}
}
