package net.bogdoll.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;

import net.bogdoll.property.Property;

public abstract class WizardPage<WC> 
{
	public static final String PROP_CAN_FINISH_EARLIER = "canFinishEarlier";
	public static final String PROP_IS_VALID = "isValid";
	public static final String PROP_VALIDATION_MESSAGES = "validationMessages";
	
	protected Property<Boolean> mIsValid = new Property<Boolean>(isValid(new ArrayList<Message>()));
	private Property<Boolean> mCanFinishEarlier = new Property<Boolean>(!isDynamic());
	private Property<List<Message>> mValidationMessages = new Property<List<Message>>(Collections.<Message>emptyList());

	public Property<Boolean> isValidProperty() {
		return mIsValid;
	}
	
	public Property<Boolean> canFinishEarlierProperty() {
		return mCanFinishEarlier;
	}
	
	public Property<List<Message>> validationMessagesProperty() {
		return mValidationMessages;
	}
	
	public boolean isValid(List<Message> aMessages) {
		return true;
	}
	
	public boolean isDynamic() {
		return false;
	}
	
	public boolean checkIfValid() {
		List<Message> messages = new ArrayList<Message>();
		boolean valid = isValid(messages);

		mValidationMessages.set(messages);
		mIsValid.set(valid);
		
		return valid;
	}

	public abstract JComponent getVisual();
	
	protected void restoreSettings(WC aWizardContext) {
	}
	
	protected void storeSettings(WC aWizwardContext) {
	}

	public abstract String getName();
}