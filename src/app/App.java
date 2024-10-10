package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {
	public static App sharedInstance;

	public static final Color BackgroundColor = Color.decode("#060606");

	private Map<String, View> loadedViews = new HashMap<String, View>();
	private View currentView;

	GameConfigurations currentGameConfiguration;

	public App() {
		if (sharedInstance == null) {
			sharedInstance = this;
		}
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(805, 892);

		// register the views
		new MainMenuView("MainMenu");
		new MultiView("Multi");
		new SelectCharacterView("SelectCharacter");

		// show the main Menu
		setView("MainMenu");
		setVisible(true);
	}

	public void startGame(GameConfigurations gameConfig) {
		switch (gameConfig) {
		case local: {
			currentGameConfiguration = gameConfig;
			setView("SelectCharacter");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + gameConfig);
		}
	}

	public void registerView(View view, String name) {
		View view2Register = loadedViews.get(name);
		if (view2Register != null) {
			throw new IllegalArgumentException("ERROR: A view with the name: " + name + ", alreadyExits");
		}
		loadedViews.put(name, view);
	}

	public void setView(String viewName) {
		if (currentView != null) {
			remove(currentView);
		}

		currentView = loadedViews.get(viewName);
		if (currentView == null) {
			System.err.println("WARNING: viewName: \"" + viewName + "\" not found returning to mainMenu");
			add(loadedViews.get("MainMenu"));
		} else {
			currentView.before();
			add(currentView);
			revalidate();
			repaint();
		}
	}

	public GameConfigurations getCurrentGameConfiguration() {
		return App.sharedInstance.currentGameConfiguration;
	}
}
