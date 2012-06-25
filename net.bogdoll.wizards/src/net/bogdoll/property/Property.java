package net.bogdoll.property;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Property<T> 
{
	private PropertyChangeSupport mPCS = new PropertyChangeSupport(this);
	private T mValue;
	
	public Property() {}
	
	public Property(T aInitValue) {
		mValue = aInitValue;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		mPCS.addPropertyChangeListener(aListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener aListener) {
		mPCS.removePropertyChangeListener(aListener);
	}

	public T get() {
		return mValue;
	}
	
	public void set(T aNewValue) {
		T old = mValue;
		mValue = aNewValue;
		mPCS.firePropertyChange("value", old, mValue);
	}
	
	@Override
	public String toString() {
		return ""+mValue;
	}

	public void pulse() {
		mPCS.firePropertyChange("value", null, mValue);
	}
}
