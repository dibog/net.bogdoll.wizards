package net.bogdoll.wizards;

import net.bogdoll.property.Property;


public abstract class WizardPageProvider<WC>  
{
	public final static String PROP_CURRENT_PAGE = "currentPage";
	
	private final Property<WizardPage<WC>> mCurrent = new Property<WizardPage<WC>>();

	public Property<WizardPage<WC>> currentPageProperty() {
		return mCurrent;
	}

	public abstract void prev();
	public abstract void next();
	public abstract boolean hasPrev();
	public abstract boolean hasNext();
	
	public boolean canDoPrevious() {
		return false;
	}

	public boolean canDoNext() {
		return false;
	}

	public boolean canDoFinish() {
		return false;
	}
}
