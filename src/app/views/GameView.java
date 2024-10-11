package app.views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.App;
import app.GameConfiguration;
import app.GameModes;
import app.SourceManager;
import app.components.GameBoardButton;
import reversi.*;

public class GameView extends View {

	
	Reversi game;
	GameBoardButton boardButtons[][];
	
	GameConfiguration gameConfig;

	@Override
	public void before() {
		this.gameConfig = App.sharedInstance.getCurrentGameConfiguration();

 		game = new Reversi();
		render(game.getGameBoardGridCurrentState());

		// TODO:load up the characters icons in play
	}

	public GameView(String name) {
		super(name);

		JPanel board = new JPanel();
		board.setBounds(147, 195, 511, 511);
		board.setOpaque(false);
		board.setLayout(new GridLayout(8, 8));
		boardButtons = new GameBoardButton[8][8];

		// fill width buttons
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				GameBoardButton boardButton = new GameBoardButton();
				boardButton.setActionCommand("click:" + x + "," + y);
				boardButton.addActionListener(this);
				boardButtons[x][y] = boardButton;
				board.add(boardButton);
			}
		}

		add(board);

		JLabel background = new JLabel();
		background.setBounds(0, -8, 805, 861);
		background.setBackground(Color.red);
		background.setOpaque(true);
		background.setIcon(SourceManager.getSpriteImage("GameBoard"));
		add(background);
	}

	private void render(Disc board[][]) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Disc disc = board[x][y];
				if (disc == null)
					continue;
				if (disc.getColor() == DiscColors.black) {
					boardButtons[x][y].setIcon(gameConfig.getPlayer1Icon());
				} else {
					boardButtons[x][y].setIcon(gameConfig.getPlayer2Icon());
				}
			}
		}
	}

	private void playerClick(int x, int y) {
		// update the turn indicator label
		MoveStatus status = game.playerPutADisc(x, y);
		if (status.wasALegalMove) {
			render(status.boardGridCurrentState);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionComand = ((JButton) e.getSource()).getActionCommand();
		String command = actionComand.split(":")[0];
		if (command.equals("click")) {
			// click:x,y
			String data = actionComand.split(":")[1];
			int x = Integer.parseInt(data.split(",")[0]);
			int y = Integer.parseInt(data.split(",")[1]);
			playerClick(x, y);
		} else if (command.equals("home")) {

		}
	}
}
