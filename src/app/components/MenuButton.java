package app.components;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import app.*;

public class MenuButton extends JButton {

	public MenuButton(String label) {
		setActionCommand(label);
		setText(label);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(-56);
		setSize(259, 91);
		setFocusable(false);
		setOpaque(true);
		setForeground(Color.white);
		setBorder(null);
		setFont(SourceManager.appFont.deriveFont(30f));
		setIcon(SourceManager.getSpriteImage("ButtonFrame"));
		setBackground(App.BackgroundColor);
		setContentAreaFilled(false);
	}
}
