
package com.jfixby.r3.activity.red.act;

import com.jfixby.scarabei.api.sys.Sys;

public class WaitEvent extends UIEvent {

	private final long period;
	private final RedUIManager tintoUIManager;

	float value_begin;
	float value_end;
	float value_current;

	@Override
	public String toString () {
		return "WaitEvent period=" + this.period + "";
	}

	public WaitEvent (final long period, final RedUIManager tintoUIManager) {
		this.period = period;
		this.tintoUIManager = tintoUIManager;

	}

	private long timestamp_begin;
	private long timestamp_end;
	private long timestamp_current;

	@Override
	public void go () {
		this.value_current = this.value_begin;
		this.timestamp_begin = Sys.SystemTime().currentTimeMillis();
		this.timestamp_end = this.timestamp_begin + this.period;

		// L.d("Fade Event: " + timestamp_begin, timestamp_end);
		// L.d(" " + value_begin, value_end);

	}

	@Override
	public boolean isOver () {
		return Sys.SystemTime().currentTimeMillis() > this.timestamp_end;
	}

}
