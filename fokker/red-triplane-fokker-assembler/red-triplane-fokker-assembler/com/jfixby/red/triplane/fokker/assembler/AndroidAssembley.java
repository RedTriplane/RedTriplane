package com.jfixby.red.triplane.fokker.assembler;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.tool.eclipse.dep.EclipseProjectInfo;

public class AndroidAssembley extends AbstractAssembley {

	public AndroidAssembley(FokkerAssembley fokkerAssembley, List<EclipseProjectInfo> dependency_list) {
		super(fokkerAssembley, dependency_list);
	}

}
