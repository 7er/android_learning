package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {
	FastRenderView view;
	private WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PowerManager powerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"My Lock");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new FastRenderView(this);
		setContentView(view);
	}

	@Override
	protected void onPause() {
		view.pause();
		wakeLock.release();
		super.onPause();
	}

	@Override
	protected void onResume() {
		view.resume();
		wakeLock.acquire();
		super.onResume();
	}
}
