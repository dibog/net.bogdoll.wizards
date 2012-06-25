package net.bogdoll.wizardsexamples;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Visual2 extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JTextField mField;
	
	public Visual2(Page2 aPage) {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel(aPage.getName()));
	
		mField = new JTextField();
		mField.setDocument(aPage.getChoice());
		mField.requestFocus();

		panel.add(mField);
		
		add(panel, BorderLayout.NORTH);
	}
}