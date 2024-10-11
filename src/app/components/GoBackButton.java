package app.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import app.App;
import app.SourceManager;

public class GoBackButton extends JButton implements ActionListener {

	public GoBackButton() {
		setText("<");
		setHorizontalTextPosition(SwingConstants.CENTER);
		setSize(50, 50);
		setFocusable(false);
		setForeground(Color.white);
		setBorder(null);
		setFont(SourceManager.appFont.deriveFont(50f));
		setContentAreaFilled(false);
		addActionListener(this);
		setLocation(0, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		App.sharedInstance.returnPreviusView();
	}
}
