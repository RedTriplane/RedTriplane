
package com.jfixby.r3.activity.api.input;

import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;

public interface MouseEvent {

	ReadOnlyFloat2 getCanvasPosition ();

	int getPointerNumber ();

}