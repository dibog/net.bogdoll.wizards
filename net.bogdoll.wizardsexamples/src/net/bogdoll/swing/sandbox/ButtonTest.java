package net.bogdoll.swing.sandbox;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.bogdoll.swing.RadioButtonGroup;

public class ButtonTest implements ActionListener {
	private RadioButtonGroup mButtonGroup = new RadioButtonGroup();

	public ButtonTest() {
		mButtonGroup.addActionListener(this);
	}
	
	public static void main(String[] args) {
		ButtonTest test = new ButtonTest();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,400);
		frame.setContentPane(test.buildUI());
		frame.setVisible(true);
	}

	private Container buildUI() 
	{
		JPanel panel = new JPanel();
		
		JRadioButton b = new JRadioButton("Button #1");
		b.setActionCommand("b1");
		mButtonGroup.add(b);
		panel.add(b);
		
		b = new JRadioButton("Button #2");
		b.setActionCommand("b2");
		mButtonGroup.add(b);
		panel.add(b);

		b = new JRadioButton("Button #3");
		b.setActionCommand("b3");
		mButtonGroup.add(b);
		panel.add(b);


		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("cmd: "+e.getActionCommand());
	}
}
