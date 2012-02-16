package com.androidgames.framework.impl;

import com.androidgames.framework.Audio;
import com.androidgames.framework.FileIO;
import com.androidgames.framework.Game;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Input;
import com.androidgames.framework.Screen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);

		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight
				/ getWindowManager().getDefaultDisplay().getHeight();

		PowerManager powerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"GLGame");

		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
	}

	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(Screen screen) {
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;

	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPause() {
		super.onPause();
		renderView.pause();
		screen.pause();
		wakeLock.release();
		if (isFinishing()) {
			screen.dispose();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

}