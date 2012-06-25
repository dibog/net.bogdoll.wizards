package net.bogdoll.wizards;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.bogdoll.property.AnnotatedProperty;
import net.bogdoll.swing.Listener;


class WizardStepsPane 
{
	private final WizardController<?> mController;
	private JPanel mVisual;
	
	public WizardStepsPane(WizardController<?> aController) 
	{
		mController = aController;
		AnnotatedProperty.connect(this,  mController.pageStepNamesProperty(), WizardController.PROP_STEP_NAMES);
	}
	
	public JComponent getVisual() {
		if(mVisual==null) 
		{
			mVisual = new JPanel();
			mVisual.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			mVisual.setPreferredSize(new Dimension(100,100));
			mVisual.setLayout(new BoxLayout(mVisual, BoxLayout.Y_AXIS));
			displayPageNames(mController.pageStepNamesProperty().get());
		}
		return mVisual;
	}


	@SuppressWarnings("unused")
	@Listener(WizardController.PROP_STEP_NAMES)
	private void newPageSteps(List<String> aOld, List<String> aNew) 
	{
		displayPageNames(aNew);
	}
	
	private void displayPageNames(List<String> aStepNames) 
	{
		JComponent visual = getVisual();

		int i=0;
		int size = aStepNames.size()-1;
		visual.removeAll();
		for(i=0; i<size; ++i) {
			mVisual.add(new JLabel(aStepNames.get(i)));
		}
		visual.add(boldLabel(aStepNames.get(i)));
		visual.validate();
		visual.repaint();
	}

	private JLabel label(String aText) {
		JLabel label = new JLabel(aText);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		return label;
	}
	
	private JLabel boldLabel(String aText) {
		JLabel label = label(">> "+aText);
		Font baseFont = label.getFont();
		label.setFont(baseFont.deriveFont(Font.BOLD, baseFont.getSize2D()*1.1f));
		return label;
	}
}