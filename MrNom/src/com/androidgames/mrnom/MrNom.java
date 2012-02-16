package com.androidgames.mrnom;

import com.androidgames.framework.Screen;
import com.androidgames.framework.impl.AndroidGame;

public class MrNom extends AndroidGame {
	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return new LoadingScreen(this);
	}

}
