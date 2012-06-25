package net.bogdoll.swing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextModel extends PlainDocument
{
	private static final long serialVersionUID = 1L;
	public final static String PROP_TEXT = "text";
	private PropertyChangeSupport mPCS = new PropertyChangeSupport(this);
	
	public TextModel() 
	{}

	public TextModel(String aText) {
		setText(aText);
	}
	
	public void addPropertyChangeListner(PropertyChangeListener aListener) {
		mPCS.addPropertyChangeListener(aListener);
	}

	public void addPropertyChangeListner(String aProperty, PropertyChangeListener aListener) {
		mPCS.addPropertyChangeListener(aProperty, aListener);
	}

	public void removePropertyChangeListner(PropertyChangeListener aListener) {
		mPCS.removePropertyChangeListener(aListener);
	}

	public void removePropertyChangeListner(String aProperty, PropertyChangeListener aListener) {
		mPCS.removePropertyChangeListener(aProperty, aListener);
	}

	public void setText(String aText) {
		try {
			replace(0,  getLength(), aText, null);
		} catch (BadLocationException e) {
		}
	}
	
	public String getText() {
		try {
			return getText(0, getLength());
		} catch (BadLocationException e) {
			return "";
		}
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException 
	{
		String old = getText();
		super.insertString(offs, str, a);
		mPCS.firePropertyChange(PROP_TEXT, old, getText());
	}
	
	@Override
	public void remove(int offs, int len) throws BadLocationException 
	{
		String old = getText();
		super.remove(offs, len);
		mPCS.firePropertyChange(PROP_TEXT, old, getText());
	}
	
	@Override
	public void replace(int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException 
	{
		String old = getText();
		super.replace(offset, length, text, attrs);
		mPCS.firePropertyChange(PROP_TEXT, old, getText());
	}
	
}