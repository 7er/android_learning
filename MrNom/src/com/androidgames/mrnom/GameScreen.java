package com.androidgames.mrnom;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";

	public GameScreen(Game game) {
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub

	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub

	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.x < 64 && event.y > 416) {
					world.snake.turnLeft();
				}
				if (event.x > 256 && event.y > 416) {
					world.snake.turnRight();
				}
			}
		}
		world.update(deltaTime);
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		switch (state) {
		case Ready:
			drawReadyUI();
			break;
		case Running:
			drawRunningUI();
			break;
		}
		drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2,
				g.getHeight() - 42);

	}

	public void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			if (character == ' ') {
				x += 20;
				continue;
			}
			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}
			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
		g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
	}

	private void drawWorld(World world) {
		Graphics g = game.getGraphics();
		Snake snake = world.snake;
		// Stain stain = world.stain;
		// Pixmap stainPixmap = null;
		// if (stain.type == Stain.TYPE_1)
		// stainPixmap = Assets.stain1;
		// if (stain.type == Stain.TYPE_2)
		// stainPixmap = Assets.stain2;
		// if (stain.type == Stain.TYPE_3)
		// stainPixmap = Assets.stain3;
		// int x = stain.x * 32;
		// int y = stain.y * 32;
		// g.drawPixmap(stainPixmap, x, y);
		int x;
		int y;
		ISnakeSegment segment = world.snake.getTail();
		while (!segment.next().isNull()) {
			x = segment.getX() * 32;
			y = segment.getY() * 32;
			g.drawPixmap(Assets.tail, x, y);
			segment = segment.next();
		}
		Pixmap headPixmap = null;
		if (snake.direction == Direction.UP)
			headPixmap = Assets.headUp;
		if (snake.direction == Direction.LEFT)
			headPixmap = Assets.headLeft;
		if (snake.direction == Direction.DOWN)
			headPixmap = Assets.headDown;
		if (snake.direction == Direction.RIGHT)
			headPixmap = Assets.headRight;
		x = segment.getX() * 32 + 16;
		y = segment.getY() * 32 + 16;
		g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2,
				y - headPixmap.getHeight() / 2);
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.ready, 47, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
