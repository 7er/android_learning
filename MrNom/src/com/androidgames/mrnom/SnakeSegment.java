package com.androidgames.mrnom;


public class SnakeSegment implements ISnakeSegment {
	private int x;
	private int y;
	private ISnakeSegment next = new NullSnakeSegment();
	
	public ISnakeSegment next() { return next; }

	public SnakeSegment(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof SnakeSegment)) {
			return false;
		}
		SnakeSegment other = (SnakeSegment) otherObject;
		return this.x == other.x && this.y == other.y && this.next.equals(other.next);
	}
	
	@Override
	public void setNext(ISnakeSegment segment) {
		next = segment;
	}

	public boolean isNull() { return false; }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
				
	}
	
	public SnakeSegment head() {
		if (this.next.isNull()) {
			return this;
		}
		return this.next.head();
	}
	
	public void slide() {
		if (this.next.isNull()) {
			return;
		}
		this.setPosition(this.next.getX(), this.next.getY());
		this.next.slide();
	}
}
