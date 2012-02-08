package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

class ShapeRenderView extends View {
	private Paint redPaint;
	private Paint greenPaint;
	private Paint translucentBlue;

	public ShapeRenderView(Context context) {
		super(context);
		redPaint = new Paint();
		redPaint.setColor(Color.RED);
		redPaint.setStrokeWidth(4);

		greenPaint = new Paint();
		greenPaint.setStyle(Style.STROKE);
		greenPaint.setColor(0xff00ff00);
		greenPaint.setStrokeWidth(2);

		translucentBlue = new Paint();
		translucentBlue.setStyle(Style.FILL);
		translucentBlue.setColor(0x770000ff);
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(255, 255, 255);
		canvas.drawLine(0, 0, canvas.getWidth() - 1,
				canvas.getHeight() - 1, redPaint);
		canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2,
				canvas.getWidth() / 4, greenPaint);
		Point top = new Point(100, canvas.getHeight() / 4);
		canvas.drawRect(top.x, top.y, top.x + 100, top.y + 100, translucentBlue);
		invalidate();
	}
}


public class ShapeTest extends Activity {
	private WakeLock wakeLock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PowerManager powerManager =
				(PowerManager)this.getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new ShapeRenderView(this));
	}
	
	@Override
	protected void onResume() {
		wakeLock.acquire();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		wakeLock.release();
		super.onPause();
	}
}
