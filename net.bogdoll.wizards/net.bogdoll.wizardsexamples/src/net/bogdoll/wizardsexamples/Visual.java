package net.bogdoll.wizardsexamples;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.bogdoll.wizards.WizardPage;

public class Visual extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	public Visual(WizardPage<?> aPage) {
		setLayout(new BorderLayout());
		add(new JLabel(aPage.getName()), BorderLayout.NORTH);
	}
}
