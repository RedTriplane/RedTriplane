
package com.jfixby.r3.scene2d.io;

import java.io.Serializable;

public class ProgressSettings implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -141831665283946796L;

	public enum TYPE {
		SIMPLE_LINE;
	}

	public TYPE type;

}
