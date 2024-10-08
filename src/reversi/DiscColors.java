package reversi;

public enum DiscColors {
	black,
	white;
	
	public static DiscColors invert(DiscColors color) {
		return color == DiscColors.black? DiscColors.white:DiscColors.black;
	}
}
