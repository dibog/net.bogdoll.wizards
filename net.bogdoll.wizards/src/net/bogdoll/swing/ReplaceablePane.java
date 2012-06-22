package net.bogdoll.swing;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ReplaceablePane extends JPanel 
{
	private static final long serialVersionUID = 8155326043736202571L;

	public ReplaceablePane() {
		super(new BorderLayout());
	}
	
	public ReplaceablePane(JComponent aComponent) {
		super(new BorderLayout());
		add(aComponent, BorderLayout.CENTER);
	}

	public void setComponent(JComponent aComponent) {
		removeAll();
		add(aComponent, BorderLayout.CENTER);
		validate();
		repaint();
	}
}
