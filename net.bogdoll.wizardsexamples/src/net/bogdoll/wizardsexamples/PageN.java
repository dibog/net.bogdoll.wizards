package net.bogdoll.wizardsexamples;

import java.util.Properties;

import javax.swing.JComponent;

import net.bogdoll.wizards.WizardPage;

public class PageN extends WizardPage<Properties>
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
	public String getName() {
		return mPageName;
	}
}
