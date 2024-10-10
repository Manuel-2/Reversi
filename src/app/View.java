	package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class View extends JPanel implements ActionListener {
	
	public final String viewName;
	
	public View(String name) {
		setBackground(App.BackgroundColor);
		viewName = name;	
		setLayout(null);
		App.sharedInstance.registerView(this,name);
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);
}
