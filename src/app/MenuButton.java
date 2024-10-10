package app;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuButton extends JButton {

	MenuButton(String label){
		setText(label);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(-56);
		setSize(259,91);
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