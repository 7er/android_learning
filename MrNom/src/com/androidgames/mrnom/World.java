package com.androidgames.mrnom;

public class World {
	public static final int maxX = 9;
	public static final int maxY = 12;
	static final int WORLD_WIDTH = 10;
	static final int WORLD_HEIGHT = 13;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.5f;
	static final float TICK_DECREMENT = 0.05f;
	float tickTime = 0;
	static float tick = TICK_INITIAL;
	
	public boolean gameOver = false;

	public Snake snake;
	public World() {
		snake = Snake.createDefault();
	}
	public void update(float deltaTime) {
		if (gameOver) {
			return;
		}
		tickTime += deltaTime;
		while (tickTime > tick) {
			tickTime -= tick;
			snake.slide(this);
			
		}
	}
}
