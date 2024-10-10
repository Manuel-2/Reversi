package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiView extends View {

	public MultiView(String viewName) {
		super(viewName);

		// todo: add the thing in the background
		
		JLabel title = new JLabel("Multiplayer");
		title.setFont(SourceManager.appFont.deriveFont(50f));
		title.setBounds(60, 48, 750, 50);
		title.setForeground(Color.white);
		add(title);

		MenuButton return2MainMenu = new MenuButton("<");
		return2MainMenu.setFont(SourceManager.appFont.deriveFont(50f));
		return2MainMenu.setBounds(10, 60, 50, 50);
		return2MainMenu.addActionListener(this);
		add(return2MainMenu);

		addVerticalArrayOfMainMenuButtons(50, 200, 300, 800, 50, new String[] { "Local", "Online" });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((MenuButton)e.getSource()).getActionCommand();
		if(actionCommand.equals("Local")) {
			App.sharedInstance.startGame(GameConfigurations.local);
		}else if(actionCommand.equals("Online")) {
			// load the online view
		}else if(actionCommand.equals("<")) {
			App.sharedInstance.setView("MainMenu");
		}
	}

	@Override
	public void before() {
		// TODO Auto-generated method stub
		
	}

}
