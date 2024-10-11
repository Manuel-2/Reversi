package app.components;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.*;

public class SquaredButton extends JPanel {

	int buttonSize = 91;
	int iconSize = 51;

	final JButton buttonComponent;

	public SquaredButton(ImageIcon icon, String actionCommand, ActionListener actionListener) {
		setLayout(null);
		setOpaque(false);
		setSize(91, 91);

		JLabel backgroundLabel = new JLabel(SourceManager.getSpriteImage("SquareButton"));
		backgroundLabel.setBounds(getVisibleRect());
		add(backgroundLabel);

		JButton button = new JButton(icon);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setBounds(0, 0, buttonSize, buttonSize);
		button.setActionCommand(actionCommand);
		button.addActionListener(actionListener);
		buttonComponent = button;
		add(button);
	}

	public void disableButton() {
		buttonComponent.setEnabled(false);
	}
	
	public void enableButton() {
		buttonComponent.setEnabled(true);
	}
}
