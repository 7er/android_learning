package com.androidgames.mrnom;

public class NullSnakeSegment implements ISnakeSegment {

	@Override
	public void setNext(ISnakeSegment segment) {
		// TODO Auto-generated method stub

	}
	public boolean equals(Object otherObject) {
		return (NullSnakeSegment) otherObject != null;
	}
}
