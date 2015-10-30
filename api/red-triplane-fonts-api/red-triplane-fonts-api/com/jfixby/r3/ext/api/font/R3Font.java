package com.jfixby.r3.ext.api.font;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.r3.api.resources.manager.PackageReader;

public class R3Font {

	static private ComponentInstaller<R3FontComponent> componentInstaller = new ComponentInstaller<R3FontComponent>(
			"R3Font");

	public static final void installComponent(
			R3FontComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final R3FontComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final R3FontComponent component() {
		return componentInstaller.getComponent();
	}

	public static PackageReader getPackageReader() {
		return invoke().getPackageReader();
	}

}