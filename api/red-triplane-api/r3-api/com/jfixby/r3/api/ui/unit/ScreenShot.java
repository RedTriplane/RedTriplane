
package com.jfixby.r3.api.ui.unit;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.image.ColorMap;

public interface ScreenShot {

	ColorMap toColorMap ();

	void saveToFile (File screenSHotFile);

}
