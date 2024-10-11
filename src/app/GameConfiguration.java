package app;

import javax.swing.ImageIcon;

public class GameConfiguration {
	private GameModes gameMode;
	private ImageIcon player1Icon;
	private ImageIcon player2Icon;

	public GameConfiguration(GameModes gameMode){
		this.setGameMode(gameMode);
	}

	public GameModes getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameModes gameMode) {
		this.gameMode = gameMode;
	}

	public ImageIcon getPlayer1Icon() {
		return player1Icon;
	}

	public void setPlayer1Icon(ImageIcon player1Icon) {
		this.player1Icon = player1Icon;
	}

	public ImageIcon getPlayer2Icon() {
		return player2Icon;
	}

	public void setPlayer2Icon(ImageIcon player2Icon) {
		this.player2Icon = player2Icon;
	}
}
