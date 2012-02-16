package com.androidgames.mrnom;

import com.androidgames.framework.Screen;
import com.androidgames.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

}
