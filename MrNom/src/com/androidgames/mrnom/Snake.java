package com.androidgames.mrnom;

public class Snake {
	private ISnakeSegment tail = new NullSnakeSegment();
	public enum Direction {
		UP, LEFT, DOWN, RIGHT
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
		return this.direction == other.direction && this.tail.equals(other.tail);
	}

	public void move(World world) {
		ISnakeSegment current = tail;
		current.slide();
		SnakeSegment head = current.head();
		switch (direction) {
		case UP:
			int y = head.getY() - 1;
			if (y < 0) {
				y = World.maxY;
			}	
			head.setPosition(head.getX(), y);
			break;
		case DOWN:
			int y2 = head.getY() + 1;
			if (y2 > World.maxY) {
				y2 = 0;
			}
			head.setPosition(head.getX(), y2);
			break;
		case LEFT:
			int x2 = head.getX() - 1;
			if (x2 < 0) {
				x2 = World.maxX;
			}
			head.setPosition(x2, head.getY());
			break;
		case RIGHT:
			int x = head.getX() + 1;
			if (x > World.maxX) {
				x = 0;
			}
			head.setPosition(x, head.getY());
			break;
		}

	}
	public void turnLeft() {
		switch (direction) {
		case UP:
			direction = Direction.LEFT;
			break;
		case LEFT:
			direction = Direction.DOWN;
			break;
		case DOWN:
			direction = Direction.RIGHT;
			break;
		case RIGHT:
			direction = Direction.UP;
			break;
		}
	}
	public void turnRight() {
		switch (direction) {
		case UP:
			direction = Direction.RIGHT;
			break;
		case LEFT:
			direction = Direction.UP;
			break;
		case DOWN:
			direction = Direction.LEFT;
			break;
		case RIGHT:
			direction = Direction.DOWN;
			break;
		}		
	}

}
