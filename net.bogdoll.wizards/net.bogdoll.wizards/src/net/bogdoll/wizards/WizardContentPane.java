package net.bogdoll.wizards;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.bogdoll.swing.ReplaceablePane;

class WizardContentPane implements PropertyChangeListener 
{
	private WizardController<?> mController;
	private ReplaceablePane mPageContent;
	private JPanel mVisual;
	
	public WizardContentPane(WizardController<?> aController) 
	{
		mController = aController;
		mController.addPropertyChangeListener(WizardController.PROP_PAGE_VISUAL, this);
	}
	
	public JPanel getVisual() 
	{
		if(mVisual==null) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

			mPageContent = new ReplaceablePane(mController.getPageVisual());
			panel.add(mPageContent, BorderLayout.CENTER);
			
			panel.add(new WizardMessagePane(mController).getVisual(), BorderLayout.SOUTH);
			
			mVisual = panel;
		}
		return mVisual;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(WizardController.PROP_PAGE_VISUAL))
		{
			JComponent comp = (JComponent) evt.getNewValue();
			mPageContent.setComponent(comp);
		}
	}
}