package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class View extends JPanel implements ActionListener {
	
	public View() {
		setBackground(App.BackgroundColor);
		setLayout(null);
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);

}
