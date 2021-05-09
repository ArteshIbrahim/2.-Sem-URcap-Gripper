package com.g5.OpenAndClose.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;

public class OpenAndCloseProgramNodeContribution implements ProgramNodeContribution {


	private static final boolean openGripper = false;
	private static final boolean closeGripper = false;
	
	private final ProgramAPIProvider apiProvider;
	private final OpenAndCloseProgramNodeView view;
	private final DataModel model;
	private UndoRedoManager undoRedoManager;
	String status = "none";
	
	
	// Constructer
	public OpenAndCloseProgramNodeContribution(ProgramAPIProvider apiProvider, OpenAndCloseProgramNodeView view, DataModel model) {
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
		this.undoRedoManager = this.apiProvider.getProgramAPI().getUndoRedoManager();
	}
	
	// What should  happen when view is rendered
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		view.setButtonValue("openGripper", true);
		view.setButtonValue("closeGripper", true);
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	// The title showing in left side
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		
		if (!getFieldValue("openGripper") && getFieldValue("closeGripper")) {
			status = "open";
		}else if (getFieldValue("openGripper") && !getFieldValue("closeGripper")){
			status = "close";
		}
		
		return "Gripper is set to "+status;
	}
	
	// URCap can be ready from yellow to green when open or close is selected. otherwise it will ask to select an optiopn
	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		// if status variable is not none
		return status != "none";
	}

	
	public String gripperAction() {
		String action = "";
		if(!getFieldValue("openGripper")) {
			action = "Open";
		}else {
			action = "Close";
		}
		return action;
	}
	
	@Override
	public void generateScript(ScriptWriter writer) {
		writer.appendLine("socket_send_string(\"" + gripperAction() +" " + " Gripper\")");
		writer.appendLine("reply_from_server = \"\"");
		writer.appendLine("while reply_from_server == \"\" :");
		writer.appendLine("reply_from_server = socket_read_string()");
		writer.appendLine("end");
		//writer.appendLine("socket_send_string(\"Robot has done action: " + getFieldValue("openGripper") + "\")");
		writer.sleep(0.2);
		
	}
	
	
	// Returns the value of the model.
	private boolean getFieldValue(String ID) {
		if (ID == "openGripper") {
			return model.get("openGripper", openGripper);	
		}
		else if (ID == "closeGripper"){
			return model.get("closeGripper", closeGripper);
		}else {
			return false;
		}
	}

	
	
	
	
	public void setModelValue(final String ID, final boolean value) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			@Override
			public void executeChanges() {
				if (ID == "openGripper") {
					//model.remove("openGripper");
					model.set("openGripper", value);
				}
				else if (ID == "closeGripper"){
					//model.remove("closeGripper");
					model.set("closeGripper", value);	
				}
			}
		});
	}
	
}
