package com.g5.OpenAndClose.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class OpenAndCloseProgramNodeView implements SwingProgramNodeView<OpenAndCloseProgramNodeContribution>{

	
	
	private JButton openButton;
	private JButton closeButton;

	
	public OpenAndCloseProgramNodeView(ViewAPIProvider apiProvider) {
		this.closeButton = new JButton("Close");
		this.openButton = new JButton("Open");
	}
		
	@Override
	public void buildUI(JPanel panel, final ContributionProvider<OpenAndCloseProgramNodeContribution> provider) {
		// TODO Auto-generated method stub
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createDescription("Select open or close"));
		panel.add(verticalSpace(25));
		
		
		// create a box so buttons are beside each other and not under each other.
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		// change button size
		openButton.setPreferredSize(new Dimension(250, 50));
		openButton.setMaximumSize(openButton.getPreferredSize());
		
		// change button size
		closeButton.setPreferredSize(new Dimension(250, 50));
		closeButton.setMaximumSize(openButton.getPreferredSize());
		
		
		// Detect click on open
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					System.out.println("Open");
					
					// Disable open button
					setButtonValue("openGripper", false);
					
					// Change model value of openGripper to false
					provider.get().setModelValue("openGripper", false);
					
					// Enable close button
					setButtonValue("closeGripper", true);
				
					// Change model value of closeGripper to true
					provider.get().setModelValue("closeGripper", true);
				}
		});
		
		// Detect click on close
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					System.out.println("Close");
					setButtonValue("closeGripper", false);
					provider.get().setModelValue("closeGripper", false);
					setButtonValue("openGripper", true);
					provider.get().setModelValue("openGripper", true);
				}
		});
		
		// Add buttons to panel
		box.add(openButton);
		box.add(closeButton);
	
		panel.add(box);
	}
	
	private Box createDescription(String description) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel label = new JLabel(description);
		box.add(label);
		return box;
	}
	
	private Component verticalSpace(int vSpace) {
		return Box.createRigidArea(new Dimension(0, vSpace));
	}
	
	public void setButtonValue(final String ID, final boolean value) {

		if (ID == "openGripper") {
			openButton.setEnabled(value);
		}
		else if(ID == "closeGripper") {
			closeButton.setEnabled(value);
		}
	}

}
