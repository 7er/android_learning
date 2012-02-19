package com.androidgames.mrnom;

public class Snake {
	private ISnakeSegment tail = null;
	public enum Direction {
		UP
	}
	private Direction direction;
	public static Snake createDefault() {
		ISnakeSegment newTail = new SnakeSegment(2, 2);
		ISnakeSegment newTail1 = new SnakeSegment(1, 2);
		newTail.setNext(newTail1);
		newTail1.setNext(new SnakeSegment(1, 1));
		return new Snake(Direction.UP, newTail);
	}
	public Snake(Direction direction, ISnakeSegment tail) {
		this.tail = tail;
		this.direction = direction;
	}
	
	public boolean equals(Object otherObject) {
		Snake other = (Snake) otherObject;
		boolean temp = this.direction == other.direction;
		if (this.tail == null) {
			return temp && this.tail == other.tail;
		}
		return temp && other.tail != null && this.tail.equals(other.tail);
	}

	public void move() {
	}

}
