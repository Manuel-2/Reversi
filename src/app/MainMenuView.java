package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuView extends View {
	public MainMenuView(String viewName) {
		super(viewName);
		
		JLabel title = new JLabel("Reversi");
		title.setFont(SourceManager.appFont.deriveFont(50f));
		title.setBounds(60, 48, 750, 50);
		title.setForeground(Color.white);
		add(title);
		
		JPanel menuButtonsContainer = new JPanel();
		menuButtonsContainer.setBounds(50, 200, 300, 600);
		menuButtonsContainer.setLayout(new BoxLayout(menuButtonsContainer,BoxLayout.Y_AXIS));
		menuButtonsContainer.setOpaque(false);
		String buttonsNames[] = {"Solo","Multi","Tutorial","Creddits"};
		for(String buttonName : buttonsNames) {
			MenuButton menuButton = new MenuButton(buttonName);
			menuButton.addActionListener(this);
			menuButtonsContainer.add(Box.createVerticalStrut(50));
			menuButtonsContainer.add(menuButton);
		}
		add(menuButtonsContainer);
		
		JLabel panelBackground = new JLabel(SourceManager.getSpriteImage("MainMenuViewBackground"));
		panelBackground.setBounds(-15,0,805,861);
		panelBackground.setOpaque(true);
		add(panelBackground);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuButton button = (MenuButton)e.getSource();
		String targetView = button.getActionCommand();
		App.sharedInstance.setView(targetView);
	}

	@Override
	public void before() {
		// TODO Auto-generated method stub
		
	}
}
