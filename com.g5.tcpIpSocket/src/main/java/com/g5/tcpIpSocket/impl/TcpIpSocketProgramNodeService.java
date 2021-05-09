package com.g5.tcpIpSocket.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class TcpIpSocketProgramNodeService implements SwingProgramNodeService<TcpIpSocketProgramNodeContribution, TcpIpSocketProgramNodeView>{

	@Override
	public String getId() {
		return "tcpipsocketnode";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setChildrenAllowed(false);
	}

	@Override
	public String getTitle(Locale locale) {
		return "TCP/IP Socket";
	}

	@Override
	public TcpIpSocketProgramNodeView createView(ViewAPIProvider apiProvider) {
		return new TcpIpSocketProgramNodeView(apiProvider);
	}

	@Override
	public TcpIpSocketProgramNodeContribution createNode(ProgramAPIProvider apiProvider,
			TcpIpSocketProgramNodeView view, DataModel model, CreationContext context){
		return new TcpIpSocketProgramNodeContribution(apiProvider, view, model);
	}

}
