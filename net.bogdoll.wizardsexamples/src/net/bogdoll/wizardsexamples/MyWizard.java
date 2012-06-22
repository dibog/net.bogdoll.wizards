package net.bogdoll.wizardsexamples;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import net.bogdoll.wizards.WizardPane;


public class MyWizard 
{
	public static void main(String[] args) 
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		
		Properties props = new Properties();
		WizardPane pane = new WizardPane(new MyPageProvider(), props);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(pane.getVisual());
		frame.setSize(640,400);
		frame.setVisible(true);
	}
}