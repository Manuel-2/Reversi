package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class View extends JPanel implements ActionListener {

	public final String viewName;

	/**
	 * Method called once just before this view is render, use it to setup or
	 * configure things at start
	 */
	public abstract void before();

	public View(String name) {
		setBackground(App.BackgroundColor);
		setLayout(null);
		App.sharedInstance.registerView(this, name);
		viewName = name;
	}

	protected void addVerticalArrayOfMainMenuButtons(int x, int y, int width, int height, int marginBottom,
			String labels[]) {
		JPanel menuButtonsContainer = new JPanel();
		menuButtonsContainer.setBounds(x, y, width, height);
		menuButtonsContainer.setLayout(new BoxLayout(menuButtonsContainer, BoxLayout.Y_AXIS));
		menuButtonsContainer.setOpaque(false);
		for (String buttonName : labels) {
			MenuButton menuButton = new MenuButton(buttonName);
			menuButton.addActionListener(this);
			menuButtonsContainer.add(Box.createVerticalStrut(marginBottom));
			menuButtonsContainer.add(menuButton);
		}
		add(menuButtonsContainer);
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);
}
