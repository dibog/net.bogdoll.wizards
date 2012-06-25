package net.bogdoll.wizards;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.bogdoll.property.AnnotatedProperty;
import net.bogdoll.swing.Listener;
import net.bogdoll.swing.ReplaceablePane;

class WizardContentPane 
{
	private WizardController<?> mController;
	private ReplaceablePane mPageContent;
	private JPanel mVisual;
	
	public WizardContentPane(WizardController<?> aController) 
	{
		mController = aController;
		AnnotatedProperty.connect(this, mController.currentPageProperty(), WizardController.PROP_CURRENT_PAGE);
	}
	
	public JPanel getVisual() 
	{
		if(mVisual==null) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

			mPageContent = new ReplaceablePane(mController.currentPageProperty().get().getVisual());
			panel.add(mPageContent, BorderLayout.CENTER);
			
			panel.add(new WizardMessagePane(mController).getVisual(), BorderLayout.SOUTH);
			
			mVisual = panel;
		}
		return mVisual;
	}
	
	@SuppressWarnings("unused")
	@Listener(WizardController.PROP_CURRENT_PAGE)
	private void newPageVisual(WizardPage<?> aOld, WizardPage<?> aNew) {
		mPageContent.setComponent(aNew.getVisual());
	}
}