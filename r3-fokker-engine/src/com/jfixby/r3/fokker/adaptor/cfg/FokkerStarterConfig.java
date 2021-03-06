
package com.jfixby.r3.fokker.adaptor.cfg;

import java.util.HashMap;

import com.jfixby.scarabei.api.log.L;

public class FokkerStarterConfig {

	public static final String TITLE = "title";
	public static final String useGL30 = "useGL30";
	public static final String width = "width";
	public static final String height = "height";
	public static final String fullscreen = "fullscreen";

	public static final String PACKAGE_FORMAT = "RedTriplane.Fokker.StarterConfig";
	public static final String FILE_NAME = "r3-fokker-starter-config.json";

	public HashMap<String, String> params = new HashMap<>();

	public String getValue (final String key) {
		return this.params.get(key);
	}

	public void print () {
		L.d("FokkerStarterConfig", this.params);
	}

}
