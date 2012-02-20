package com.androidgames.mrnom;

public class Snake {

	private ISnakeSegment tail = new NullSnakeSegment();
	int direction;
	public ISnakeSegment getTail() { return tail; }
	public static Snake createDefault() {
		ISnakeSegment newTail = new SnakeSegment(2, 2);
		ISnakeSegment newTail1 = new SnakeSegment(1, 2);
		newTail.setNext(newTail1);
		newTail1.setNext(new SnakeSegment(1, 1));
		return new Snake(Direction.UP, newTail);
	}
	public Snake(int direction, ISnakeSegment tail) {
		this.tail = tail;
		this.direction = direction;
	}
	
	public boolean equals(Object otherObject) {
		Snake other = (Snake) otherObject;
		return this.direction == other.direction && this.tail.equals(other.tail);
	}

	public void slide(World world) {
		ISnakeSegment current = tail;
		current.slide();
		SnakeSegment head = current.head();
		switch (direction) {
		case Direction.UP:
			int y = head.getY() - 1;
			if (y < 0) {
				y = World.maxY;
			}	
			head.setPosition(head.getX(), y);
			break;
		case Direction.DOWN:
			int y2 = head.getY() + 1;
			if (y2 > World.maxY) {
				y2 = 0;
			}
			head.setPosition(head.getX(), y2);
			break;
		case Direction.LEFT:
			int x2 = head.getX() - 1;
			if (x2 < 0) {
				x2 = World.maxX;
			}
			head.setPosition(x2, head.getY());
			break;
		case Direction.RIGHT:
			int x = head.getX() + 1;
			if (x > World.maxX) {
				x = 0;
			}
			head.setPosition(x, head.getY());
			break;
		}

	}
	public void turnLeft() {
		if (direction == Direction.UP) {
			direction = Direction.LEFT;
		}
		else {
			direction -= 1;
		}
	}
	public void turnRight() {
		if (direction == Direction.LEFT) {
			direction = Direction.UP;
		}
		else {
			direction += 1;
		}
	}
}
