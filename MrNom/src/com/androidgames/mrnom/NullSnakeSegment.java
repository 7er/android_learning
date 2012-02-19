package com.androidgames.mrnom;

public class NullSnakeSegment implements ISnakeSegment {
	public void setNext(ISnakeSegment segment) {
		throw new IllegalStateException();
	}
	public boolean equals(Object otherObject) {
		return otherObject instanceof NullSnakeSegment;
	}
	
	public boolean isNull() { return true; }
	
	public SnakeSegment head() {
		throw new IllegalStateException();
	}
	
	public void slide() {
		throw new IllegalStateException();
	}
	public int getX() {
		throw new IllegalStateException();
	}
	public int getY() {
		throw new IllegalStateException();
	}
}
