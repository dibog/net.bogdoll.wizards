package net.bogdoll.wizardsexamples;

import java.util.List;
import java.util.Properties;

import javax.swing.JComponent;

import net.bogdoll.swing.TextModel;
import net.bogdoll.wizards.Message;
import net.bogdoll.wizards.WizardPage;

public class Page1 implements WizardPage<Properties>
{
	private Visual1 mVisual;
	private TextModel mChoice = new TextModel();

	@Override
	public JComponent getVisual() {
		if(mVisual==null) {
			mVisual = new Visual1(this);
		}
		return mVisual;
	}
	
	@Override
	public String getName() {
		return "Page #1";
	}
	
	public TextModel getChoice() {
		return mChoice;
	}
	
	@Override
	public boolean isValid(List<Message> aMessages) {
		if(mChoice.getText().isEmpty() ) {
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
	}

	@Override
	public void storeSettings(Properties aWizwardContext) {
	}

	@Override
	public boolean canFinish() {
		return false;
	}
}