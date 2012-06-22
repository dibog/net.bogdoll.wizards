package net.bogdoll.swing;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextModel extends PlainDocument
{
	private static final long serialVersionUID = 1L;

	public TextModel() 
	{}
	
	public TextModel(String aText) {
		setText(aText);
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
}