package com.jfixby.red.triplane.fokker.assembler;

import java.io.IOException;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.File;
import com.jfixby.tool.eclipse.dep.EclipseProjectInfo;

public class DesktopAssembley extends AbstractAssembley {

	public DesktopAssembley(FokkerAssembley fokkerAssembley, List<EclipseProjectInfo> dependency_list,
			File gradle_output_project_folder)  {
		super(fokkerAssembley, dependency_list,gradle_output_project_folder);
	}
}