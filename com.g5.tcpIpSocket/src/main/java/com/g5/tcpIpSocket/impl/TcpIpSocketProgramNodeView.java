package com.g5.tcpIpSocket.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class TcpIpSocketProgramNodeView implements SwingProgramNodeView<TcpIpSocketProgramNodeContribution> {

	private JTextField inputIP;
	private JTextField inputPORT;
	
	// Create instance of JTextField for each field
	public TcpIpSocketProgramNodeView(ViewAPIProvider apiProvider) {
		this.inputIP = new JTextField();
		this.inputPORT = new JTextField();
	}


	// Here we have the program panel, and we add each element  to the panel
	@Override
	public void buildUI(JPanel panel, ContributionProvider<TcpIpSocketProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createDescription("Connect to robot providing IP and PORT"));
		panel.add(verticalSpace(25));
		panel.add(createTextInput("IP: ", provider, inputIP));
		panel.add(verticalSpace(5));
		panel.add(createTextInput("PORT: ", provider, inputPORT));

	}
	
	private Box createDescription(String description) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel label = new JLabel(description);
		box.add(label);
		return box;
	}
	
	private Box createTextInput(String description, final ContributionProvider<TcpIpSocketProgramNodeContribution> provider, final JTextField textInput) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel label = new JLabel(description);
		label.setPreferredSize(new Dimension(60, 24));
		label.setMaximumSize(label.getPreferredSize());
		box.add(label);
		box.add(horizontalSpace(20));
		textInput.setFocusable(false);
		textInput.setPreferredSize(new Dimension(200, 24));
		textInput.setMaximumSize(textInput.getPreferredSize());
		
		// Enable keyboard for input
		textInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (textInput == inputIP) {
					KeyboardTextInput keyboardInput = provider.get().getKeyboardInputValue("IP");
					keyboardInput.show(textInput, provider.get().keyboardCallBack("IP"));					
				}
				else if(textInput == inputPORT) {
					KeyboardTextInput keyboardInput = provider.get().getKeyboardInputValue("PORT");
					keyboardInput.show(textInput, provider.get().keyboardCallBack("PORT"));
				}
			}
		});
		
		box.add(textInput);
		return box;
	}

	private Component horizontalSpace(int hSpace) {
		return Box.createRigidArea(new Dimension(hSpace, 0));
	}
	
	private Component verticalSpace(int vSpace) {
		return Box.createRigidArea(new Dimension(0, vSpace));
	}
	
	public void setInput(final String value, final String textID) {
		if ("IP".equals(textID)) {
			inputIP.setText(value);			
		}
		else if("PORT".equals(textID)) {
			inputPORT.setText(value);
		}
	}
	
}
