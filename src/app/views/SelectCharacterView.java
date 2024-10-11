package app.views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.SourceManager;
import app.components.GoBackButton;
import app.components.MenuButton;
import app.components.SquaredButton;
import app.GameConfigurations;

public class SelectCharacterView extends View {

	final String charactersNames[] = new String[] { "Blue", "Green", "Orange", "Pink", "Purple", "Red", "White",
			"Yellow" };

	String player1CharacterName, player2CharacterName;
	JLabel player1SelectedCharacterLabel, player2SelectedCharacterLabel;
	Boolean player1Ready, player2Ready;

	JPanel characterGrid;
	Map<String, SquaredButton> charactersButtons = new HashMap<String, SquaredButton>();

	GameConfigurations gameConfig;

	@Override
	public void before() {
		if (player1CharacterName != null) {
			charactersButtons.get(player1CharacterName).enableButton();
		}
		if (player2CharacterName != null) {
			charactersButtons.get(player2CharacterName).enableButton();
		}
		player1CharacterName = null;
		player2CharacterName = null;
		player1SelectedCharacterLabel.setIcon(null);
		player2SelectedCharacterLabel.setIcon(null);
		player1Ready = false;
		player2Ready = false;
	}

	public SelectCharacterView(String name) {
		super(name);

		setOpaque(true);
		add(new GoBackButton());

		player1SelectedCharacterLabel = new JLabel("P1: ");
		player1SelectedCharacterLabel.setFont(SourceManager.appFont.deriveFont(30f));
		player1SelectedCharacterLabel.setBounds(60, 30, 200, 100);
		player1SelectedCharacterLabel.setForeground(Color.white);
		player1SelectedCharacterLabel.setHorizontalTextPosition(JLabel.LEFT);
		add(player1SelectedCharacterLabel);

		player2SelectedCharacterLabel = new JLabel("P2: ");
		player2SelectedCharacterLabel.setFont(SourceManager.appFont.deriveFont(30f));
		player2SelectedCharacterLabel.setBounds(805 - 280, 30, 200, 100);
		player2SelectedCharacterLabel.setForeground(Color.white);
		player2SelectedCharacterLabel.setHorizontalTextPosition(JLabel.LEFT);
		add(player2SelectedCharacterLabel);

		characterGrid = new JPanel();
		characterGrid.setBounds(805 / 2 - 685 / 2, 200, 685, 500);
		characterGrid.setOpaque(false);
		characterGrid.setLayout(new GridLayout(2, 4, 80, 10));

		for (String character : charactersNames) {
			SquaredButton selecCharacterButton = new SquaredButton(SourceManager.getSpriteImage(character), character,
					this);
			characterGrid.add(selecCharacterButton);
			charactersButtons.put(character, selecCharacterButton);
		}
		add(characterGrid);

		MenuButton select = new MenuButton("Select");
		select.setBounds(60, 700, select.getSize().width, select.getSize().height);
		select.addActionListener(this);
		add(select);

		MenuButton start = new MenuButton("Start");
		start.setBounds(486, 700, start.getSize().width, start.getSize().height);
		start.addActionListener(this);
		add(start);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();

		if (actionCommand == "Select") {
			if (!player1Ready) {
				if (player1CharacterName != null) {
					player1Ready = true;
					charactersButtons.get(player1CharacterName).disableButton();
				}
			} else {
				if (player2CharacterName != null) {
					player2Ready = true;
					charactersButtons.get(player2CharacterName).disableButton();
				}
			}
		} else if (actionCommand == "Start") {
			if (player1Ready && player2Ready) {
				// start the game
			}
		} else {
			// Here action Command is a character Name
			String charactername = actionCommand;
			if (!player1Ready) {
				player1CharacterName = charactername;
				player1SelectedCharacterLabel.setIcon(SourceManager.getSpriteImage(charactername));
			} else if (player1Ready && !player2Ready) {
				player2CharacterName = charactername;
				player2SelectedCharacterLabel.setIcon(SourceManager.getSpriteImage(charactername));
			}
		}
	}
}
