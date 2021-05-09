package com.g5.tcpIpSocket.impl;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputFactory;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class TcpIpSocketProgramNodeContribution implements ProgramNodeContribution {
	
	
	private static final String IP = "192.168.104.180";
	private static final String PORT = "10000";
	
	
	private final ProgramAPIProvider apiProvider;
	private final TcpIpSocketProgramNodeView view;
	private final DataModel model;
	private final KeyboardInputFactory keyboardFactory;
	private final UndoRedoManager undoRedoManager;

	
	
	public TcpIpSocketProgramNodeContribution(ProgramAPIProvider apiProvider, TcpIpSocketProgramNodeView view, DataModel model) {
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
		this.keyboardFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getKeyboardInputFactory();
		this.undoRedoManager = this.apiProvider.getProgramAPI().getUndoRedoManager();
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		view.setInput(getFieldName("IP"), "IP");
		view.setInput(getFieldName("PORT"), "PORT");	
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Connect to " + getFieldName("IP") + " on port " + getFieldName("PORT");
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		writer.appendLine("open = socket_open(\"" + getFieldName("IP") + "\", " + getFieldName("PORT") + ")");
		writer.appendLine("while not open :");
		writer.appendLine("open = socket_open(\"" + getFieldName("IP") + "\", " + getFieldName("PORT") + ")");
		writer.appendLine("end");
		writer.appendLine("socket_send_string(\"Connected succesfully .\")");
		writer.sleep(0.2);
	}

	
	
	private String getFieldName(String ID) {
		if ("IP".equals(ID)) {
			return model.get(IP, IP);	
		}
		else if ("PORT".equals(ID)){
			return model.get(PORT, PORT);
		}else {
			return null;
		}
	}
	
	
	// For debuging only !
	public static void setErrorPopup(String message, String title){
		JOptionPane.showMessageDialog(new JFrame(),message, title,JOptionPane.ERROR_MESSAGE);
	}

	
	public KeyboardTextInput getKeyboardInputValue(String textID) {
		KeyboardTextInput keyboardInput = keyboardFactory.createStringKeyboardInput();
		keyboardInput.setInitialValue(getFieldName(textID));
		return keyboardInput;
	}
	

	
	public KeyboardInputCallback<String> keyboardCallBack(final String textID) {
		return new KeyboardInputCallback<String>() {
			@Override
			public void onOk(String value) {
					//setErrorPopup(textID, value);
					//System.out.println(textID.equals("IP"));
					
					if(textID.equals("IP")) {
						setInputValue(value, "IP");
						view.setInput(value, "IP");
					}else if(textID.equals("PORT")) {
						setInputValue(value, "PORT");
						view.setInput(value, "PORT");
					}
				}
		};
	}
	
	
	public void setInputValue(final String value, final String textID) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			@Override
			public void executeChanges() {
				if ("IP".equals(textID)) {
					if ("".equals(value)) {
						model.remove(IP);
					} else {
						model.set(IP, value);
					}	
				}
				else if ("PORT".equals(textID)){
					if ("".equals(value)) {
						model.remove(PORT);
					} else {
						model.set(PORT, value);
					}
				}
			}
		});
	}
}
