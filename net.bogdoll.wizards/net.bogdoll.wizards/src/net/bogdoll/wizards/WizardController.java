package net.bogdoll.wizards;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import net.bogdoll.swing.AnnotatedAction;

public class WizardController<WC>
{
	public static final String PROP_PAGE_VISUAL = "pageVisual";
	public static final String PROP_STEP_NAMES = "stepNames";
	public static final String PROP_PAGE_MESSAGES = "pageMessages";
	
	private final WizardPageProvider<WC> mPageProvider;
	private final WC mContext;
	private JComponent mPageVisual;
	private List<String> mPageNames = Collections.emptyList();
	private List<Message> mMessages;
	private PropertyChangeSupport mPCS = new PropertyChangeSupport(this);
	
	private AnnotatedAction mNext;
	private AnnotatedAction mPrev;
	private AnnotatedAction mFinish;
	private AnnotatedAction mCancel;
	
	public WizardController(WizardPageProvider<WC> aPageProvider, WC aContext) {
		mContext = aContext;
		mPageProvider = aPageProvider;
		mPageProvider.getCurrent().restoreSettings(mContext);
		setPageVisual(mPageProvider.getCurrent().getVisual());

		addPageStepName(mPageProvider.getCurrent().getName());
		
		refreshUI();
	}
	
	Action getPrevAction() {
		if(mPrev==null) {
			mPrev = new AnnotatedAction(this, "prev");
			mPrev.setText("<<");
		}
		return mPrev;
	}

	Action getNextAction() {
		if(mNext==null) {
			mNext = new AnnotatedAction(this, "next");
			mNext.setText(">>");
		}
		return mNext;
	}

	Action getFinishAction() {
		if(mFinish==null) {
			mFinish = new AnnotatedAction(this, "finish");
			mFinish.setText("Finish");
		}
		return mFinish;
	}

	Action getCancelAction() {
		if(mCancel==null) {
			mCancel = new AnnotatedAction(this, "cancel");
			mCancel.setText("Cancel");
		}
		return mCancel;
	}
	
	@SuppressWarnings("unused")
	@net.bogdoll.swing.Action("next")
	private void doNext() {
		List<Message> messages =  new ArrayList<Message>();
		if(getCurrentPage().isValid(messages)) {
			clearMessages();
			getCurrentPage().storeSettings(mContext);
			mPageProvider.next();
			addPageStepName(getCurrentPage().getName());
			getCurrentPage().restoreSettings(mContext);
		} 
		else {
			setMessages(messages);
		}
		
		refreshUI();
	}
	
	private void clearMessages() {
		setMessages(Collections.<Message>emptyList());
	}
	
	private void setMessages(List<Message> aMessages) {
		List<Message> old = mMessages;
		mMessages = new ArrayList<Message>(aMessages);
		mPCS.firePropertyChange(PROP_PAGE_MESSAGES, old, mMessages);
	}
	
	@SuppressWarnings("unused")
	@net.bogdoll.swing.Action("prev")
	private void doPrev() {
		getCurrentPage().storeSettings(mContext);
		mPageProvider.prev();
		removePageStepName();
		getCurrentPage().restoreSettings(mContext);

		refreshUI();
	}
	
	private void refreshUI() {
		setPageVisual(getCurrentPage().getVisual());
		refreshButton();
	}
	
	private WizardPage<WC> getCurrentPage() {
		return mPageProvider.getCurrent();
	}

	@SuppressWarnings("unused")
	@net.bogdoll.swing.Action("finish")
	private void doFinish() {
		List<Message> messages = new ArrayList<Message>();
		if(getCurrentPage().isValid(messages)) {
			clearMessages();
			getCurrentPage().storeSettings(mContext);
			if(mPageVisual!=null) {
				SwingUtilities.getRoot(mPageVisual).setVisible(false);
			}
		}
		else {
			setMessages(messages);
		}
	}
	
	@SuppressWarnings("unused")
	@net.bogdoll.swing.Action("cancel")
	private void doCancel() {
		if(mPageVisual!=null) {
			SwingUtilities.getRoot(mPageVisual).setVisible(false);
		}
	}

	private void refreshButton() {
		getPrevAction().setEnabled( mPageProvider.hasPrev() );
		getNextAction().setEnabled( mPageProvider.hasNext() );
		getFinishAction().setEnabled( mPageProvider.canFinish() );
	}
	
	private void addPageStepName(String aPageName) {
		Object old = mPageNames;
		List<String> newList = new ArrayList<String>(mPageNames);
		newList.add(aPageName);
		mPageNames = newList;
		mPCS.firePropertyChange(PROP_STEP_NAMES, old, mPageNames);
	}

	private void removePageStepName() {
		Object old = mPageNames;
		List<String> newList = new ArrayList<String>(mPageNames);
		newList.remove(newList.size()-1);
		mPageNames = newList;
		mPCS.firePropertyChange(PROP_STEP_NAMES, old, mPageNames);
	}

	private void setPageVisual(JComponent aVisual) {
		Object old = mPageVisual;
		mPageVisual = aVisual;
		mPCS.firePropertyChange(PROP_PAGE_VISUAL, old, mPageVisual);
	}
	
	void addPropertyChangeListener(PropertyChangeListener aListener) {
		mPCS.addPropertyChangeListener(aListener);
	}

	void addPropertyChangeListener(String aPropertyName, PropertyChangeListener aListener) {
		mPCS.addPropertyChangeListener(aPropertyName, aListener);
	}

	void removePropertyChangeListener(PropertyChangeListener aListener) {
		mPCS.removePropertyChangeListener(aListener);
	}

	void removePropertyChangeListener(String aPropertyName, PropertyChangeListener aListener) {
		mPCS.removePropertyChangeListener(aPropertyName, aListener);
	}

	JComponent getPageVisual() {
		return mPageVisual;
	}

	List<String> getStepNames() {
		return mPageNames;
	}
}