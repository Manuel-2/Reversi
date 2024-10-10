package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Menu;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuView extends JPanel {
	public MainMenuView() {
		setBackground(App.BackgroundColor);
		setLayout(null);
		//setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Reversi");
		title.setFont(SourceManager.appFont.deriveFont(50f));
		title.setBounds(60, 48, 750, 50);
		title.setForeground(Color.white);
		add(title,BorderLayout.NORTH);
		
		JPanel menuButtonsContainer = new JPanel();
		menuButtonsContainer.setBounds(50, 200, 300, 600);
		menuButtonsContainer.setLayout(new BoxLayout(menuButtonsContainer,BoxLayout.Y_AXIS));
		menuButtonsContainer.setOpaque(false);
		menuButtonsContainer.add(new MenuButton("Solo"));
		menuButtonsContainer.add(Box.createVerticalStrut(50));
		menuButtonsContainer.add(new MenuButton("Multi"));
		menuButtonsContainer.add(Box.createVerticalStrut(50));
		menuButtonsContainer.add(new MenuButton("Tutorial"));
		menuButtonsContainer.add(Box.createVerticalStrut(50));
		menuButtonsContainer.add(new MenuButton("Creddits"));
		add(menuButtonsContainer);
		
		JLabel panelBackground = new JLabel(SourceManager.getSpriteImage("MainMenuViewBackground"));
		panelBackground.setBounds(-15,0,805,861);
		panelBackground.setOpaque(true);
		add(panelBackground);
	}
}
