package com.jfixby.r3.api.ui.unit.input;

import com.jfixby.cmns.api.floatn.FixedFloat2;

public interface TouchUpEvent {

    FixedFloat2 getCanvasPosition();

    int getPointerNumber();

}
