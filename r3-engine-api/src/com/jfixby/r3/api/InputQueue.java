package com.jfixby.r3.api;

import java.util.Iterator;

public interface InputQueue {

	int size();

	Iterator<InputEvent> getIterator();

}
