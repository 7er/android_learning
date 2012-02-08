package com.badlogic.androidgames;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FastRenderView extends SurfaceView implements Runnable {

	private SurfaceHolder holder;
	private volatile boolean running = false;
	private Thread renderThread;
	private Random rand = new Random();
	

	public FastRenderView(Context context) {
		super(context);
		holder = getHolder();
	}

	void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	public void run() {
		while (running) {
			if (!holder.getSurface().isValid()) {
				continue;
			}
			Canvas canvas = holder.lockCanvas();
			canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				return;
			} catch (InterruptedException e) {
				// retry
			}
		}
	}
}
