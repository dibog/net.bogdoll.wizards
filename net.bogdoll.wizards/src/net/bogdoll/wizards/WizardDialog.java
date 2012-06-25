package net.bogdoll.wizards;

import javax.swing.JDialog;

public class WizardDialog<WC> extends JDialog 
{
	private static final long serialVersionUID = -6549086829257854804L;
	
	private WizardPane mPane;
	private WizardController<WC> mController;
	
	public WizardDialog(WizardPageProvider<WC> aPageProvider, WC aContext) {
		mController = new WizardController<WC>(aPageProvider, aContext);
		mPane = new WizardPane(mController, aPageProvider);
		setModal(true);
		setContentPane(mPane.getVisual());
	}
	
	public static <WC> WizardDialog<WC> create(WizardPageProvider<WC> aPageProvider, WC aContext) {
		return new WizardDialog<WC>(aPageProvider, aContext);
	}

	public Object showWizard() {
		setVisible(true);
		return mController.getResult();
	}
	
	public WC getContext() {
		return mController.getContext();
	}
}
