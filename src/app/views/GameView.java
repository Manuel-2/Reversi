package app.views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.ObjectInputFilter.Config;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import app.App;
import app.GameConfiguration;
import app.GameModes;
import app.SourceManager;
import app.components.GameBoardButton;
import app.components.ScoreDisplay;
import reversi.*;

public class GameView extends View {

	Reversi game;
	GameBoardButton boardButtons[][];

	GameConfiguration gameConfig;

	JLabel statusDisplay;
	ScoreDisplay scoreDisplay;

	boolean player1Turn;
	boolean gameOver;

	@Override
	public void before() {
		this.gameConfig = App.sharedInstance.getCurrentGameConfiguration();
		scoreDisplay.updateConfiguration();

		game = new Reversi();
		player1Turn = true;
		gameOver = false;

		render(game.getGameBoardGridCurrentState());
	}

	public GameView(String name) {
		super(name);

		JPanel board = new JPanel();
		board.setBounds(147, 195, 511, 511);
		board.setOpaque(false);
		board.setLayout(new GridLayout(8, 8));
		boardButtons = new GameBoardButton[8][8];

		statusDisplay = new JLabel();
		statusDisplay.setBounds(125, 70, 555, 100);
		statusDisplay.setFont(SourceManager.appFont.deriveFont(30f));
		statusDisplay.setHorizontalAlignment(JLabel.CENTER);
		statusDisplay.setForeground(Color.white);
		add(statusDisplay);

		GameBoardButton returnHomeButton = new GameBoardButton();
		returnHomeButton.setIcon(SourceManager.getSpriteImageIcon("Home"));
		returnHomeButton.setLocation(7, 0);
		returnHomeButton.setActionCommand("home");
		returnHomeButton.addActionListener(this);
		add(returnHomeButton);

		GameBoardButton resetButton = new GameBoardButton();
		resetButton.setIcon(SourceManager.getSpriteImageIcon("Replay"));
		resetButton.setLocation(736, 76);
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(this);
		add(resetButton);

		JLabel reversiTitle = new JLabel("Reversi");
		reversiTitle.setBounds(100, 0, 600, 60);
		reversiTitle.setFont(SourceManager.appFont.deriveFont(50f));
		reversiTitle.setForeground(Color.white);
		add(reversiTitle);

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

		scoreDisplay = new ScoreDisplay();
		scoreDisplay.setBounds(15, 157, 103, 750);
		scoreDisplay.setOpaque(false);
		add(scoreDisplay);

		// always leave at the end
		JLabel background = new JLabel();
		background.setBounds(0, -8, 805, 861);
		background.setBackground(Color.red);
		background.setOpaque(true);
		background.setIcon(SourceManager.getSpriteImageIcon("GameBoard"));
		add(background);
	}

	private void render(Disc board[][]) {
		scoreDisplay.updatePlayerScore(game.getBlackDiscCount(), game.getWhiteDiscCount());

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				GameBoardButton button = boardButtons[x][y];
				button.setIcon(null);
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

		Position2D posibleMoves[] = game.getPosibleMoves();
		if (posibleMoves != null) {
			for (Position2D posibleMove : posibleMoves) {
				GameBoardButton button = boardButtons[posibleMove.x()][posibleMove.y()];
				// TODO:set a circle based on the color
				ImageIcon circleColored = SourceManager.getSpriteImageIcon("null");
				if (player1Turn) {
					circleColored = SourceManager.getSpriteImageIcon("Circle" + gameConfig.getPlayer1CharacterName());
				} else {
					circleColored = SourceManager.getSpriteImageIcon("Circle" + gameConfig.getPlayer2CharacterName());
				}
				button.setIcon(circleColored);
			}
		}

		statusDisplayPlayerTurn();
	}

	private void playerClick(int x, int y) {
		if (gameOver || (gameConfig.getGameMode() == GameModes.solo && !player1Turn))
			return;
		// update the turn indicator label
		MoveStatus status = game.playerPutADisc(x, y);
		if (status.wasALegalMove) {
			player1Turn = status.nextTurn == DiscColors.black;
			render(status.boardGridCurrentState);

			if (status.gameIsOver) {
				GameOver();
				return;
			}

			if (gameConfig.getGameMode() == GameModes.solo) {
				cpuPlayerPlays();
			}
		}
	}

	private void cpuPlayerPlays() {

		SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
			@Override
			protected String doInBackground() throws Exception {
				for (int i = 0; i < 4; i++) {
					String suspensivePoints = ".".repeat(i);
					statusDisplay.setText("CPU thinking " + suspensivePoints);
					Thread.sleep(500);
				}
				return "done";
			}

			@Override
			protected void done() {
				Position2D posibleMoves[] = game.getPosibleMoves();

				// the most sophisticated reversi bot
				int selectedMoveIndex = (int) (Math.random() * (posibleMoves.length));
				Position2D move = posibleMoves[selectedMoveIndex];

				MoveStatus status = game.playerPutADisc(move.x(), move.y());
				player1Turn = true;
				render(status.boardGridCurrentState);
				if (status.gameIsOver) {
					GameOver();
					return;
				}
			}

		};

		worker.execute();
	}

	private void GameOver() {
		gameOver = true;
		int player1Score = game.getBlackDiscCount();
		int player2Score = game.getWhiteDiscCount();

		statusDisplay.setForeground(Color.white);
		if (player1Score > player2Score) {
			statusDisplay.setText(gameConfig.getPlayer1CharacterName() + " wins!!!");
		} else if (player2Score > player1Score) {
			if (gameConfig.getGameMode() == GameModes.solo) {
				statusDisplay.setText("You Lost :(");
			} else {
				statusDisplay.setText(gameConfig.getPlayer2CharacterName() + " wins!!!");
			}
		} else {
			statusDisplay.setText("Draw");
		}
	}

	private void statusDisplayPlayerTurn() {
		if (player1Turn) {
			statusDisplay.setText("First Player turn");
			statusDisplay
					.setForeground(GameConfiguration.charactersNames2Colors.get(gameConfig.getPlayer1CharacterName()));
		} else {
			if (gameConfig.getGameMode() == GameModes.solo) {
				statusDisplay.setText("CPU Turn....");
			} else {
				statusDisplay.setText("Second Player Turn");
			}
			statusDisplay
					.setForeground(GameConfiguration.charactersNames2Colors.get(gameConfig.getPlayer2CharacterName()));
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
			App.sharedInstance.setView("MainMenu");
		} else if (command.equals("reset")) {
			App.sharedInstance.setView("Game");
		}
	}
}