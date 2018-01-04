package com.hebe.jumpingline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hebe.jumpingline.screens.GameScreen;

public class JumpingLine extends Game {

	public static final int GAME_WIDTH = 1920;
	public static final int GAME_HEIGHT = 1080;
	public static final String TITLE = "Jumping Line";
	
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
			
	@Override
	public void create() {	
		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		this.font = new BitmapFont();
		
		GameScreen gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	
	public SpriteBatch getSpriteBatch() {
		return this.spriteBatch;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return this.shapeRenderer;
	}
	
	public BitmapFont getFont() {
		return this.font;
	}
	
}
