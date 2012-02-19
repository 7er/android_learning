package com.androidgames.mrnom;

public class NullSnakeSegment implements ISnakeSegment {

	@Override
	public void setNext(ISnakeSegment segment) {
		throw new IllegalStateException();
	}
	public boolean equals(Object otherObject) {
		return otherObject instanceof NullSnakeSegment;
	}
}
