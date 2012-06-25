package net.bogdoll.wizards;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;


public class WizardPane 
{
	private JComponent mMainVisual;
	
//	public <WC> WizardPane(WizardPageProvider<WC> aPageProvider, WC aContext)
//	{
//		this(new WizardController<WC>(aPageProvider, aContext));
//	}

	public <WC> WizardPane(WizardController<WC> aController, WizardPageProvider<WC> aPageProvider)
	{
		JComponent stepsVisual = new WizardStepsPane(aController).getVisual();
		JComponent buttonsVisual = new WizardButtonsRow(aController, aPageProvider).getButtonsRow();
		JComponent pageVisual = new WizardContentPane(aController).getVisual();
		mMainVisual = initVisual(stepsVisual, buttonsVisual, pageVisual);
	}
	
	private JComponent initVisual(JComponent aSteps, JComponent aButtons, JComponent aPage) 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(aSteps, BorderLayout.WEST);
		panel.add(aPage, BorderLayout.CENTER);
		panel.add(aButtons, BorderLayout.SOUTH);
		
		return panel;
	}

	public JComponent getVisual() 
	{
		return mMainVisual;
	}
}