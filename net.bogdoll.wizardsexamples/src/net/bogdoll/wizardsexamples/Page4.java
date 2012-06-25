package net.bogdoll.wizardsexamples;

import java.util.Properties;

import javax.swing.JComponent;

import net.bogdoll.wizards.WizardPage;

public class Page4 extends WizardPage<Properties>
{
	private Visual4 mVisual;
	
	@Override
	public JComponent getVisual() {
		if(mVisual==null) {
			mVisual = new Visual4(this);
		}
		return mVisual;
	}
	
	@Override
	public String getName() {
		return "Page #4";
	}
}