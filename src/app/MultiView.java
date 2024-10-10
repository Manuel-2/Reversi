package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class MultiView extends View {
	
	public MultiView(String viewName) {
		super(viewName);
		setLayout(new BorderLayout());
		
		JLabel viewTitle = new JLabel("Multiplayer");
		viewTitle.setFont(SourceManager.appFont.deriveFont(50f));
		add(viewTitle);
		MenuButton localButton = new MenuButton("On this Computer");
		add(localButton);
		MenuButton onlineButton = new MenuButton("Online(LAN)");
		add(onlineButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
