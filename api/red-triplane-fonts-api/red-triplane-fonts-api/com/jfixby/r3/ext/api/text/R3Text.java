
package com.jfixby.r3.ext.api.text;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.rana.api.pkg.PackageReader;

public class R3Text {

	static private ComponentInstaller<R3TextComponent> componentInstaller = new ComponentInstaller<R3TextComponent>("R3Text");

	public static final void installComponent (final R3TextComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final R3TextComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final R3TextComponent component () {
		return componentInstaller.getComponent();
	}

	public static PackageReader getStringsPackageReader () {
		return invoke().getStringsPackageReader();
	}

	public static PackageReader getTextPackageReader () {
		return invoke().getTextPackageReader();
	}

}
