package net.bogdoll.wizards;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.bogdoll.property.AnnotatedProperty;
import net.bogdoll.swing.Listener;

class WizardMessagePane 
{
	private JPanel mVisual;
	private AnnotatedProperty<List<Message>> mValidationMessages;
	
	public WizardMessagePane(WizardController<?> aController)
	{
		AnnotatedProperty.connect(this, aController.currentPageProperty(), WizardController.PROP_CURRENT_PAGE);
	}

	@SuppressWarnings("unused")
	@Listener(WizardController.PROP_CURRENT_PAGE)
	private void newCurrentPage(WizardPage<?> aOld, WizardPage<?> aNew) {
		if(mValidationMessages!=null) {
			mValidationMessages.disconnect();
		}
		if(aNew!=null) {
			mValidationMessages = AnnotatedProperty.connect(this, aNew.validationMessagesProperty(), WizardController.PROP_PAGE_MESSAGES);
		}
	}

	@SuppressWarnings("unused")
	@Listener(WizardController.PROP_PAGE_MESSAGES)
	private void newValidationMessages(List<Message> aOld, List<Message> aNew) {
		if(mVisual!=null) {
			mVisual.removeAll();
			for(Message m : aNew)
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