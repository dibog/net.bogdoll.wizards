package net.bogdoll.wizardsexamples;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Visual5 extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	public Visual5(Page5 aPage) {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel(aPage.getName()));

		JButton button = new JButton(aPage.getFinishEarlierButton());
		button.setText("Finish earlier");
		button.requestFocus();
		
		panel.add(button);
		
		add(panel, BorderLayout.NORTH);
	}
}