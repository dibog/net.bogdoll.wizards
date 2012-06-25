package net.bogdoll.wizardsexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.bogdoll.wizards.WizardPage;
import net.bogdoll.wizards.WizardPageProvider;

public class MyPageProvider extends WizardPageProvider<Properties> 
{
	private List<WizardPage<Properties>> mPages;
	private int mCurrent = 0;
	
	public MyPageProvider() {
		mPages = new ArrayList<WizardPage<Properties>>();
		mPages.add(new Page1());
		mPages.add(new Page2());
		mPages.add(new PageN("Page #3"));
		mPages.add(new Page4());
		mPages.add(new Page5());
		mPages.add(new Page6());
		mPages.add(new PageN("Page #7"));
		
		currentPageProperty().set(mPages.get(0));
	}
	
	@Override
	public boolean hasPrev() {
		return mCurrent>0; 
	}
	
	@Override
	public boolean hasNext() {
		return mCurrent<mPages.size()-1;
	}

	@Override
	public void prev() {
		if(hasPrev()) {
			mCurrent--;
			currentPageProperty().set(mPages.get(mCurrent));
		}
	}

	@Override
	public void next() {
		if(hasNext()) {
			mCurrent++;
			currentPageProperty().set(mPages.get(mCurrent));
		}
	}
	
	@Override
	public boolean canDoPrevious() {
		return hasPrev();
	}
	
	@Override
	public boolean canDoNext() {
		WizardPage<Properties> current = currentPageProperty().get();
		return  current.isDynamic() 
				? current.isValidProperty().get() && hasNext()
				: hasNext();
	}

	@Override
	public boolean canDoFinish() {
		WizardPage<Properties> current = currentPageProperty().get();
		boolean canFinish = current.canFinishEarlierProperty().get() || !hasNext();
		return  current.isDynamic() 
				? current.isValidProperty().get() && canFinish
				: canFinish;
	}
}
