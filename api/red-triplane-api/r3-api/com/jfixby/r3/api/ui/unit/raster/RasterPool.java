
package com.jfixby.r3.api.ui.unit.raster;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.r3.api.ui.unit.ComponentsFactory;
import com.jfixby.r3.api.ui.unit.layer.VisibleComponent;

public interface RasterPool extends VisibleComponent {

	public AssetID getAssetID ();

	public ComponentsFactory getComponentsFactory ();

	public Raster newInstance ();

}