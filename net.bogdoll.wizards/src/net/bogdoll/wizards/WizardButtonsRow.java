package net.bogdoll.wizards;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

class WizardButtonsRow
{
	private final WizardController<?> mController;

	public WizardButtonsRow(WizardController<?> aController) 
	{
		mController = aController;
	}
	
	public JComponent getButtonsRow() 
	{
		JPanel buttons = new JPanel();
		GridLayout layout = new GridLayout(1, 0, 10, 10);
		buttons.setLayout(layout);
		buttons.add(new JButton(mController.getPrevAction()));
		buttons.add(new JButton(mController.getNextAction()));

		JButton finish = new JButton(mController.getFinishAction());
		finish.setDefaultCapable(true);
		buttons.add(finish);

		buttons.add(new JButton(mController.getCancelAction()));
		
		JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT,10, 10));
		flow.add(buttons);
		
		return flow;
	}
}