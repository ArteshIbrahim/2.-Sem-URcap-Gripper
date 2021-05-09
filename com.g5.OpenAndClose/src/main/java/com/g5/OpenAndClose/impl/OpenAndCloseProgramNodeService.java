package com.g5.OpenAndClose.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class OpenAndCloseProgramNodeService implements SwingProgramNodeService<OpenAndCloseProgramNodeContribution, OpenAndCloseProgramNodeView> {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "openandclosegripper";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		configuration.setChildrenAllowed(false);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Open And Close Gripper";
	}

	@Override
	public OpenAndCloseProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new OpenAndCloseProgramNodeView(apiProvider);
	}

	@Override
	public OpenAndCloseProgramNodeContribution createNode(ProgramAPIProvider apiProvider,
			OpenAndCloseProgramNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new OpenAndCloseProgramNodeContribution(apiProvider, view, model);
	}

}
