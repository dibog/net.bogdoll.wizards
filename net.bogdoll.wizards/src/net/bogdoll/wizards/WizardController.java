package net.bogdoll.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import net.bogdoll.property.Property;
import net.bogdoll.swing.AnnotatedAction;
import net.bogdoll.swing.Listener;

public class WizardController<WC>
{
	public static final Object FINISHED_OPTION = new Object();
	public static final Object CANCELED_OPTION = new Object();
	
	public static final String PROP_PAGE_VISUAL = "pageVisual";
	public static final String PROP_STEP_NAMES = "stepNames";
	public static final String PROP_PAGE_MESSAGES = "pageMessages";
	public static final String PROP_ENABLE_FINISH = "enableFinish";
	public static final String PROP_CURRENT_PAGE = "currentPage";
	
	private final WizardPageProvider<WC> mPageProvider;
	private final WC mContext;

	private Property<List<String>> mPageNames = new Property<List<String>>(Collections.<String>emptyList());
	private Property<WizardPage<WC>> mCurrentPage;
	
	private AnnotatedAction mNext;
	private AnnotatedAction mPrev;
	private AnnotatedAction mFinish;
	private AnnotatedAction mCancel;
	
	private Object mResult;
	
	public WizardController(WizardPageProvider<WC> aPageProvider, WC aContext) {
		mResult = CANCELED_OPTION;
		mContext = aContext;
		mPageProvider = aPageProvider;
		
		mCurrentPage = mPageProvider.currentPageProperty();
		
		setFirstPage(mCurrentPage.get());
	}
	
	private void setFirstPage(WizardPage<WC> aFirstPage) {

		aFirstPage.restoreSettings(mContext);
		addPageStepName(aFirstPage.getName());

		aFirstPage.checkIfValid();
		
		refreshUI();
	}
	
	Action getPrevAction() {
		if(mPrev==null) {
			mPrev = new AnnotatedAction(this, "prev");
		}
		return mPrev;
	}

	Action getNextAction() {
		if(mNext==null) {
			mNext = new AnnotatedAction(this, "next");
		}
		return mNext;
	}

	Action getFinishAction() {
		if(mFinish==null) {
			mFinish = new AnnotatedAction(this, "finish");
		}
		return mFinish;
	}

	Action getCancelAction() {
		if(mCancel==null) {
			mCancel = new AnnotatedAction(this, "cancel");
		}
		return mCancel;
	}
	
	@SuppressWarnings("unused")
	@Listener("next")
	private void doNext() {
		WizardPage<WC> current = currentPageProperty().get();
		current.checkIfValid();
		if(current.isValidProperty().get()) {
			current.storeSettings(mContext);
			
			mPageProvider.next();
			
			current = currentPageProperty().get();
			addPageStepName(current.getName());
			current.restoreSettings(mContext);
		} 
		
		refreshUI();
	}

	@SuppressWarnings("unused")
	@Listener("prev")
	private void doPrev() {
		WizardPage<WC> current = currentPageProperty().get();
		current.storeSettings(mContext);
		
		mPageProvider.prev();
		
		current = currentPageProperty().get();
		removePageStepName();
		current.restoreSettings(mContext);

		refreshUI();
	}
	
	public Property<WizardPage<WC>> currentPageProperty() {
		return mCurrentPage;
	}

	@SuppressWarnings("unused")
	@Listener("finish")
	private void doFinish() {
		WizardPage<WC> current = currentPageProperty().get();
		current.checkIfValid();
		if(current.isValidProperty().get()) {
			current.storeSettings(mContext);
			mResult = FINISHED_OPTION;
			JComponent visual = current.getVisual();
			if(visual!=null) {
				SwingUtilities.getRoot(visual).setVisible(false);
			}
		}
	}
	
	private void refreshUI() {
		
	}
	
	@SuppressWarnings("unused")
	@Listener("cancel")
	private void doCancel() {
		JComponent visual = currentPageProperty().get().getVisual();
		if(visual!=null) {
			mResult = CANCELED_OPTION;
			SwingUtilities.getRoot(visual).setVisible(false);
		}
	}

	private void addPageStepName(String aPageName) {
		List<String> newList = new ArrayList<String>(mPageNames.get());
		newList.add(aPageName);
		mPageNames.set(newList);
	}

	private void removePageStepName() {
		List<String> newList = new ArrayList<String>(mPageNames.get());
		newList.remove(newList.size()-1);
		mPageNames.set(newList);
	}

	public Property<List<String>> pageStepNamesProperty() {
		return mPageNames;
	}
	
	public Object getResult() {
		return mResult;
	}

	public WC getContext() {
		return mContext;
	}
}