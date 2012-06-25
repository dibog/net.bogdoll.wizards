package net.bogdoll.wizardsexamples;

import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import net.bogdoll.swing.Listener;
import net.bogdoll.swing.AnnotatedAction;
import net.bogdoll.wizards.WizardPage;

public class Page5 extends WizardPage<Properties>
{
	public static final String PROP_FINISH_EARLIER = "finishEarlier";
	private Visual5 mVisual;
	
	private AnnotatedAction mFinishEarlier = new AnnotatedAction(this, PROP_FINISH_EARLIER);

	@Override
	public JComponent getVisual() {
		if(mVisual==null) {
			mVisual = new Visual5(this);
		}
		return mVisual;
	}
	
	@Override
	public String getName() {
		return "Page #5";
	}
	
	public AbstractAction getFinishEarlierButton() {
		return mFinishEarlier;
	}
	
	@SuppressWarnings("unused")
	@Listener(PROP_FINISH_EARLIER)
	private void finishEarlier() {
		canFinishEarlierProperty().set(true);
	}
	
	public boolean isDynamic() {
		return true;
	}
}