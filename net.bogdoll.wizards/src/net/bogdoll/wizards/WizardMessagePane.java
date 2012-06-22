package net.bogdoll.wizards;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WizardMessagePane implements PropertyChangeListener
{
	private JPanel mVisual;
	
	public WizardMessagePane(WizardController<?> aController)
	{
		aController.addPropertyChangeListener(WizardController.PROP_PAGE_MESSAGES, this);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(WizardController.PROP_PAGE_MESSAGES) && mVisual!=null) {
			mVisual.removeAll();
			@SuppressWarnings("unchecked")
			List<Message> messages = (List<Message>) evt.getNewValue();
			for(Message m : messages)
			{
				mVisual.add(label(m));
			}
			Container parent = mVisual.getParent();
			if(parent!=null) {
				parent.validate();
				parent.repaint();
			}			
		}
	}

	private JLabel label(Message m)  {
		BufferedImage icon = null;
		
		try {
			switch(m.status) {
				case ERROR: icon = ImageIO.read(getClass().getResource("error.png")); break;
				case WARN: icon = ImageIO.read(getClass().getResource("warn.png")); break;
				case INFO: icon = ImageIO.read(getClass().getResource("info.png")); break;
				default: icon = ImageIO.read(getClass().getResource("debug.png")); break;
			}
			return new JLabel(m.message, new ImageIcon(icon), JLabel.LEFT);
		}
		catch(IOException e) 
		{
			return new JLabel(m.message);
		}
		
	}

	public JPanel getVisual() {
		if(mVisual==null) {
			mVisual = new JPanel();
			mVisual.setLayout(new BoxLayout(mVisual, BoxLayout.Y_AXIS));
			mVisual.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		}
		return mVisual;
	}
}