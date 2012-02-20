package com.androidgames.mrnom;

public enum Direction {
	UP, RIGHT, DOWN, LEFT;

	public Direction turnLeft() {
		switch (this) {
		case UP:
			return LEFT;
		case LEFT:
			return DOWN;
		case DOWN:
			return RIGHT;
		case RIGHT:
			return UP;
		}
		throw new IllegalStateException();
	}

	public Direction turnRight() {
		switch (this) {
		case UP:
			return RIGHT;
		case LEFT:
			return UP;
		case DOWN:
			return LEFT;
		case RIGHT:
			return DOWN;
		}
		throw new IllegalStateException();
	}

}