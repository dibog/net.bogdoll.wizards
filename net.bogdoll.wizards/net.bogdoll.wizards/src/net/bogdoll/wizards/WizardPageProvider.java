package net.bogdoll.wizards;

public interface WizardPageProvider<WC> 
{
	WizardPage<WC> getCurrent();
	void prev();
	void next();
	
	boolean hasPrev();
	boolean hasNext();
	
	boolean canFinish();
}
