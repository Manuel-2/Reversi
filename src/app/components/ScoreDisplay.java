package app.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.App;
import app.GameConfiguration;
import app.SourceManager;

public class ScoreDisplay extends JPanel {

	GameConfiguration currentGameConfig;

	JLabel player1ScoreLabel;
	JLabel player2ScoreLabel;

	JPanel scoreColorBar;
	JLabel scoreColorBarPoints[];

	boolean inGame;

	// this component is only instaciated when the program enters the game view so,
	// the configuration exists for sure
	public ScoreDisplay() {
		inGame = false;

		scoreColorBarPoints = new JLabel[64];

		setLayout(new FlowLayout());

		player2ScoreLabel = new JLabel();
		player2ScoreLabel.setText("99");
		player2ScoreLabel.setHorizontalTextPosition(JLabel.LEFT);
		player2ScoreLabel.setFont(SourceManager.appFont.deriveFont(26f));
		player2ScoreLabel.setIconTextGap(-5);
		add(player2ScoreLabel);

		// -------------

		scoreColorBar = new JPanel();
		scoreColorBar.setPreferredSize(new Dimension(100, 448));
		scoreColorBar.setLayout(new BoxLayout(scoreColorBar, BoxLayout.Y_AXIS));
		scoreColorBar.setOpaque(false);

		for (int i = 0; i < 64; i++) {
			JLabel barPoint = new JLabel(" ");
			Dimension labelSize = new Dimension(40, 7);
			barPoint.setPreferredSize(labelSize);
			barPoint.setMinimumSize(labelSize);
			barPoint.setMaximumSize(labelSize);
			barPoint.setAlignmentX(JLabel.CENTER_ALIGNMENT);

			// scoreColorBar.add(Box.createRigidArea(new Dimension(0, 0)));
			scoreColorBar.add(barPoint);
			scoreColorBarPoints[i] = barPoint;
		}
		add(scoreColorBar);

		// ---------

		player1ScoreLabel = new JLabel();
		player1ScoreLabel.setText("99");
		player1ScoreLabel.setHorizontalTextPosition(JLabel.RIGHT);

		player1ScoreLabel.setFont(SourceManager.appFont.deriveFont(26f));

		player1ScoreLabel.setIconTextGap(-5);
		add(player1ScoreLabel);
	}

	public void updateConfiguration() {
		currentGameConfig = App.sharedInstance.getCurrentGameConfiguration();
		player2ScoreLabel.setIcon(currentGameConfig.getPlayer2Icon());
		player2ScoreLabel.setForeground(
				GameConfiguration.charactersNames2Colors.get(currentGameConfig.getPlayer2CharacterName()));

		player1ScoreLabel.setIcon(currentGameConfig.getPlayer1Icon());
		player1ScoreLabel.setForeground(
				GameConfiguration.charactersNames2Colors.get(currentGameConfig.getPlayer1CharacterName()));
	}

	public void updatePlayerScore(int player1Score, int player2Score) {

		player1ScoreLabel.setText(player1Score + "");
		player2ScoreLabel.setText(player2Score + "");

		for (int i = 0; i < 64; i++) {
			JLabel pointBar = scoreColorBarPoints[i];
			pointBar.setOpaque(false);

			if (i < player2Score) {
				Color playerColor = GameConfiguration.charactersNames2Colors
						.get(currentGameConfig.getPlayer2CharacterName());
				if (i % 2 == 0) {
					playerColor = playerColor.darker();
				}
				pointBar.setOpaque(true);
				pointBar.setBackground(playerColor);
			} else if (i >= (64 - player1Score)) {
				Color playerColor = GameConfiguration.charactersNames2Colors
						.get(currentGameConfig.getPlayer1CharacterName());
				if (i % 2 == 0) {
					playerColor = playerColor.darker();
				}
				pointBar.setBackground(playerColor);
				pointBar.setOpaque(true);
			}
		}
	}
}
