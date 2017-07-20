
package com.jfixby.r3.ui.api.activity;

public interface Activity {

	public void onCreate (final ActivityManager unitManager);

	public void onStart ();

	public void onResume ();

	public void onPause ();

	public void onDestroy ();

}
