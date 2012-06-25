package net.bogdoll.wizardsexamples;

import java.util.List;
import java.util.Properties;

import javax.swing.JComponent;

import net.bogdoll.swing.TextModel;
import net.bogdoll.wizards.Message;
import net.bogdoll.wizards.WizardPage;

public class Page2 extends WizardPage<Properties>
{
	private Visual2 mVisual;
	private TextModel mChoice;

	@Override
	public JComponent getVisual() {
		if(mVisual==null) {
			mVisual = new Visual2(this);
		}
		return mVisual;
	}
	
	@Override
	public String getName() {
		return "Page #2";
	}
	
	public TextModel getChoice() {
		if(mChoice==null) {
			mChoice = new TextModel();
		}
		return mChoice;
	}
	
	@Override
	public boolean isValid(List<Message> aMessages) {
		if(mChoice==null || mChoice.getText().isEmpty() ) {
			aMessages.add(Message.error("Choice must not be null nor empty"));
			return false;
		}
		else if(mChoice.getText().equalsIgnoreCase("okay")) {
			return true;
		}
		else if(mChoice.getText().equalsIgnoreCase("more")) {
			aMessages.add(Message.error("*** error ***"));
			aMessages.add(Message.warn("*** warn ***"));
			aMessages.add(Message.info("*** info ***"));
			aMessages.add(Message.debug("*** debug ***"));
			return false;
		}
		else {
			aMessages.add(Message.warn("Please enter 'Okay' as choice to continue"));
			return false;
		}
	}

	@Override
	public void restoreSettings(Properties aWizardContext) {
		String choice = aWizardContext.getProperty("Page2.choice", "");
		getChoice().setText(choice);
	}

	@Override
	public void storeSettings(Properties aWizwardContext) {
		aWizwardContext.put("Page2.choice", mChoice.getText());
	}
}