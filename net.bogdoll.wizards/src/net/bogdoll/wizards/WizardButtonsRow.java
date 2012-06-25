package net.bogdoll.wizards;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.bogdoll.property.AnnotatedProperty;
import net.bogdoll.swing.Listener;

class WizardButtonsRow
{
	private final WizardPageProvider<?> mPageProvider;
	private final JButton mPrevious;
	private final JButton mNext;
	private final JButton mFinish;
	private final JButton mCancel;
	
	private JComponent mVisual;
	
	private AnnotatedProperty<Boolean> mIsValidProperty;
	private AnnotatedProperty<Boolean> mCanFinishEarlier;

	public <WC> WizardButtonsRow(WizardController<WC> aController, WizardPageProvider<WC> aPageProvider) 
	{
		mPageProvider = aPageProvider;
		AnnotatedProperty.connect(this, aPageProvider.currentPageProperty(), WizardController.PROP_CURRENT_PAGE);
		
		mPrevious = button("<<", aController.getPrevAction());
		mNext = button(">>", aController.getNextAction());
		mFinish = button("Finish", aController.getFinishAction());
		mCancel = button("Cancel", aController.getCancelAction());
	}
	
	public JComponent getButtonsRow() 
	{
		if(mVisual==null) {
			JPanel buttons = new JPanel();
			GridLayout layout = new GridLayout(1, 0, 10, 10);
			buttons.setLayout(layout);
			
			buttons.add(mPrevious);
			buttons.add(mNext);
			
			mFinish.setDefaultCapable(true);
			buttons.add(mFinish);
			
			buttons.add(mCancel);
			
			JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT,10, 10));
			flow.add(buttons);

			mVisual = flow;
			refreshUI();
		}
		
		return mVisual;
	}
	
	@SuppressWarnings("unused")
	@Listener(WizardController.PROP_CURRENT_PAGE)
	private void newCurrentPage(WizardPage<?> aOld, WizardPage<?> aNew) 
	{
		if(mIsValidProperty!=null) {
			mIsValidProperty.disconnect();
		}
		if(mCanFinishEarlier!=null) {
			mCanFinishEarlier.disconnect();
		}
		
		if(aNew!=null) 
		{
			mIsValidProperty = AnnotatedProperty.connect(this, aNew.isValidProperty(), "isValid");
			mCanFinishEarlier = AnnotatedProperty.connect(this, aNew.canFinishEarlierProperty(), "canFinishEarlier");
		}
		
		refreshUI();
	}
	
	@SuppressWarnings("unused")
	@Listener("isValid")
	private void newIsValid(Boolean aOld, Boolean aNew) {
		refreshUI();
	}
	
	@SuppressWarnings("unused")
	@Listener("canFinishEarlier")
	private void newCanFinishEarlier(Boolean aOld, Boolean aNew) {
		refreshUI();
	}
	
	void refreshUI() 
	{
		boolean enablePrev = mPageProvider.canDoPrevious(); 
		mPrevious.setEnabled(enablePrev);
		
		boolean enableNext = mPageProvider.canDoNext();
		mNext.setEnabled(enableNext);
		
		boolean enableFinish = mPageProvider.canDoFinish();
		mFinish.setEnabled(enableFinish);
	}
	
	private static JButton button(String aText, Action aAction) {
		JButton button = new JButton(aAction);
		button.setText(aText);
		return button;
	}
}