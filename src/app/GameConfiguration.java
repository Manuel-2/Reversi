package app;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.util.Map;

public class GameConfiguration {

	public static final Map<String, Color> charactersNames2Colors = Map.of(
			"Blue", Color.decode("#33ded6"), 
			"Green",Color.decode("#45de33"),
			"Orange", Color.decode("#ff9233"),
			"Pink",Color.decode("#de33a4"),
			"Yellow", Color.decode("#f6eb2c"), 
			"White",Color.white,
			"Red", Color.decode("#de3333"),
			"Purple",Color.decode("#6333de")
			);

	private GameModes gameMode;
	private ImageIcon player1Icon;
	private ImageIcon player2Icon;
	private String player1CharacterName;
	private String player2CharacterName;

	public GameConfiguration(GameModes gameMode) {
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

	public String getPlayer1CharacterName() {
		return player1CharacterName;
	}

	public void setPlayer1CharacterName(String player1CharacterName) {
		this.player1CharacterName = player1CharacterName;
	}

	public String getPlayer2CharacterName() {
		return player2CharacterName;
	}

	public void setPlayer2CharacterName(String player2CharacterName) {
		this.player2CharacterName = player2CharacterName;
	}
}
