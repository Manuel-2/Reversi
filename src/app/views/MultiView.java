package app.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.components.GoBackButton;
import app.components.MenuButton;
import app.*;

public class MultiView extends View {

	public MultiView(String viewName) {
		super(viewName);

		// TODO: add the thing in the background

		JLabel title = new JLabel("Multiplayer");
		title.setFont(SourceManager.appFont.deriveFont(50f));
		title.setBounds(60, 48, 750, 50);
		title.setForeground(Color.white);
		add(title);

		add(new GoBackButton());

		addVerticalArrayOfMainMenuButtons(50, 200, 300, 800, 50, new String[] { "Local"});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((MenuButton) e.getSource()).getActionCommand();
		if (actionCommand.equals("Local")) {
			App.sharedInstance.setGameModeAndEnterCharacterSelection(GameModes.local);
		} else if (actionCommand.equals("Online")) {
			// load the online view
		}
	}

	@Override
	public void before() {
		// TODO Auto-generated method stub

	}
}
