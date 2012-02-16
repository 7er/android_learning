package com.androidgames.mrnom;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		if (state == GameState.Ready) {
			drawReadyUI();
		}
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
