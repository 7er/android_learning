package com.androidgames.mrnom;

public interface ISnakeSegment {
	public void setNext(ISnakeSegment segment);
	public boolean isNull();
	public SnakeSegment head();
	public void slide();
	public int getX();
	public int getY();
}