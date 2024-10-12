package app.views;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import app.SourceManager;
import app.components.GoBackButton;

public class RulesView extends View {

	public RulesView(String name) {
		super(name);
		add(new GoBackButton());

		// well i was running out of time
		JLabel content = new JLabel(SourceManager.getSpriteImageIcon("Rules"));
		content.setBounds(0, -16, 820, 892);
		content.setText(" ");
		add(content);
	}

	@Override
	public void before() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
