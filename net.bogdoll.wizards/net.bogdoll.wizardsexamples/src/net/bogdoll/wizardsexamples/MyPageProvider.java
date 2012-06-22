package net.bogdoll.wizardsexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.bogdoll.wizards.WizardPage;
import net.bogdoll.wizards.WizardPageProvider;

public class MyPageProvider implements WizardPageProvider<Properties> 
{
	private List<WizardPage<Properties>> mPages;
	private int mCurrent = 0;
	
	public MyPageProvider() {
		mPages = new ArrayList<WizardPage<Properties>>();
		mPages.add(new Page1());
		mPages.add(new PageN("Page #2"));
		mPages.add(new PageN("Page #3"));
		mPages.add(new PageN("Page #4"));
		mPages.add(new PageN("Page #5"));
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
		}
	}

	@Override
	public void next() {
		if(hasNext()) {
			mCurrent++;
		}
	}
	
	@Override
	public WizardPage<Properties> getCurrent() {
		return mPages.get(mCurrent);
	}

	@Override
	public boolean canFinish() {
		return !hasNext();
	}
}
