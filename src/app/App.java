package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {
	public static App sharedInstance;

	public static final Color BackgroundColor = Color.decode("#060606");

	private Map<String, JPanel> loadedViews = new HashMap<String, JPanel>();
	private JPanel currentView;

	public App(){

		if (sharedInstance == null) {
			sharedInstance = this;
		}
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(805, 892);

		MainMenuView mainMenu = new MainMenuView();
		loadedViews.put("mainMenu", mainMenu);

		setView("mainMenu");
		setVisible(true);
	}

	public void setView(String viewName) {
		if (currentView != null) {
			remove(currentView);
		}
		currentView = loadedViews.get(viewName);
		if (currentView == null) {
			System.err.println("WARNING: view Name not found returning to mainMenu");
			add(loadedViews.get("mainMenu"));
		} else {
			add(currentView);
		}
	}
}
