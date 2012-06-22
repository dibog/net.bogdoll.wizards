package net.bogdoll.wizardsexamples;

import java.util.List;
import java.util.Properties;

import javax.swing.JComponent;

import net.bogdoll.wizards.Message;
import net.bogdoll.wizards.WizardPage;

public class PageN implements WizardPage<Properties>
{
	private final String mPageName;
	private Visual mVisual;
	
	public PageN(String aPage) {
		mPageName = aPage;		
	}
	
	@Override
	public JComponent getVisual() {
		if(mVisual==null) {
			mVisual = new Visual(this);
		}
		return mVisual;
	}

	@Override
	public boolean isValid(List<Message> aMessages) {
		return true;
	}
	
	@Override
	public String getName() {
		return mPageName;
	}

	@Override
	public void restoreSettings(Properties aWizardContext) {
	}

	@Override
	public void storeSettings(Properties aWizwardContext) {
	}

	@Override
	public boolean canFinish() {
		return false;
	}
}
