package app;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
	public static final Color BackgroundColor = Color.decode("#060606");
	
	public App() {
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 750);
		
		MainMenuView mainMenu = new MainMenuView();
		add(mainMenu);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuButton menuButton = (MenuButton) e.getSource();
	}
}
