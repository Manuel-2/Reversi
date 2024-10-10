package app.views;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import app.GameConfigurations;
import reversi.*;

public abstract class GameView extends View {

	GameConfigurations gameConfig;
	Reversi game;

	public GameView(String name, GameConfigurations gameConfig) {
		super(name);
		this.gameConfig = gameConfig;
		JPanel board = new JPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
