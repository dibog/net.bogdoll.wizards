package net.bogdoll.wizards;

import java.util.List;

import javax.swing.JComponent;

public interface WizardPage<WC> 
{
	boolean isValid(List<Message> aMessages);
	boolean canFinish();
	
	JComponent getVisual();
	
	void restoreSettings(WC aWizardContext);
	void storeSettings(WC aWizwardContext);

	String getName();
}
