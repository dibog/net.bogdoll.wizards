package net.bogdoll.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.event.EventListenerList;

public class RadioButtonGroup extends ButtonGroup implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	private EventListenerList mListenerList = new EventListenerList();
	
	public void addActionListener(ActionListener aListener) {
		mListenerList.add(ActionListener.class, aListener);
	}
	
	public void removeActionListener(ActionListener aListener) {
		mListenerList.remove(ActionListener.class, aListener);
	}

	@Override
	public void add(AbstractButton aButton) {
		super.add(aButton);
		aButton.addActionListener(this);
	}
	
	@Override
	public void remove(AbstractButton aButton) {
		aButton.removeActionListener(this);
		super.remove(aButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(ActionListener listener : mListenerList.getListeners(ActionListener.class))
		{
			try {
				listener.actionPerformed(e);
			}
			catch(RuntimeException re) {
			}
		}
	}
}