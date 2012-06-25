package net.bogdoll.wizardsexamples;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Visual4 extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	public Visual4(Page4 aPage) {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel(aPage.getName()));

		panel.add(new JLabel("Nothing to do here"));
		
		add(panel, BorderLayout.NORTH);
	}
}