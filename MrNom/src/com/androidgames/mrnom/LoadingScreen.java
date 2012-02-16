package com.androidgames.mrnom;


import com.androidgames.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(MrNom mrNom) {
		super(mrNom);
	}

	@Override
	public void update(float deltaTime) {
		Assets.load(game);
		Settings.load(game);
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub

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
