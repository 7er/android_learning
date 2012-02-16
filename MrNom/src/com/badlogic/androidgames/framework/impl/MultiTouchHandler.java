package com.badlogic.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import com.badlogic.androidgames.framework.TouchHandler;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class MultiTouchHandler implements TouchHandler {
	float scaleX;
	float scaleY;
	boolean[] isTouched = new boolean[20];
	int[] touchX = new int[20];
	int[] touchY = new int[20];
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();

	public MultiTouchHandler(View view, float scaleX, float scaleY) {
		view.setOnTouchListener(this);
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
	     synchronized (this) {
	            int action = event.getAction() & MotionEvent.ACTION_MASK;
	            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
	            int pointerId = event.getPointerId(pointerIndex);
	            TouchEvent touchEvent;

	            switch (action) {
	            case MotionEvent.ACTION_DOWN:
	            case MotionEvent.ACTION_POINTER_DOWN:
	                touchEvent = new TouchEvent();
	                touchEvent.type = TouchEvent.TOUCH_DOWN;
	                touchEvent.pointer = pointerId;
	                touchEvent.x = touchX[pointerId] = (int) (event
	                        .getX(pointerIndex) * scaleX);
	                touchEvent.y = touchY[pointerId] = (int) (event
	                        .getY(pointerIndex) * scaleY);
	                isTouched[pointerId] = true;
	                touchEventsBuffer.add(touchEvent);
	                break;

	            case MotionEvent.ACTION_UP:
	            case MotionEvent.ACTION_POINTER_UP:
	            case MotionEvent.ACTION_CANCEL:
	                touchEvent = new TouchEvent();
	                touchEvent.type = TouchEvent.TOUCH_UP;
	                touchEvent.pointer = pointerId;
	                touchEvent.x = touchX[pointerId] = (int) (event
	                        .getX(pointerIndex) * scaleX);
	                touchEvent.y = touchY[pointerId] = (int) (event
	                        .getY(pointerIndex) * scaleY);
	                isTouched[pointerId] = false;
	                touchEventsBuffer.add(touchEvent);
	                break;

	            case MotionEvent.ACTION_MOVE:
	                int pointerCount = event.getPointerCount();
	                for (int i = 0; i < pointerCount; i++) {
	                    pointerIndex = i;
	                    pointerId = event.getPointerId(pointerIndex);

	                    touchEvent = new TouchEvent();
	                    touchEvent.type = TouchEvent.TOUCH_DRAGGED;
	                    touchEvent.pointer = pointerId;
	                    touchEvent.x = touchX[pointerId] = (int) (event
	                            .getX(pointerIndex) * scaleX);
	                    touchEvent.y = touchY[pointerId] = (int) (event
	                            .getY(pointerIndex) * scaleY);
	                    touchEventsBuffer.add(touchEvent);
	                }
	                break;
	            }

	            return true;
	        }
	}

	@Override
	public boolean isTouchDown(int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTouchX(int pointer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTouchY(int pointer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		synchronized(this) {
		List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
		touchEvents.addAll(touchEventsBuffer);
		touchEventsBuffer.clear();
		return touchEvents;
		}
		
	}

}
