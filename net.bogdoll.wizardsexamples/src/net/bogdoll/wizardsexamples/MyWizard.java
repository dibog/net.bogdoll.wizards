package net.bogdoll.wizardsexamples;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import net.bogdoll.wizards.WizardController;
import net.bogdoll.wizards.WizardDialog;


public class MyWizard 
{
	public static void main(String[] args) 
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		
		WizardDialog<Properties> wizard = WizardDialog.create(new MyPageProvider(), new Properties());
		wizard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		wizard.setSize(640,400);
		
		
		Object result = wizard.showWizard();
		
		if(result==WizardController.FINISHED_OPTION) {
			System.out.println("Finished");
			Properties props = wizard.getContext();
			System.out.println(props);
		}
		else if(result==WizardController.CANCELED_OPTION) {
			System.out.println("Canceled");
		} 
		else {
			System.out.println("Unknown");
		}
	}
}