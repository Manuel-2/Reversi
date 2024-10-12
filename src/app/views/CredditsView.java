package app.views;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import app.SourceManager;
import app.components.GoBackButton;

public class CredditsView extends View {

	public CredditsView(String name) {
		super(name);
		add(new GoBackButton());

		// well i was running out of time
		JLabel content = new JLabel(SourceManager.getSpriteImage("Creddits"));
		content.setBounds(0, 30, 820, 892);
		content.setLocation(0, 30);
		content.setText("a");
		add(content);
	}

	@Override
	public void before() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
