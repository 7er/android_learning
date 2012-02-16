package com.androidgames.mrnom;

import com.androidgames.framework.Game;
import com.androidgames.framework.Graphics;
import com.androidgames.framework.Graphics.PixmapFormat;
import com.androidgames.framework.Pixmap;
import com.androidgames.framework.Sound;

public class Assets {
	public static Pixmap	background;
	public static Pixmap	logo;
	public static Pixmap 	mainMenu;
	public static Pixmap	buttons;
	public static Pixmap	help1;
	public static Pixmap 	help2;
	public static Pixmap 	help3;
	public static Pixmap	numbers;
	public static Pixmap	ready;
	public static Pixmap	pause;
	public static Pixmap	gameOver;
	public static Pixmap	headUp;
	public static Pixmap	headLeft;
	public static Pixmap	headDown;
	public static Pixmap	headRight;
	public static Pixmap	tail;
	public static Pixmap	stain1;
	public static Pixmap	stain2;
	public static Pixmap	stain3;
	public static Sound 	click;
	public static Sound 	eat;
	public static Sound 	bitten;
	public static void load(Game game) {
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
		Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
		Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
		Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
		Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
		Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
		Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
		Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
		Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
		Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
		Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
		Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
		Assets.click = game.getAudio().newSound("click.ogg");
		Assets.eat = game.getAudio().newSound("eat.ogg");
		Assets.bitten = game.getAudio().newSound("bitten.ogg");
	}

}
