package net.bogdoll.wizardsexamples;

import java.util.Properties;

import javax.swing.JComponent;

import net.bogdoll.wizards.WizardPage;

public class Page6 extends WizardPage<Properties>
{
	private Visual mVisual;

	public Page6() {
		canFinishEarlierProperty().set(true);
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
		return "Page #6";
	}
}