
package com.jfixby.r3.activity.api.input;

import com.jfixby.r3.activity.api.raster.Raster;
import com.jfixby.scarabei.api.collections.Collection;

public interface InputSpecs {
	String getName ();

	void setName (String button_name);

	void addTouchArea (TouchAreaSpecs touch_area);

	public Collection<TouchAreaSpecs> listTouchAreas ();

	void addOption (Raster raster);

	Collection<Raster> listOptions ();

}